package com.dev.j_ticket.application.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object for requesting the issuance of a new Ticket.
 * This record captures the essential identifiers and pricing information
 * required to link a User to an Event.
 * @param price the purchase price, must be a positive value.
 */
public record TicketRequestDTO(
    @NotNull(message = "Event ID is required")
    Long eventId,

    @NotNull(message = "User ID is required")
    Long userId,

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be greater than zero")
    Double price
) {}
