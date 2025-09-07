package backend.AIGymTracker.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WorkoutFeedbackRequest {
    @NotNull(message = "Workout log ID is required")
    private Long workoutLogId;
    
    @NotNull(message = "Energy level is required")
    @Min(value = 1, message = "Energy level must be between 1 and 10")
    @Max(value = 10, message = "Energy level must be between 1 and 10")
    private Integer energyLevel;
    
    @NotNull(message = "Mood is required")
    @Min(value = 1, message = "Mood must be between 1 and 10")
    @Max(value = 10, message = "Mood must be between 1 and 10")
    private Integer mood;
    
    @NotNull(message = "Difficulty is required")
    @Min(value = 1, message = "Difficulty must be between 1 and 10")
    @Max(value = 10, message = "Difficulty must be between 1 and 10")
    private Integer difficulty;
    
    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    private String notes;
}
