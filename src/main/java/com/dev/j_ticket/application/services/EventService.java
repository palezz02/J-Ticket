package com.dev.j_ticket.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Retrieves the complete catalog of events available in the system.
     * This method serves as the primary data provider for the public 
     * and the administrative dashboard.
     * * @return a List of all active and past Event entities.
     */
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
