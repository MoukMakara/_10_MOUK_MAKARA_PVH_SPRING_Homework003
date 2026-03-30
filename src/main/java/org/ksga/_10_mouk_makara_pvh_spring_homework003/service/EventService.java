package org.ksga._10_mouk_makara_pvh_spring_homework003.service;

import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Event;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Long page, Long size);

    Event getEventById(Long eventId);

    Event createEvent(EventRequest request);

    Event deleteEventById(Long eventId);

    Event updateEventById(Long eventId, EventRequest request);
}
