package com.dev.j_ticket.application.mappers;

import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.dev.j_ticket.application.dto.request.EventRequestDTO;
import com.dev.j_ticket.application.dto.response.EventResponseDTO;
import com.dev.j_ticket.domain.models.Event;

/**
 * Mapper for Event entity and DTOs.
 * Handles the transformation of event data and calculates UI-specific fields.
 */
@Mapper(componentModel = "spring")
public interface EventMapper {

	/**
     * Maps the creation/update request to the domain entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "version", ignore = true)
    Event toEntity(EventRequestDTO request);

    /**
     * Maps the domain entity to a response DTO.
     * Includes business logic for sold-out status and localized date formatting.
     */
    @Mapping(target = "isSoldOut", source = "availableTickets", qualifiedByName = "mapIsSoldOut")
    @Mapping(target = "formattedDate", source = "eventDate", qualifiedByName = "mapFormattedDate")
    @Mapping(target = "selfUrl", ignore = true)
    EventResponseDTO toResponse(Event event);

    /**
     * Business Logic: Determines if the event is sold out based on remaining tickets.
     * @param availableTickets current tickets in stock.
     * @return true if zero or null, false otherwise.
     */
    @Named("mapIsSoldOut")
    default boolean mapIsSoldOut(Integer availableTickets) {
        return availableTickets == null || availableTickets <= 0;
    }

    /**
     * Technical Utility: Formats the date for the users' locale.
     * @param eventDate the raw LocalDateTime.
     * @return formatted string (dd/MM/yyyy HH:mm).
     */
    @Named("mapFormattedDate")
    default String mapFormattedDate(java.time.LocalDateTime eventDate) {
        if (eventDate == null) return null;
        return eventDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
