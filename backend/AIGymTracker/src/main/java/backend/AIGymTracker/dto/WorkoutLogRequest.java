package backend.AIGymTracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkoutLogRequest {
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;
    
    @Size(max = 1000, message = "Notes cannot exceed 1000 characters")
    private String notes;
}
