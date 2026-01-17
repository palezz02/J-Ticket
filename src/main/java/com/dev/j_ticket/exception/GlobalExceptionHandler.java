package com.dev.j_ticket.exception;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Global controller advice to handle all application exceptions.
 * It transforms technical errors into readable JSON responses
 * for the frontend, ensuring no sensitive system info is leaked.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
     * Helper method to build the Enterprise Error Response.
     */
	private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message, HttpServletRequest request, Map<String, String> errors) {
        String traceId = UUID.randomUUID().toString();
        
        log.error("TraceID: [{}] | Path: [{}] | Status: {} | Message: {}", 
                  traceId, request.getRequestURI(), status.value(), message);

        ErrorResponse error = ErrorResponse.builder()
                .status(status.value())
                .message(message)
                .timestamp(LocalDateTime.now().toString())
                .path(request.getRequestURI())
                .errors(errors) 
                .traceId(traceId)
                .build();
        
        return new ResponseEntity<>(error, status);
    }
	
	/**
     * Handles Bean Validation errors (@NotBlank, @Email, @Min, etc.)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return buildResponse(HttpStatus.BAD_REQUEST, "Validation failed", request, errors);
    }

    /**
     * Handles authentication failures.
     * Logged as WARN to monitor potential brute-force attempts on J-Tycket systems.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Invalid username or password", request, null);
    }
    
    /**
     * Handles malformed JSON or type mismatches.
     * Prevents the application from exposing technical parser errors to the client.
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Malformed request or parameter mismatch", request, null);
    }

    /**
     * Handles cases where a requested resource (User, Event, Ticket) does not exist.
     */
    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, "The requested resource was not found", request, null);
    }
    
    /**
     * Handles database integrity violations, such as duplicate emails.
     * Maps to 409 Conflict as the resource already exists.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, "The operation could not be completed because of a data conflict (e.g., duplicate entry).", request, null);
    }

    /**
     * Handles security authorization failures.
     * Maps to 403 Forbidden.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.FORBIDDEN, "You do not have permission to access this resource.", request, null);
    }

    /**
     * Handles concurrent update failures (Optimistic Locking).
     * Crucial for preventing overbooking in ticket sales.
     */
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handleOptimisticLocking(ObjectOptimisticLockingFailureException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, "The ticket was modified by another user. Please try again.", request, null);
    }

    /**
     * Top-level catch-all for any unhandled server-side exceptions.
     * It masks real stack traces while providing
     * a Trace ID for developers to investigate the logs.
     * * @return 500 Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An internal error occurred.", request, null);
    }
}
