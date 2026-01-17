package com.dev.j_ticket.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.dev.j_ticket.domain.models.Ticket;

/**
 * Domain Repository Interface for Ticket.
 * This interface defines the contract for Ticket persistence operations.
 * By residing in the domain layer, it ensures that the business logic 
 * remains independent of specific database technologies (like JPA or NoSQL).
 */
public interface TicketRepository {
	
	/**
     * Retrieves all issued tickets in the system.
     * Primarily used for administrative auditing and global sales reporting.
     * * @return a {@link List} of all {@link Ticket} entities.
     */
	List<Ticket> findAll();
	
	/**
     * Persists a new ticket or updates an existing one.
     * This method ensures that the ticketis officially
     * registered and linked to a user and an event.
     * @param ticket, the Ticket entity to be saved.
     * @return the persisted {@link Ticket} with its assigned database ID.
     */
	Ticket save(Ticket ticket);
	
	/**
     * Finds a specific ticket by its unique primary key.
     * Used for validation during entry checks at the venue or for 
     * retrieving details for a specific customer request.
     * @param id, the unique ID of the ticket.
     * @return an {@link Optional}, containing the ticket if found, or empty otherwise.
     */
    Optional<Ticket> findById(Long id);
}
