package com.dev.j_ticket.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.j_ticket.domain.models.User;

/**
 * Spring Data JPA implementation for User persistence.
 * This interface provides the actual SQL execution logic using Hibernate.
 */
public interface SpringDataUserRepository extends JpaRepository<User, Long>{

}
