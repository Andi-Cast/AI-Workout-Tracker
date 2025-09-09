package backend.AIGymTracker.dto;

import backend.AIGymTracker.entity.GoalType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutContextDto {
    
    private Long workoutLogId;
    private LocalDate workoutDate;
    private String workoutNotes;
    
    private List<ExerciseContextDto> currentExercises;
    
    private WorkoutFeedbackDto feedback;
    
    private GoalType userGoal;
    
    private Map<String, ExerciseProgressDto> exerciseProgress;
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ExerciseContextDto {
        private String exerciseName;
        private Integer sets;
        private Integer reps;
        private BigDecimal weight;
    }
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class WorkoutFeedbackDto {
        private Integer energyLevel;
        private Integer mood;
        private Integer difficulty;
        private String notes;
    }
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ExerciseProgressDto {
        private String exerciseName;
        private BigDecimal previousWeight;
        private Integer previousReps;
        private BigDecimal currentWeight;
        private Integer currentReps;
        private BigDecimal weightImprovement;
        private Integer repImprovement;
        private LocalDate previousWorkoutDate;
        private int daysSinceLast;
    }
}