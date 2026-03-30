package org.ksga._10_mouk_makara_pvh_spring_homework003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.entity.Attendee;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.AttendeeRequest;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.request.AttendeeUpdateRequest;
import org.ksga._10_mouk_makara_pvh_spring_homework003.model.response.ApiResponse;
import org.ksga._10_mouk_makara_pvh_spring_homework003.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(@Positive @RequestParam(defaultValue = "1")  Long page,
                                                                       @Positive @RequestParam(defaultValue = "10")  Long size){
        List<Attendee> attendees = attendeeService.getAllAttendees(page, size);
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .timestamp(Instant.now())
                .message("Retrieved attendees successfully")
                .status(HttpStatus.OK)
                .payload(attendees)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@Positive @PathVariable("attendee-id") Long attendeeId) {
        Attendee attendee = attendeeService.getAttendeeById(attendeeId);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .timestamp(Instant.now())
                .message("Retrieved attendee successfully")
                .status(HttpStatus.OK)
                .payload(attendee)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> createAttendee(@Valid @RequestBody AttendeeRequest request){
        Attendee attendee = attendeeService.createAttendee(request);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .timestamp(Instant.now())
                .message("Created attendee successfully")
                .status(HttpStatus.CREATED)
                .payload(attendee)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> deleteAttendeeById(@Positive @PathVariable("attendee-id") Long attendeeId) {
        attendeeService.deleteAttendeeById(attendeeId);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .timestamp(Instant.now())
                .message("Deleted attendee with id " + attendeeId + " successfully")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendeeById(@Positive @PathVariable("attendee-id") Long attendeeId, @Valid @RequestBody AttendeeUpdateRequest request) {
        Attendee attendee = attendeeService.updateAttendeeById(attendeeId, request);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .timestamp(Instant.now())
                .message("Updated attendee with id " + attendeeId + " successfully")
                .status(HttpStatus.OK)
                .payload(attendee)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
