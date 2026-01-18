package com.dev.j_ticket.application.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.j_ticket.application.dto.response.UserResponseDTO;
import com.dev.j_ticket.application.services.UserService;
import com.dev.j_ticket.domain.models.Event;
import com.dev.j_ticket.domain.models.User;

/**
 * REST Controller for User Management.
 * This controller exposes the public and administrative endpoints for interacting 
 * with users in the J-Ticket system. It follows RESTful principles and 
 * serves as the primary entry point for the user catalog.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

	/**
     * Constructs the controller with the required UserService.
     * @param userService the service layer component responsible for user business logic.
     */
    public UserController(UserService userService) {
		this.userService = userService;
	}

    /**
     * Retrieves all users.
     * GET /api/v1/users
     * This endpoint provides a full list of users for the storefront.
     * In the future, this should support pagination and filtering (e.g., by username).
     * @return a {@link List} of {@link User} objects with a 200 OK status.
     */
	@GetMapping
    public List<UserResponseDTO> findAll() {
        return userService.getAllUsers();
    }
}
