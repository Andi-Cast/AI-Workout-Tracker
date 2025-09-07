package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.WorkoutLogRequest;
import backend.AIGymTracker.dto.WorkoutLogResponse;
import backend.AIGymTracker.entity.WorkoutLog;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutLogService {
    WorkoutLogResponse saveWorkoutLog(WorkoutLogRequest request);
    List<WorkoutLogResponse> getWorkoutLogsByUserId (Long userId);
    List<WorkoutLogResponse> getWorkoutLogsByUserIdAndBetween(Long userId, LocalDate start, LocalDate end);
    WorkoutLogResponse getWorkoutLogById(Long id);
    void deleteWorkoutLog(Long id);
}
