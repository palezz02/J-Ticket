package com.dev.j_ticket.application.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.j_ticket.application.services.UserService;
import com.dev.j_ticket.domain.models.User;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

    public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
    public List<User> findAll() {
        return userService.getAllUsers();
    }
}
