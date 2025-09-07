package backend.AIGymTracker.service;

import backend.AIGymTracker.exceptions.ForbiddenException;
import backend.AIGymTracker.exceptions.UnauthorizedException;
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
            .orElseThrow(() -> new ForbiddenException("Workout log not found"));
            
        if (!currentUserId.equals(workoutLog.getUser().getId())) {
            throw new ForbiddenException("Access denied: You can only access your own workout logs");
        }
    }
    
    public void validateWorkoutFeedbackAccess(Long feedbackId) {
        Long currentUserId = getCurrentUserId();
        var feedback = workoutFeedbackRepository.findById(feedbackId)
            .orElseThrow(() -> new ForbiddenException("Workout feedback not found"));
            
        if (!currentUserId.equals(feedback.getWorkoutLog().getUser().getId())) {
            throw new ForbiddenException("Access denied: You can only access your own workout feedback");
        }
    }
}