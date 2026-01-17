package com.dev.j_ticket.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.j_ticket.domain.models.Ticket;
import com.dev.j_ticket.domain.repositories.TicketRepository;

/**
 * Application Service for managing Ticket-related operations.
 * This class acts as the Orchestrator for the Ticket business logic. 
 * It coordinates calls to the domain repositories and will eventually 
 * handle cross-cutting concerns such as authorization, validation, 
 * and transactional boundaries for the J-Ticket system.
 */
@Service
public class TicketService {

	private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

    /**
     * Retrieves the complete catalog of tickets available in the system.
     * This method serves as the primary data provider for 
     * the administrative dashboard.
     * @return a {@link List} of all active and past {@link Ticket} entities.
     */
	public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

	/**
     * Orchestrates the issuance of a new ticket.
     * @param ticket, the ticket domain object containing purchase details.
     * @return the newly created and persisted {@link Ticket}.
     */
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
