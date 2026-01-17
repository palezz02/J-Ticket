package com.dev.j_ticket.application.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.j_ticket.application.services.EventService;
import com.dev.j_ticket.domain.models.Event;

/**
 * REST Controller for Event Management.
 * This controller exposes the public and administrative endpoints for interacting 
 * with events in the J-Ticket system. It follows RESTful principles and 
 * serves as the primary entry point for the event catalog.
 */
@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Retrieves all events.
     * GET /api/v1/events
     * This endpoint provides a full list of events for the storefront.
     * In the future, this should support pagination and filtering (e.g., by date or location).
     * @return a {@link List} of {@link Event} objects with a 200 OK status.
     */
    @GetMapping
    public List<Event> getAll() {
        return eventService.getAllEvents();
    }
}
