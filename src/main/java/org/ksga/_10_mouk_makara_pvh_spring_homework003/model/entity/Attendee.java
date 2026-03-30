package org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {
    private Long attendeeId;
    private String attendeeName;
    private String email;
}
