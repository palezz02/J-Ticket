package com.dev.j_ticket.domain.models;

import java.time.LocalDateTime;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a scheduled event.
 * This is the core entity for the J-Ticket system to track 
 */
@Entity
@Table(name = "events")
@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Official title of the event.
     */
    @NotBlank(message = "Event name is mandatory")
    @Column(nullable = false)
    private String title;

    private String description;

    /**
     * Venue or specific location where the event is held.
     */
    @NotBlank(message = "Location is mandatory")
    @Column(nullable = false)
    private String location = "Milano";

    /**
     * The scheduled start date and time.
     * Use @Future to ensure that the Owner doesn't create events in the past.
     */
    @NotNull(message = "Event date is mandatory")
    @Future(message = "Event date must be in the future")
    @Column(nullable = false)
    private LocalDateTime eventDate;

    /**
     * Total capacity or remaining seats for the event.
     * A Senior ensures this never goes below zero via @Min.
     */
    @NotNull(message = "Available tickets count is mandatory")
    @Min(value = 0, message = "Available tickets cannot be negative")
    @Column(nullable = false)
    private Integer availableTickets;

    /**
     * List of issued tickets for the specific event.
     */
    @OneToMany(mappedBy = "event", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("event")
    private List<Ticket> tickets;
    
    /**
     * Gestisce la concorrenza (Optimistic Locking)
     */
    @Version
    private Integer version;

}
