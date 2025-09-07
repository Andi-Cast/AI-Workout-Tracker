package backend.AIGymTracker.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 25, message = "Password must be between 6 to 25 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    private String password;

    @NotBlank(message = "Firstname is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String firstname;

    @NotBlank(message = "Lastname is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String lastname;

    @NotNull(message = "Age is required")
    @Min(value = 16, message = "Must be at least 16 years old")
    private Integer age;

    @NotNull(message = "Goal type is required")
    private String goalType;

}
