package com.dev.j_ticket.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.j_ticket.domain.models.User;
import com.dev.j_ticket.domain.repositories.UserRepository;

/**
 * Application Service for managing User-related operations.
 * This class acts as the Orchestrator for the User business logic. 
 * It coordinates calls to the domain repositories and will eventually 
 * handle cross-cutting concerns such as authorization, validation, 
 * and transactional boundaries for the J-Ticket system.
 */
@Service
public class UserService {
	
	private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

    /**
     * Retrieves the complete catalog of users available in the system.
     * This method serves as the primary data provider for 
     * the administrative dashboard.
     * @return a List of all User entities.
     */
	public List<User> getAllUsers() {
        return userRepository.findAll();
    }

	/**
     * Finds a specific user by their unique email address.
     * @param email the unique email address to search for.
     * @return an Optional, containing the found user, or empty if no match exists.
     */
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
