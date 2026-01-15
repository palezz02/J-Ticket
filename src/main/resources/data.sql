-- Inserimento Eventi
INSERT INTO events (title, description, location, event_date, available_tickets, version)
VALUES ('Grand Opening Green Day', 'Evento inaugurale a Firenze', 'Visarno Arena', '2026-06-01 21:00:00', 500, 0);

-- Inserimento Utenti
INSERT INTO users (id, username, email, password) 
VALUES (1, 'mario_rossi', 'mario@email.it', 'password123');

-- Inserimento Ticket (Collega utente 1 con l'evento dei Green Day)
INSERT INTO tickets (price, serial_number, user_id, event_id)
VALUES (75.50, 'TKT-GD-2026-001', 1, (SELECT id FROM events WHERE title = 'Grand Opening Green Day' LIMIT 1));