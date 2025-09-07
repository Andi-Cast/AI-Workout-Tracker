package backend.AIGymTracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkoutLogResponse {
    private Long id;
    private String notes;
    private Long userId;
    private LocalDate date;
    private LocalDate createdAt;

}
