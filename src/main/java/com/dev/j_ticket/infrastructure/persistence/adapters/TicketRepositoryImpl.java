package com.dev.j_ticket.infrastructure.persistence.adapters;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dev.j_ticket.domain.models.Ticket;
import com.dev.j_ticket.domain.repositories.TicketRepository;
import com.dev.j_ticket.infrastructure.persistence.jpa.SpringDataTicketRepository;

@Repository
public class TicketRepositoryImpl implements TicketRepository{
	
	private final SpringDataTicketRepository jpaRepo;

	public TicketRepositoryImpl(SpringDataTicketRepository jpaRepo) {
		this.jpaRepo = jpaRepo;
	}

	@Override
    public List<Ticket> findAll() {
        return jpaRepo.findAll();
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return jpaRepo.findById(id);
    }

    @Override
    public Ticket save(Ticket ticket) {
        return jpaRepo.save(ticket);
    }

}
