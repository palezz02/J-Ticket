package com.dev.j_ticket.domain.models;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a registered user in the J-Ticket system.
 * Handles authentication data and maintains a record of purchased tickets.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User's chosen login name or display name.
     * Note: In this configuration, username is NOT unique, 
     * reliance for identification is placed solely on the email.
     */
    @NotBlank(message = "Username is mandatory")
    @Column(nullable = false)
    private String username;
    
    /**
     * Encrypted password string. 
     * Sensitive data: should never be exposed in API responses.
     */
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(nullable = false)
    private String password;

    /**
     * Primary unique identifier for authentication and communication.
     */
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * List of tickets owned by this user.
     * CascadeType.ALL ensures that if a user is deleted, their tickets 
     * are handled according to business requirements.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private List<Ticket> tickets;
}
