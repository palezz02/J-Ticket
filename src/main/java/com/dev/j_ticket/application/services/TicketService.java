package com.dev.j_ticket.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.j_ticket.domain.models.Ticket;
import com.dev.j_ticket.domain.repositories.TicketRepository;

@Service
public class TicketService {

	private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
