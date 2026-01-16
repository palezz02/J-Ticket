package com.dev.j_ticket.exception;

import lombok.Builder;
import lombok.Getter;

@Getter 
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private String timestamp;
}
