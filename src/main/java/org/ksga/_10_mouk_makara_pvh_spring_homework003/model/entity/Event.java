package org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long eventId;
    private String eventName;
    private LocalDate eventDate;
    private Venue venue;
    private List<Attendee> attendees;
}

