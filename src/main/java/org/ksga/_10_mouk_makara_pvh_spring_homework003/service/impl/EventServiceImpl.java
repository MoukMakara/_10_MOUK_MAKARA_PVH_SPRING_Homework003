package org.ksga._10_mouk_makara_pvh_spring_homework003.service.impl;
import org.ksga._10_mouk_makara_pvh_spring_homework003.exception.ConflictException;
import org.ksga._10_mouk_makara_pvh_spring_homework003.exception.NotFoundException;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Event;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.EventRequest;
import org.ksga._10_mouk_makara_pvh_spring_homework003.repository.EventAttendeeRepository;
import org.ksga._10_mouk_makara_pvh_spring_homework003.repository.EventRepository;
import org.ksga._10_mouk_makara_pvh_spring_homework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventAttendeeRepository eventAttendeeRepository;

    public EventServiceImpl(EventRepository eventRepository, EventAttendeeRepository eventAttendeeRepository) {
        this.eventRepository = eventRepository;
        this.eventAttendeeRepository = eventAttendeeRepository;
    }

    @Override
    public List<Event> getAllEvents(Long page, Long size) {
        Long offset = (page - 1) * size;
        return eventRepository.getAllEvents(offset, size);
    }

    @Override
    public Event getEventById(Long eventId) {
        Event event = eventRepository.getEventById(eventId);
        if(event == null){
            throw new NotFoundException("Event with ID " + eventId + " not found");
        }
        return event;
    }


    @Override
    public Event createEvent(EventRequest request) {
        String requestedName = request.getEventName();
        Event eventName = eventRepository.findEventByName(requestedName);
        if (eventName != null) {
            throw new ConflictException("Event name already exists");
        }

        Event event = eventRepository.createEvent(request);

        for(Long attendeeId : request.getAttendeeIds()){
            eventAttendeeRepository.insertEventIdAndAttendeeId(event.getEventId(), attendeeId);
        }
        return eventRepository.getEventById(event.getEventId());
    }

    @Override
    public Event deleteEventById(Long eventId) {
        Event event = eventRepository.getEventById(eventId);
        if(event == null){
            throw new NotFoundException("Event with ID " + eventId + " not found");
        }

        return eventRepository.deleteEventById(eventId);
    }

    @Override
    public Event updateEventById(Long eventId, EventRequest request) {
        Event event = eventRepository.getEventById(eventId);
        if(event == null){
            throw new NotFoundException("Event with ID " + eventId + " not found");
        }

        String requestedName = request.getEventName();
        Event eventName = eventRepository.findEventByName(requestedName);
        if (eventName != null && !eventName.getEventId().equals(eventId)) {
            throw new ConflictException("Event name already exists");
        }

        eventRepository.updateEventById(eventId, request);
        eventAttendeeRepository.deleteByEventId(eventId);
        for(Long attendeeId : request.getAttendeeIds()){
            eventAttendeeRepository.insertEventIdAndAttendeeId(eventId, attendeeId);
        }
        return eventRepository.getEventById(eventId);
    }
}
