package com.dev.j_ticket.application.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.j_ticket.application.dto.response.TicketResponseDTO;
import com.dev.j_ticket.application.services.TicketService;
import com.dev.j_ticket.domain.models.Event;
import com.dev.j_ticket.domain.models.Ticket;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * REST Controller for Ticket Management.
 * This controller exposes the public and administrative endpoints for interacting 
 * with tickets in the J-Ticket system. It follows RESTful principles and 
 * serves as the primary entry point for the ticket catalog.
 */
@RestController
@RequestMapping("/api/v1/tickets")
public class TicketControllers {

	private final TicketService ticketService;

	/**
     * Constructs the controller with the required TicketService.
     * @param ticketService the service layer component responsible for ticket business logic.
     */
    public TicketControllers(TicketService ticketService) {
		this.ticketService = ticketService;
	}

    /**
     * Retrieves all tickets.
     * GET /api/v1/tickets
     * This endpoint provides a full list of tickets for the storefront.
     * In the future, this should support pagination and filtering (e.g., by user or date).
     * @return a {@link List} of {@link Ticket} objects with a 200 OK status.
     */
	@GetMapping
    public List<TicketResponseDTO> findAll() {
        return ticketService.getAllTickets();
    }

	/**
     * Issues a new ticket in the system.
     * POST /api/v1/tickets
     * This endpoint triggers the ticket purchase workflow. It requires a valid 
     * ticket representation in the request body.
     * @param ticket, the ticket data provided by the client.
     * @return the created {@link Ticket} with its unique identifier and a 201 Created status.
     */
    @PostMapping
    public Ticket save(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }
}
