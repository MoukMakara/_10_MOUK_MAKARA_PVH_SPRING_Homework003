package org.ksga._10_mouk_makara_pvh_spring_homework003.service.impl;

import org.ksga._10_mouk_makara_pvh_spring_homework003.exception.ConflictException;
import org.ksga._10_mouk_makara_pvh_spring_homework003.exception.NotFoundException;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Attendee;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Venue;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.AttendeeRequest;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.AttendeeUpdateRequest;
import org.ksga._10_mouk_makara_pvh_spring_homework003.repository.AttendeeRepository;
import org.ksga._10_mouk_makara_pvh_spring_homework003.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Attendee> getAllAttendees(Long page, Long size) {
        Long offset = size * (page - 1);
        return attendeeRepository.getAllAttendees(offset, size);
    }

    @Override
    public Attendee getAttendeeById(Long attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if(attendee == null){
            throw new NotFoundException("Attendee with id " + attendeeId + " not found");
        }
        return attendeeRepository.getAttendeeById(attendeeId);
    }

    @Override
    public Attendee createAttendee(AttendeeRequest request) {
        String requestAttendeeName = request.getAttendeeName();
        Attendee attendeeName = attendeeRepository.findAttendeeByName(requestAttendeeName);

        if(attendeeName != null){
            throw new ConflictException("Attendee name already exists");
        }

        String requestEmail = request.getEmail();
        Attendee attendeeEmail = attendeeRepository.findAttendeeByEmail(requestEmail);
        if(attendeeEmail != null){
            throw new ConflictException("Attendee email already exists");
        }
        return attendeeRepository.createAttendee(request);
    }

    @Override
    public Attendee deleteAttendeeById(Long attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if(attendee == null){
            throw new NotFoundException("Attendee with id " + attendeeId + " not found");
        }
        return attendeeRepository.deleteAttendeeById(attendeeId);
    }

    @Override
    public Attendee updateAttendeeById(Long attendeeId, AttendeeUpdateRequest request) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if(attendee == null){
            throw new NotFoundException("Attendee with id " + attendeeId + " not found");
        }

        String requestAttendeeName = request.getAttendeeName();
        Attendee existingAttendeeByName = attendeeRepository.findAttendeeByName(requestAttendeeName);
        if (existingAttendeeByName != null) {
            throw new ConflictException("Attendee name already exists");
        }

        return attendeeRepository.updateAttendeeById(attendeeId, request);
    }
}
