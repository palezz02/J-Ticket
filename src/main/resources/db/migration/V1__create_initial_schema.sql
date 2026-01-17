DROP TABLE IF EXISTS events CASCADE;

CREATE TABLE IF NOT EXISTS events (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    location VARCHAR(100) NOT NULL DEFAULT 'Milano',
    event_date TIMESTAMP NOT NULL,
    available_tickets INT NOT NULL,
    version INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tickets (
    id BIGSERIAL PRIMARY KEY,
    price DECIMAL(10,2) NOT NULL,
    serial_number VARCHAR(100) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE
);


-- Inserimento Eventi
INSERT INTO events (title, description, location, event_date, available_tickets, version)
VALUES ('Grand Opening Green Day', 'Evento inaugurale a Firenze', 'Visarno Arena', '2026-06-01 21:00:00', 500, 0);

-- Inserimento Utenti
INSERT INTO users (id, username, email, password) 
VALUES (1, 'mario_rossi', 'mario@email.it', 'password123');

-- Inserimento Ticket (Collega utente 1 con l'evento dei Green Day)
INSERT INTO tickets (price, serial_number, user_id, event_id)
VALUES (75.50, 'TKT-GD-2026-001', 1, (SELECT id FROM events WHERE title = 'Grand Opening Green Day' LIMIT 1));


