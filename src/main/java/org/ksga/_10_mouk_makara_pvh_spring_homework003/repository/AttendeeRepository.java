package org.ksga._10_mouk_makara_pvh_spring_homework003.repository;

import org.apache.ibatis.annotations.*;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Attendee;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.AttendeeRequest;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.AttendeeUpdateRequest;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    @Results(id = "attendeeResultMap", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
    })
    @Select("""
    SELECT * FROM attendees OFFSET #{offset} LIMIT #{size}
""")
    List<Attendee> getAllAttendees(Long offset, Long size);

    @ResultMap("attendeeResultMap")
    @Select("""
    SELECT * FROM attendees WHERE attendee_id = #{attendeeId}
""")
    Attendee getAttendeeById(Long attendeeId);

    @ResultMap("attendeeResultMap")
    @Select("""
    INSERT INTO attendees VALUES (default, #{req.attendeeName}, #{req.email}) RETURNING *
""")
    Attendee createAttendee(@Param("req") AttendeeRequest request);

    @ResultMap("attendeeResultMap")
    @Select("""
    SELECT * FROM attendees WHERE attendee_name = #{requestAttendeeName} LIMIT 1
""")
    Attendee findAttendeeByName(String requestAttendeeName);

    @ResultMap("attendeeResultMap")
    @Select("""
    SELECT * FROM attendees WHERE email = #{requestEmail} LIMIT 1
""")
    Attendee findAttendeeByEmail(String requestEmail);

    @ResultMap("attendeeResultMap")
    @Select("""
    DELETE FROM attendees WHERE attendee_id = #{attendeeId} RETURNING *
""")
    Attendee deleteAttendeeById(Long attendeeId);

    @ResultMap("attendeeResultMap")
    @Select("""
    UPDATE attendees SET attendee_name = #{req.attendeeName}
    WHERE attendee_id = #{attendeeId} RETURNING *
""")
    Attendee updateAttendeeById(Long attendeeId, @Param("req") AttendeeUpdateRequest request);
}
