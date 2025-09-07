package backend.AIGymTracker.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExerciseEntryRequest {
    @NotNull(message = "Workout log ID is required")
    private Long workoutLogId;
    
    @NotBlank(message = "Exercise name is required")
    @Size(max = 100, message = "Exercise name cannot exceed 100 characters")
    private String exerciseName;
    
    @NotNull(message = "Sets is required")
    @Min(value = 1, message = "Sets must be at least 1")
    @Max(value = 50, message = "Sets cannot exceed 50")
    private Integer sets;
    
    @NotNull(message = "Reps is required")
    @Min(value = 1, message = "Reps must be at least 1")
    @Max(value = 1000, message = "Reps cannot exceed 1000")
    private Integer reps;
    
    @DecimalMin(value = "0.0", message = "Weight must be non-negative")
    @DecimalMax(value = "1000.0", message = "Weight cannot exceed 1000kg")
    @Digits(integer = 4, fraction = 2, message = "Weight must have at most 4 integer digits and 2 decimal places")
    private BigDecimal weight;
}