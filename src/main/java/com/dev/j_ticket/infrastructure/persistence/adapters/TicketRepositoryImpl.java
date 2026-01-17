package com.dev.j_ticket.infrastructure.persistence.adapters;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dev.j_ticket.domain.models.Ticket;
import com.dev.j_ticket.domain.repositories.TicketRepository;
import com.dev.j_ticket.infrastructure.persistence.jpa.SpringDataTicketRepository;

/**
 * Persistence Adapter for Ticket entities.
 * This class implements the EventRepository by wrapping the 
 * SpringDataEventRepository. It acts as a bridge between the 
 * domain layer and the Spring Data JPA infrastructure, ensuring that 
 * the business logic is decoupled from the underlying ORM framework.
 */
@Repository
public class TicketRepositoryImpl implements TicketRepository{
	
	private final SpringDataTicketRepository jpaRepo;

	public TicketRepositoryImpl(SpringDataTicketRepository jpaRepo) {
		this.jpaRepo = jpaRepo;
	}

    /**
     * Fetches all tickets by delegating the call to the JPA repository.
     * @return a {@link List} of all {@link Ticket} entities found in the database.
     */
	@Override
    public List<Ticket> findAll() {
        return jpaRepo.findAll();
    }

	/**
     * Delegates the lookup of a ticket by ID to the JPA repository.
     * @param id, the internal database identifier.
     * @return an {@link Optional}, containing the found ticket, or empty if no ticket exists with the given ID.
     */
    @Override
    public Optional<Ticket> findById(Long id) {
        return jpaRepo.findById(id);
    }

	/**
     * Executes the persistence of a ticket to the PostgreSQL database.
     * @param the ticket to persist.
     * @return the saved {@link Ticket} including the generated database primary key.
     */
    @Override
    public Ticket save(Ticket ticket) {
        return jpaRepo.save(ticket);
    }

}
