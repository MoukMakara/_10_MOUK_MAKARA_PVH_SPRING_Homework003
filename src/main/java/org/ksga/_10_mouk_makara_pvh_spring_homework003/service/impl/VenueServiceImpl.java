package org.ksga._10_mouk_makara_pvh_spring_homework003.service.impl;

import org.ksga._10_mouk_makara_pvh_spring_homework003.exception.ConflictException;
import org.ksga._10_mouk_makara_pvh_spring_homework003.exception.NotFoundException;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Venue;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.VenueRequest;
import org.ksga._10_mouk_makara_pvh_spring_homework003.repository.VenueRepository;
import org.ksga._10_mouk_makara_pvh_spring_homework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService  {
    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getAllVenues(Long page, Long size) {
        Long offset = (page - 1) * size;
        return venueRepository.getAllVenues(offset, size);
    }

    @Override
    public Venue createVenue(VenueRequest request) {
        String requestedName = request.getVenueName();
        Venue venueName = venueRepository.findVenueByName(requestedName);
        if (venueName != null) {
            throw new ConflictException("Venue name already exists");
        }

        return venueRepository.createVenue(request);
    }

    @Override
    public Venue getVenueById(Long venueId) {
        Venue venue = venueRepository.getVenueById(venueId);
        if (venue == null){
            throw new NotFoundException("Venue with id " + venueId + " not found");

        }
        return venueRepository.getVenueById(venueId);
    }

    @Override
    public Venue deleteVenueById(Long venueId) {
        Venue venue = venueRepository.getVenueById(venueId);
        if (venue == null){
            throw new NotFoundException("Venue with id " + venueId + " not found");
        }

        return venueRepository.deleteVenueById(venueId);
    }

    @Override
    public Venue updateVenueById(Long venueId, VenueRequest request) {
        Venue venue = venueRepository.getVenueById(venueId);
        if(venue == null){
            throw new NotFoundException("Venue with id " + venueId + " not found");
        }
        String requestedName = request.getVenueName();
        Venue existingVenue = venueRepository.findVenueByName(requestedName);
        if (existingVenue != null) {
            throw new ConflictException("Venue name already exists");
        }

        return venueRepository.updateVenueById(venueId, request);
    }
}
