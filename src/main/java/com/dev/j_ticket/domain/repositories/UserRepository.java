package com.dev.j_ticket.domain.repositories;

import java.util.List;
import java.util.Optional;


import com.dev.j_ticket.domain.models.User;

public interface UserRepository{
	List<User> findAll();
	User save(User user);
	Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
