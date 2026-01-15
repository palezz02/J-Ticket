package com.dev.j_ticket.domain.repositories;

import java.util.List;

import com.dev.j_ticket.domain.models.Event;

public interface EventRepository {
	List<Event> findAll();
    Event save(Event event);
}
