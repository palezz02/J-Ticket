package com.dev.j_ticket.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.j_ticket.domain.models.Event;

/**
 * Spring Data JPA implementation for Event persistence.
 * This interface provides the actual SQL execution logic using Hibernate.
 */
public interface SpringDataEventRepository extends JpaRepository<Event, Long>{

}
