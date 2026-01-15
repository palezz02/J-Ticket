package com.dev.j_ticket.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.j_ticket.domain.models.Ticket;

public interface SpringDataTicketRepository extends JpaRepository<Ticket, Long> {
}
