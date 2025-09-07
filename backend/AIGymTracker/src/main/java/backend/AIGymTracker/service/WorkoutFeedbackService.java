package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.WorkoutFeedbackRequest;
import backend.AIGymTracker.dto.WorkoutFeedbackResponse;
import backend.AIGymTracker.entity.WorkoutFeedback;

import java.util.Optional;

public interface WorkoutFeedbackService {
    WorkoutFeedbackResponse saveWorkoutFeedback(WorkoutFeedbackRequest request);
    WorkoutFeedbackResponse updateWorkoutFeedback(Long id, WorkoutFeedbackRequest request);
    WorkoutFeedbackResponse getFeedbackById(Long id);
    WorkoutFeedbackResponse getWorkoutFeedbackByWorkoutLogId(Long workoutLogId);
    void deleteWorkoutFeedbackById(Long id);
}
