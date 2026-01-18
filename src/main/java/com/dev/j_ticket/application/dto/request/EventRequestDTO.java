package com.dev.j_ticket.application.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for creating or updating an Event.
 * Includes Bean Validation constraints to ensure data integrity at the entry point.
 * @param eventDate must be a future date to prevent scheduling past events.
 * @param availableTickets must be zero or greater: represents initial event capacity.
 */
public record EventRequestDTO(
    @NotBlank(message = "Event title is mandatory")
    String title,

    String description,

    @NotBlank(message = "Location is mandatory")
    String location,

    @NotNull(message = "Event date is mandatory")
    @Future(message = "Event date must be in the future")
    LocalDateTime eventDate,

    @NotNull(message = "Available tickets count is mandatory")
    @Min(value = 0, message = "Available tickets cannot be negative")
    Integer availableTickets
) {}
