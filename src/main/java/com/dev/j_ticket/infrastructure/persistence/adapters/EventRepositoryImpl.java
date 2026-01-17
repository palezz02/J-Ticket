package com.dev.j_ticket.infrastructure.persistence.adapters;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dev.j_ticket.domain.models.Event;
import com.dev.j_ticket.domain.repositories.EventRepository;
import com.dev.j_ticket.infrastructure.persistence.jpa.SpringDataEventRepository;

/**
 * Persistence Adapter for Event entities.
 * This class implements the EventRepository by wrapping the 
 * SpringDataEventRepository. It acts as a bridge between the 
 * domain layer and the Spring Data JPA infrastructure, ensuring that 
 * the business logic is decoupled from the underlying ORM framework.
 */
@Repository
public class EventRepositoryImpl implements EventRepository {

    private final SpringDataEventRepository jpaRepo;

    public EventRepositoryImpl(SpringDataEventRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    /**
     * Fetches all events by delegating the call to the JPA repository.
     * @return a {@link List} of all {@link Event} entities found in the database.
     */
	@Override
	public List<Event> findAll() {
		return jpaRepo.findAll();
	}

	/**
     * Executes the persistence of an event to the PostgreSQL database.
     * @param event, the event to persist.
     * @return the saved {@link Event} including the generated database primary key.
     */
	@Override
	public Event save(Event event) {
		// TODO Auto-generated method stub
		return null;
	}

}
