package com.dev.j_ticket.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for representing a Ticket in API responses.
 * Provides a consolidated view of ticket details, including associated 
 * event information and owner identification.
 * @param price the monetary value of the ticket in BigDecimal for financial precision.
 * @param serialNumber unique code for ticket validation at the venue.
 * @param eventTitle the name of the event this ticket is for.
 * @param eventDate raw timestamp of the event for sorting or logic.
 * @param formattedEventDate human-readable date for UI display.
 * @param userEmail email of the ticket holder for identification.
 * @param selfUrl HATEOAS link to this specific ticket resource.
 */
public record TicketResponseDTO(
    Long id,
    BigDecimal price,
    String serialNumber,
    String eventTitle,
    LocalDateTime eventDate,
    String formattedEventDate,
    String userEmail,
    String selfUrl
) {}
