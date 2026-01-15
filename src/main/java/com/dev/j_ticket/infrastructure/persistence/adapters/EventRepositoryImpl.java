package com.dev.j_ticket.infrastructure.persistence.adapters;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dev.j_ticket.domain.models.Event;
import com.dev.j_ticket.domain.repositories.EventRepository;
import com.dev.j_ticket.infrastructure.persistence.jpa.SpringDataEventRepository;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private final SpringDataEventRepository jpaRepo;

    public EventRepositoryImpl(SpringDataEventRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

	@Override
	public List<Event> findAll() {
		return jpaRepo.findAll();
	}

	@Override
	public Event save(Event event) {
		// TODO Auto-generated method stub
		return null;
	}

}
