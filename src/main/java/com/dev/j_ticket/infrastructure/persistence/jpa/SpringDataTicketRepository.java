package com.dev.j_ticket.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.j_ticket.domain.models.Ticket;

/**
 * Spring Data JPA implementation for Ticket persistence.
 * This interface provides the actual SQL execution logic using Hibernate.
 */
public interface SpringDataTicketRepository extends JpaRepository<Ticket, Long> {
}
