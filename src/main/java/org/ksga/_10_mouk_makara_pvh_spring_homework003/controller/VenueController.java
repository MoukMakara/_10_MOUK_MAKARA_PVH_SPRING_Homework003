package org.ksga._10_mouk_makara_pvh_spring_homework003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Venue;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.VenueRequest;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.response.ApiResponse;
import org.ksga._10_mouk_makara_pvh_spring_homework003.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(@RequestParam(defaultValue = "1") @Positive Long page,
                                                    @RequestParam(defaultValue = "10") @Positive Long size) {
        List<Venue> venues = venueService.getAllVenues(page, size);
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .timestamp(Instant.now())
                .message("Venues retrieved successfully")
                .status(HttpStatus.OK)
                .payload(venues)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> createVenue(@RequestBody @Valid VenueRequest request){
        Venue venue = venueService.createVenue(request);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder().
                timestamp(Instant.now())
                .message("Venue created successfully")
                .status(HttpStatus.CREATED)
                .payload(venue)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id") @Positive Long venueId) {


        Venue venue = venueService.getVenueById(venueId);

            ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                    .timestamp(Instant.now())
                    .message("Retrieved venue with id " + venueId + " successfully")
                    .status(HttpStatus.OK)
                    .payload(venue)
                    .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> deleteVenueById(@PathVariable("venue-id") @Positive Long venueId) {
        venueService.deleteVenueById(venueId);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .timestamp(Instant.now())
                .message("Deleted venue with id " + venueId + " successfully")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PutMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> updateVenueById(@PathVariable("venue-id") @Positive Long venueId, @RequestBody @Valid VenueRequest request) {
        Venue venue = venueService.updateVenueById(venueId, request);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .timestamp(Instant.now())
                .message("Updated venue with id " + venueId + " successfully")
                .status(HttpStatus.OK)
                .payload(venue)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
