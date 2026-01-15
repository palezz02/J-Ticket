package com.dev.j_ticket.domain.repositories;

import java.util.List;
import java.util.Optional;

import com.dev.j_ticket.domain.models.Ticket;

public interface TicketRepository {
	List<Ticket> findAll();
	Ticket save(Ticket ticket);
    Optional<Ticket> findById(Long id);
}
