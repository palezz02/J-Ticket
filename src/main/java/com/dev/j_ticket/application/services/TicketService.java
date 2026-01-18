package com.dev.j_ticket.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.j_ticket.application.dto.response.TicketResponseDTO;
import com.dev.j_ticket.application.mappers.EventMapper;
import com.dev.j_ticket.application.mappers.TicketMapper;
import com.dev.j_ticket.domain.models.Event;
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
	private final TicketMapper ticketMapper;

	/**
     * Constructs the service with its required repository port.
     * @param ticketRepository the domain repository interface for ticket data access.
     */
    public TicketService(TicketRepository ticketRepository, TicketMapper ticketMapper) {
		this.ticketRepository = ticketRepository;
		this.ticketMapper = ticketMapper;
	}

    /**
     * Retrieves the complete catalog of tickets available in the system.
     * This method serves as the primary data provider for 
     * the administrative dashboard.
     * @return a {@link List} of all active and past {@link Ticket} entities in ResponseDTO format.
     */
	public List<TicketResponseDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(ticketMapper::toResponse)
                .toList();
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
