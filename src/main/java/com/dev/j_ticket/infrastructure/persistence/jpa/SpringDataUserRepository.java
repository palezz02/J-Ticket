package com.dev.j_ticket.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.j_ticket.domain.models.User;

public interface SpringDataUserRepository extends JpaRepository<User, Long>{

}
