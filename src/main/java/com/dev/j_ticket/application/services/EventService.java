package com.dev.j_ticket.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.j_ticket.domain.models.Event;
import com.dev.j_ticket.domain.repositories.EventRepository;

@Service
public class EventService {

	private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
