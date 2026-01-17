package com.dev.j_ticket.domain.repositories;

import java.util.List;
import java.util.Optional;


import com.dev.j_ticket.domain.models.User;

/**
 * Domain Repository Interface for User.
 * This interface defines the contract for User persistence operations.
 * By residing in the domain layer, it ensures that the business logic 
 * remains independent of specific database technologies (like JPA or NoSQL).
 */
public interface UserRepository{
	
	/**
     * Retrieves all users in the system.
     * Primarily used for administrative purposes and user management dashboards.
     * * @return a List of all available User entities.
     */
	List<User> findAll();
	
	/**
     * Persists a new user or updates an existing one.
     * Handles the storage of sensitive data, including encrypted credentials.
     * This method is central to the user registration and profile update flow
     * @param user, the User entity to be saved.
     * @return the persisted User with its assigned database ID.
     */
	User save(User user);
	
	/**
     * Finds a specific user by its unique email address.
     * Critical for the Authentication Provider and Security Filter Chain.
     * @param email, the unique email of the user.
     * @return an Optional, containing the user if found, or empty otherwise.
     */
    Optional<User> findByEmail(String email);
}
