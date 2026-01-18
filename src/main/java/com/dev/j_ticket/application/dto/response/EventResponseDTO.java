package com.dev.j_ticket.application.dto.response;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for representing an Event in API responses.
 * This record provides a read-only view of the Event entity,
 * including calculated fields for UI rendering and business logic.
 * @param id the unique technical identifier of the event.
 * @param title the official name or title of the event.
 * @param description a detailed summary of the event activities.
 * @param location the venue or city where the event takes place.
 * @param eventDate the raw date and time for logical processing and filtering.
 * @param availableTickets the current number of seats or tickets left for purchase.
 * @param isSoldOut business flag indicating if the event has reached its maximum capacity.
 * @param formattedDate human-readable date string pre-formatted for UI display.
 * @param selfUrl the direct API resource link for HATEOAS-compliant navigation.
 */
public record EventResponseDTO(
    Long id,
    String title,
    String description,
    String location,
    LocalDateTime eventDate,
    Integer availableTickets,
    boolean isSoldOut, 
    String formattedDate, 
    String selfUrl
) {}
