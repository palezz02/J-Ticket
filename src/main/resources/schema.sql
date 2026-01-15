-- J-Ticket Database Schema
-- Last Update: 2026-01-13
-- Location: Milano

DROP TABLE IF EXISTS events CASCADE;

CREATE TABLE IF NOT EXISTS events (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    location VARCHAR(100) NOT NULL DEFAULT 'Florence',
    event_date TIMESTAMP NOT NULL,
    available_tickets INT NOT NULL,
    version INT DEFAULT 0
);