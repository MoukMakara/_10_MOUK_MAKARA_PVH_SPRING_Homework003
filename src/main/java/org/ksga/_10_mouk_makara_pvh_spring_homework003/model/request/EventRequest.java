package org.ksga._10_mouk_makara_pvh_spring_homework003.model.request;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    @NotBlank(message = "Event name cannot be blank")
    @Size(min = 1, max = 100, message = "Venue name must not exceed 100 characters")
    private String eventName;

    @NotNull(message = "Event date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Future(message = "Event date must be in the future")
    private LocalDate eventDate;

    @NotNull(message = "Venue cannot be null")
    private Long venueId;


    @NotEmpty(message = "Attendees cannot be empty")
    @Size(min = 1, message = "Attendees must be at least one attendee")
    private List<Long> attendeeIds;
}
