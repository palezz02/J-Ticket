package com.dev.j_ticket.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for requesting User registration and profile updates.
 * This record captures authentication and identity details.
 * @param password is encrypted by the service layer before persistence.
 */
public record UserRequestDTO(
    @NotBlank(message = "Username is mandatory")
    String username,
    
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password,

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    String email
) {}
