package com.dev.j_ticket.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import java.util.Map;

/**
 * API error response.
 * Standardizes how the J-Ticket system communicates failures to the frontend.
 */
@Getter 
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    /**
     * HTTP Status code (e.g., 400, 401, 404, 500).
     * Essential for the client to handle logic.
     */
    private int status;

    /**
     * Human-readable message explaining the error.
     * Can be shown directly to the user.
     */
    private String message;

    /**
     * ISO-8601 formatted timestamp of the error.
     */
    private String timestamp;

    /**
     * The API endpoint path where the error occurred.
     * Helps developers identify exactly which resource failed.
     */
    private String path;

    /**
     * Map of specific validation errors.
     * Only populated for 400 Bad Request errors.
     */
    private Map<String, String> errors;

    /**
     * Internal tracking ID.
     * Useful for cross-referencing this error with server-side logs (UUID).
     */
    private String traceId;
}