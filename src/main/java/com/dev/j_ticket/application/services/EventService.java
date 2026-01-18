package com.dev.j_ticket.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.j_ticket.application.dto.response.EventResponseDTO;
import com.dev.j_ticket.application.mappers.EventMapper;
import com.dev.j_ticket.domain.models.Event;
import com.dev.j_ticket.domain.repositories.EventRepository;

/**
 * Application Service for managing Event-related operations.
 * This class acts as the Orchestrator for the Event business logic. 
 * It coordinates calls to the domain repositories and will eventually 
 * handle cross-cutting concerns such as authorization, validation, 
 * and transactional boundaries for the J-Ticket system.
 */
@Service
public class EventService {

	private final EventRepository eventRepository;
	private final EventMapper eventMapper;

	/**
     * Constructs the service with its required repository port.
     * @param eventRepository the domain repository interface for event data access.
     * @param eventMapper the domain repository interface for event data access.
     */
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    /**
     * Retrieves the complete catalog of events available in the system.
     * This method serves as the primary data provider for the public 
     * and the administrative dashboard.
     * @return a {@link List} of all active and past {@link Event} entities in ResponseDTO format.
     */
    public List<EventResponseDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(eventMapper::toResponse)
                .toList();
    }
}
