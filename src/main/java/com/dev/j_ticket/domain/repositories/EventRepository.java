package com.dev.j_ticket.domain.repositories;

import java.util.List;

import com.dev.j_ticket.domain.models.Event;

/**
 * Domain Repository Interface for Event.
 * This interface defines the contract for Event persistence operations.
 * By residing in the domain layer, it ensures that the business logic 
 * remains independent of specific database technologies (like JPA or NoSQL).
 */
public interface EventRepository {
	
	/**
     * Retrieves all events managed by the system.
     * Useful for main dashboard and event listing.
     * * @return a List of all available Event entities.
     */
	List<Event> findAll();
	
	/**
     * Persists a new event or updates an existing one.
     * This method is the entry point to publish new 
     * concerts or shows into the system.
     * @param event the Event entity to be saved.
     * @return the persisted Event with its assigned database ID.
     */
    Event save(Event event);
}
