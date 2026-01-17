package com.dev.j_ticket;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

/**
 * J-Ticket Application - Core Entry Point.
 * This service manages the ticketing lifecycle,
 * including event scheduling, ticket issuance, and user management.
 * @author palezz02
 * @version 1.0
 */
@SpringBootApplication
public class JTicketApplication {
	
	/**
     * Ensures the application consistently uses UTC or a specific timezone.
     */
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

	public static void main(String[] args) {
		SpringApplication.run(JTicketApplication.class, args);
	}

}
