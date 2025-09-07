package backend.AIGymTracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkoutLogRequest {
    private Long userId;
    private LocalDate date;
    private String notes;
}
