package org.ksga._10_mouk_makara_pvh_spring_homework003.model.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    @NotBlank(message = "Attendee cannot be blank")
    @Size(min = 1, max = 50, message = "Attendee name must be between 1 and 50 characters")
    private String attendeeName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@gmail\\.com$",
            message = "Email must be (example: email@gmail.com)"
    )
    @Schema(example = "email@gmail.com")
    private String email;
}
