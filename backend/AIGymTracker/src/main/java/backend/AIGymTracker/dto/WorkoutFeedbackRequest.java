package backend.AIGymTracker.dto;

import lombok.Data;

@Data
public class WorkoutFeedbackRequest {
    private Long workoutLogId;
    private Integer energyLevel;
    private Integer mood;
    private Integer difficulty;
    private String notes;
}
