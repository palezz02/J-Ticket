
CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    location VARCHAR(100) NOT NULL DEFAULT 'Milano',
    event_date TIMESTAMP NOT NULL,
    available_tickets INT NOT NULL,
    version INT DEFAULT 0
);

INSERT INTO events (title, description, location, event_date, available_tickets, version)
VALUES ('Grand Opening Green Day', 'Evento inaugurale a Milano', 'Fiera Milano (Rho)', '2026-06-01 21:00:00', 500, 0);