package backend.AIGymTracker.service;

import backend.AIGymTracker.exceptions.BodyStatNotFoundException;
import backend.AIGymTracker.exceptions.ExerciseEntryNotFoundException;
import backend.AIGymTracker.exceptions.ForbiddenException;
import backend.AIGymTracker.exceptions.SleepLogNotFoundException;
import backend.AIGymTracker.exceptions.UnauthorizedException;
import backend.AIGymTracker.exceptions.WorkoutFeedbackNotFoundException;
import backend.AIGymTracker.exceptions.WorkoutLogNotFoundException;
import backend.AIGymTracker.repository.BodyStatRepository;
import backend.AIGymTracker.repository.ExerciseEntryRepository;
import backend.AIGymTracker.repository.SleepLogRepository;
import backend.AIGymTracker.repository.WorkoutFeedbackRepository;
import backend.AIGymTracker.repository.WorkoutLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final WorkoutLogRepository workoutLogRepository;
    private final WorkoutFeedbackRepository workoutFeedbackRepository;
    private final BodyStatRepository bodyStatRepository;
    private final SleepLogRepository sleepLogRepository;
    private final ExerciseEntryRepository exerciseEntryRepository;
    
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User is not authenticated");
        }
        
        try {
            return Long.parseLong(authentication.getName());
        } catch (NumberFormatException e) {
            throw new UnauthorizedException("Invalid user authentication");
        }
    }
    
    public void validateUserAccess(Long requestedUserId) {
        Long currentUserId = getCurrentUserId();
        if (!currentUserId.equals(requestedUserId)) {
            throw new ForbiddenException("Access denied: You can only access your own data");
        }
    }
    
    public void validateUserAccess(Long requestedUserId, String resourceType) {
        Long currentUserId = getCurrentUserId();
        if (!currentUserId.equals(requestedUserId)) {
            throw new ForbiddenException("Access denied: You can only access your own " + resourceType);
        }
    }
    
    public void validateWorkoutLogAccess(Long workoutLogId) {
        Long currentUserId = getCurrentUserId();
        var workoutLog = workoutLogRepository.findById(workoutLogId)
            .orElseThrow(() -> new WorkoutLogNotFoundException("Workout log with ID " + workoutLogId + " not found"));
            
        if (!currentUserId.equals(workoutLog.getUser().getId())) {
            throw new ForbiddenException("Access denied: You can only access your own workout logs");
        }
    }
    
    public void validateWorkoutFeedbackAccess(Long feedbackId) {
        Long currentUserId = getCurrentUserId();
        var feedback = workoutFeedbackRepository.findById(feedbackId)
            .orElseThrow(() -> new WorkoutFeedbackNotFoundException("Workout feedback with ID " + feedbackId + " not found"));
            
        if (!currentUserId.equals(feedback.getWorkoutLog().getUser().getId())) {
            throw new ForbiddenException("Access denied: You can only access your own workout feedback");
        }
    }
    
    public void validateBodyStatAccess(Long bodyStatId) {
        Long currentUserId = getCurrentUserId();
        var bodyStat = bodyStatRepository.findById(bodyStatId)
            .orElseThrow(() -> new BodyStatNotFoundException(bodyStatId));
            
        if (!currentUserId.equals(bodyStat.getUser().getId())) {
            throw new ForbiddenException("Access denied: You can only access your own body stats");
        }
    }
    
    public void validateSleepLogAccess(Long sleepLogId) {
        Long currentUserId = getCurrentUserId();
        var sleepLog = sleepLogRepository.findById(sleepLogId)
            .orElseThrow(() -> new SleepLogNotFoundException(sleepLogId));
            
        if (!currentUserId.equals(sleepLog.getUser().getId())) {
            throw new ForbiddenException("Access denied: You can only access your own sleep logs");
        }
    }
    
    public void validateExerciseEntryAccess(Long exerciseEntryId) {
        Long currentUserId = getCurrentUserId();
        var exerciseEntry = exerciseEntryRepository.findById(exerciseEntryId)
            .orElseThrow(() -> new ExerciseEntryNotFoundException(exerciseEntryId));
            
        if (!currentUserId.equals(exerciseEntry.getWorkoutLog().getUser().getId())) {
            throw new ForbiddenException("Access denied: You can only access your own exercise entries");
        }
    }
}