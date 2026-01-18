package com.dev.j_ticket.application.mappers;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.dev.j_ticket.application.dto.request.UserRequestDTO;
import com.dev.j_ticket.application.dto.response.UserResponseDTO;
import com.dev.j_ticket.domain.models.Ticket;
import com.dev.j_ticket.domain.models.User;

/**
 * Mapper for User entity management.
 * Bridges the gap between secure authentication data and public profile information.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Maps UserRequestDTO to the domain entity.
     * Note: The password field is mapped directly; encryption must be performed 
     * by the Service layer before persisting to the database.
     */
    User toEntity(UserRequestDTO request);

    /**
     * Maps the User entity to a secure Response DTO.
     * Automatically excludes the password as it's not present in the record.
     */
    @Mapping(target = "ticketCount", source = "tickets", qualifiedByName = "mapActiveTicketsCount")
    @Mapping(target = "selfUrl", ignore = true)
    UserResponseDTO toResponse(User user);

    /**
     * Business Logic: Counts only tickets for future events.
     * This filtering ensures the frontend shows relevant data to the user.
     * @param tickets the collection of all tickets owned by the user.
     * @return count of tickets where the event date is in the future.
     */
    @Named("mapActiveTicketsCount")
    default int mapActiveTicketsCount(List<Ticket> tickets) {
        if (tickets == null) return 0;
        
        LocalDateTime now = LocalDateTime.now();
        
        return (int) tickets.stream()
            .filter(t -> t.getEvent() != null && t.getEvent().getEventDate().isAfter(now))
            .count();
    }
}
