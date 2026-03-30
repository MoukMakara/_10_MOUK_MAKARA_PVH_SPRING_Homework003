SELECT * FROM attendees;

INSERT INTO events VALUES (default, 'bdfghjk', '2026-03-30', 4);
SELECT * FROM events;

INSERT INTO event_attendees VALUES (4, 1), (5, 1);

SELECT * FROM event_attendees ea
    INNER JOIN attendees a ON ea.attendee_id = a.attendee_id
WHERE ea.event_id = 1;

