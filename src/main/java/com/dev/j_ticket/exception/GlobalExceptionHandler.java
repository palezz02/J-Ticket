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
     * Default constructor for the global exception handler.
     */
    public GlobalExceptionHandler() {
    }

    /**
     * Helper method to build a standardized Enterprise Error Response.
     *
     * @param status the HTTP status to return.
     * @param message the user-friendly error message.
     * @param request the current HTTP request.
     * @param errors a map of specific field errors (optional).
     * @return a formatted ResponseEntity containing the ErrorResponse object.
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
     * Handles Bean Validation errors (@NotBlank, @Email, @Min, etc.).
     *
     * @param ex the validation exception containing field errors.
     * @param request the current HTTP request.
     * @return a 400 Bad Request response with the list of validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return buildResponse(HttpStatus.BAD_REQUEST, "Validation failed", request, errors);
    }

    /**
     * Handles authentication failures.
     * Logged as WARN to monitor potential brute-force attempts on J-Ticket systems.
     *
     * @param ex the bad credentials exception.
     * @param request the current HTTP request.
     * @return a 401 Unauthorized response.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Invalid username or password", request, null);
    }
    
    /**
     * Handles malformed JSON or type mismatches.
     * Prevents the application from exposing technical parser errors to the client.
     *
     * @param ex the exception triggered by a bad request format.
     * @param request the current HTTP request.
     * @return a 400 Bad Request response.
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Malformed request or parameter mismatch", request, null);
    }

    /**
     * Handles cases where a requested resource (User, Event, Ticket) does not exist.
     *
     * @param ex the exception thrown when a resource is missing.
     * @param request the current HTTP request.
     * @return a 404 Not Found response.
     */
    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, "The requested resource was not found", request, null);
    }
    
    /**
     * Handles database integrity violations, such as duplicate entries.
     *
     * @param ex the integrity violation exception.
     * @param request the current HTTP request.
     * @return a 409 Conflict response.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, "The operation could not be completed because of a data conflict.", request, null);
    }

    /**
     * Handles security authorization failures.
     *
     * @param ex the access denied exception.
     * @param request the current HTTP request.
     * @return a 403 Forbidden response.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.FORBIDDEN, "You do not have permission to access this resource.", request, null);
    }

    /**
     * Handles concurrent update failures (Optimistic Locking).
     * Crucial for preventing overbooking in ticket sales.
     *
     * @param ex the optimistic locking failure exception.
     * @param request the current HTTP request.
     * @return a 409 Conflict response.
     */
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handleOptimisticLocking(ObjectOptimisticLockingFailureException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, "The ticket was modified by another user. Please try again.", request, null);
    }

    /**
     * Top-level catch-all for any unhandled server-side exceptions.
     * It masks real stack traces while providing a Trace ID for investigation.
     *
     * @param ex the unexpected exception.
     * @param request the current HTTP request.
     * @return a 500 Internal Server Error response.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An internal error occurred.", request, null);
    }
}