package org.ksga._10_mouk_makara_pvh_spring_homework003.service;

import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Venue;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.VenueRequest;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues(Long page, Long size);

    Venue createVenue(VenueRequest request);

    Venue getVenueById(Long venueId);

    Venue deleteVenueById(Long venueId);

    Venue updateVenueById(Long venueId, VenueRequest request);
}
