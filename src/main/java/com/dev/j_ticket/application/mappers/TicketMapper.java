package com.dev.j_ticket.application.mappers;

import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.dev.j_ticket.application.dto.request.TicketRequestDTO;
import com.dev.j_ticket.application.dto.response.TicketResponseDTO;
import com.dev.j_ticket.domain.models.Ticket;

/**
 * Mapper for Ticket entity and DTOs.
 * Handles the transformation of ticket data and calculates UI-specific fields.
 */
@Mapper(componentModel = "spring")
public interface TicketMapper {

    /**
     * Maps the purchase request to a Ticket entity.
     * Note: The IDs (eventId, userId) will be linked to actual entities 
     * by the Service layer before calling this mapper or during persistence.
     */
    @Mapping(target = "event.id", source = "eventId")
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "serialNumber", ignore = true)
    Ticket toEntity(TicketRequestDTO request);

    /**
     * Maps the Ticket entity to a flat Response DTO.
     * Extracts nested data from Event and User for UI convenience.
     */
    @Mapping(target = "eventTitle", source = "event.title")
    @Mapping(target = "eventDate", source = "event.eventDate")
    @Mapping(target = "userEmail", source = "user.email")
    @Mapping(target = "formattedEventDate", source = "event.eventDate", qualifiedByName = "formatEventDate")
    @Mapping(target = "selfUrl", ignore = true)
    TicketResponseDTO toResponse(Ticket ticket);

    /**
     * Helper to format the event date specifically for the ticket view.
     * @param eventDate the LocalDateTime from the associated Event.
     * @return formatted string for the digital ticket.
     */
    @Named("formatEventDate")
    default String formatEventDate(java.time.LocalDateTime eventDate) {
        if (eventDate == null) return null;
        return eventDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
