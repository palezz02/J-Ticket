INSERT INTO events (title, description, location, event_date, available_tickets, version)
VALUES ('Grand Opening Green Day', 'Evento inaugurale a Milano', 'Fiera Milano (Rho)', '2026-06-01 21:00:00', 500, 0);

INSERT INTO users (username, email, password) 
VALUES ('mario_rossi', 'mario@email.it', 'password123')
ON CONFLICT (username) DO NOTHING;