package com.dev.j_ticket.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.j_ticket.domain.models.Event;

public interface SpringDataEventRepository extends JpaRepository<Event, Long>{

}
