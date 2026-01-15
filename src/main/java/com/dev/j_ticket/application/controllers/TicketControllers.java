package com.dev.j_ticket.application.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.j_ticket.application.services.TicketService;
import com.dev.j_ticket.domain.models.Ticket;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketControllers {

	private final TicketService ticketService;

    public TicketControllers(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@GetMapping
    public List<Ticket> findAll() {
        return ticketService.getAllTickets();
    }

    @PostMapping
    public Ticket save(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }
}
