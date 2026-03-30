package org.ksga._10_mouk_makara_pvh_spring_homework003.repository;

import org.apache.ibatis.annotations.*;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Attendee;

import java.util.List;

@Mapper
public interface EventAttendeeRepository {
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
    })
    @Select("""
            SELECT * FROM event_attendees ea
                INNER JOIN attendees a ON ea.attendee_id = a.attendee_id
            WHERE ea.event_id = #{eventId}
 """)
    public List<Attendee> getAttendeeIdByEventId(Long eventId);

    @ResultMap("attendeeMapper")
    @Select("""
    INSERT INTO event_attendees VALUES (#{attendeeId}, #{eventId})
 """)
    void insertEventIdAndAttendeeId(Long eventId, Long attendeeId);

    @ResultMap("attendeeMapper")
    @Delete("""
    DELETE FROM event_attendees WHERE event_id = #{eventId}
""")
    void deleteByEventId(Long eventId);
}
