package backend.AIGymTracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 25, message = "Password must be between 6 to 25 characters long")
    private String password;

    @NotBlank(message = "Firstname is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String firstName;

    @NotBlank(message = "Lastname is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String lastName;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotNull(message = "Goal type is required")
    private String goalType;

}
