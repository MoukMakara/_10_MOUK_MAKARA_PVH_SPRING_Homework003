package org.ksga._10_mouk_makara_pvh_spring_homework003.service;

import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Attendee;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.AttendeeRequest;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.AttendeeUpdateRequest;

import java.util.List;

public interface AttendeeService {

    List<Attendee> getAllAttendees(Long page, Long size);

    Attendee getAttendeeById(Long attendeeId);

    Attendee createAttendee(AttendeeRequest request);

    Attendee deleteAttendeeById(Long attendeeId);

    Attendee updateAttendeeById(Long attendeeId, AttendeeUpdateRequest request);
}
