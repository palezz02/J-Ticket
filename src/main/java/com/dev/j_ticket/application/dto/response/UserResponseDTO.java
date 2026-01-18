package com.dev.j_ticket.application.dto.response;

/**
 * Data Transfer Object for representing a User in API responses.
 * Provides public identity information and account statistics 
 * while ensuring sensitive credentials remain hidden.
 * @param username chosen display name for the user.
 * @param email primary unique identifier for the user.
 * @param activeTicketsCount number of tickets for upcoming events.
 * @param selfUrl HATEOAS link to the user's detailed profile.
 */
public record UserResponseDTO(
    Long id,
    String username,
    String email,
    int activeTicketsCount,
    String selfUrl
) {}
