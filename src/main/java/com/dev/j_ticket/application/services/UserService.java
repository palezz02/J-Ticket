package com.dev.j_ticket.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.j_ticket.domain.models.User;
import com.dev.j_ticket.domain.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
