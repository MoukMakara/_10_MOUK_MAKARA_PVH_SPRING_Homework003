package org.ksga._10_mouk_makara_pvh_spring_homework003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Event;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Venue;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.EventRequest;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.response.ApiResponse;
import org.ksga._10_mouk_makara_pvh_spring_homework003.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(@RequestParam(defaultValue = "1") @Positive Long page,
                                                     @RequestParam(defaultValue = "10") @Positive Long size) {
        List<Event> events = eventService.getAllEvents(page, size);
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .timestamp(Instant.now())
                .message("Events retrieved successfully")
                .payload(events)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@Positive @PathVariable("event-id") Long eventId) {
        Event event = eventService.getEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .timestamp(Instant.now())
                .message("Event retrieved successfully")
                .payload(event)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>> createEvent(@Valid @RequestBody EventRequest request) {
        Event event = eventService.createEvent(request);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .timestamp(Instant.now())
                .message("Event created successfully")
                .payload(event)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> deleteEvent(@Positive @PathVariable("event-id") Long eventId) {
        eventService.deleteEventById(eventId);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .timestamp(Instant.now())
                .message("Event deleted successfully")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> updateEventById(@Positive @PathVariable("event-id") Long eventId, @Valid @RequestBody EventRequest request) {
        Event event = eventService.updateEventById(eventId, request);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .timestamp(Instant.now())
                .message("Event updated successfully")
                .status(HttpStatus.OK)
                .payload(event)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
