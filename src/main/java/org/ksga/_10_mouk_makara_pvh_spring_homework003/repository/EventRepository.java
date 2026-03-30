package org.ksga._10_mouk_makara_pvh_spring_homework003.repository;

import org.apache.ibatis.annotations.*;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Event;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.EventRequest;

import java.util.List;

@Mapper
public interface EventRepository {
    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id", one = @One(select = "org.ksga._10_mouk_makara_pvh_spring_homework003.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id", many = @Many(select = "org.ksga._10_mouk_makara_pvh_spring_homework003.repository.EventAttendeeRepository.getAttendeeIdByEventId"))
    })
    @Select("""
            SELECT * FROM events OFFSET #{offset} LIMIT #{size}
""")
    List<Event> getAllEvents(Long offset, Long size);

    @ResultMap("eventMapper")
    @Select("""
    SELECT * FROM events WHERE event_id = #{eventId}
""")
    Event getEventById(Long eventId);

    @ResultMap("eventMapper")
    @Select("""
    INSERT INTO events VALUES (default, #{req.eventName}, #{req.eventDate}, #{req.venueId}) RETURNING *
""")
    Event createEvent(@Param("req") EventRequest request);

    @ResultMap("eventMapper")
    @Select("""
    SELECT * FROM events WHERE event_name = #{name} LIMIT 1
""")
    Event findEventByName(String requestedName);

    @ResultMap("eventMapper")
    @Select("""
    DELETE FROM events WHERE event_id = #{eventId}
""")
    Event deleteEventById(Long eventId);

    @ResultMap("eventMapper")
    @Select("""
    UPDATE events SET event_name = #{req.eventName}, event_date = #{req.eventDate}, venue_id = #{req.venueId} WHERE event_id = #{eventId} RETURNING *
""")
    Event updateEventById(Long eventId,@Param("req") EventRequest request);
}
