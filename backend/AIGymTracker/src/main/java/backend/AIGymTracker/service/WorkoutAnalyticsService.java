package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.WorkoutContextDto;
import backend.AIGymTracker.entity.*;
import backend.AIGymTracker.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutAnalyticsService {
    
    private final WorkoutLogRepository workoutLogRepository;
    private final ExerciseEntryRepository exerciseEntryRepository;
    private final WorkoutFeedbackRepository workoutFeedbackRepository;
    private final UserRepository userRepository;
    
    public WorkoutContextDto buildWorkoutContext(Long workoutLogId) {
        WorkoutLog workoutLog = workoutLogRepository.findById(workoutLogId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));
        
        User user = workoutLog.getUser();
        
        List<ExerciseEntry> currentExercises = exerciseEntryRepository
                .findByWorkoutLog(workoutLog);
        
        Optional<WorkoutFeedback> feedback = workoutFeedbackRepository
                .findByWorkoutLog(workoutLog);
        
        Map<String, WorkoutContextDto.ExerciseProgressDto> exerciseProgress = 
                buildExerciseProgress(currentExercises, user.getId());
        
        return WorkoutContextDto.builder()
                .workoutLogId(workoutLogId)
                .workoutDate(workoutLog.getDate())
                .workoutNotes(workoutLog.getNotes())
                .currentExercises(mapToExerciseContextDto(currentExercises))
                .feedback(mapToWorkoutFeedbackDto(feedback))
                .userGoal(user.getGoalType())
                .exerciseProgress(exerciseProgress)
                .build();
    }
    
    private List<WorkoutContextDto.ExerciseContextDto> mapToExerciseContextDto(
            List<ExerciseEntry> exercises) {
        return exercises.stream()
                .map(exercise -> WorkoutContextDto.ExerciseContextDto.builder()
                        .exerciseName(exercise.getExerciseName())
                        .sets(exercise.getSets())
                        .reps(exercise.getReps())
                        .weight(exercise.getWeight())
                        .build())
                .collect(Collectors.toList());
    }
    
    private WorkoutContextDto.WorkoutFeedbackDto mapToWorkoutFeedbackDto(
            Optional<WorkoutFeedback> feedback) {
        return feedback.map(fb -> WorkoutContextDto.WorkoutFeedbackDto.builder()
                        .energyLevel(fb.getEnergyLevel())
                        .mood(fb.getMood())
                        .difficulty(fb.getDifficulty())
                        .notes(fb.getNotes())
                        .build())
                .orElse(null);
    }
    
    private Map<String, WorkoutContextDto.ExerciseProgressDto> buildExerciseProgress(
            List<ExerciseEntry> currentExercises, Long userId) {
        
        Map<String, WorkoutContextDto.ExerciseProgressDto> progressMap = new HashMap<>();
        LocalDate cutoffDate = LocalDate.now().minusDays(30);
        
        for (ExerciseEntry current : currentExercises) {
            String exerciseName = current.getExerciseName();
            
            Optional<ExerciseEntry> previousEntry = findMostRecentPreviousExercise(
                    exerciseName, userId, current.getWorkoutLog().getDate(), cutoffDate);
            
            if (previousEntry.isPresent()) {
                ExerciseEntry previous = previousEntry.get();
                WorkoutContextDto.ExerciseProgressDto progress = calculateProgress(current, previous);
                progressMap.put(exerciseName, progress);
            }
        }
        
        return progressMap;
    }
    
    private Optional<ExerciseEntry> findMostRecentPreviousExercise(
            String exerciseName, Long userId, LocalDate currentDate, LocalDate cutoffDate) {
        
        List<WorkoutLog> recentWorkouts = workoutLogRepository
                .findByUser_IdAndDateBetweenOrderByDateDesc(userId, cutoffDate, currentDate.minusDays(1));
        
        for (WorkoutLog workout : recentWorkouts) {
            List<ExerciseEntry> entries = exerciseEntryRepository.findByWorkoutLog(workout);
            Optional<ExerciseEntry> match = entries.stream()
                    .filter(entry -> exerciseName.equalsIgnoreCase(entry.getExerciseName()))
                    .findFirst();
            
            if (match.isPresent()) {
                return match;
            }
        }
        
        return Optional.empty();
    }
    
    private WorkoutContextDto.ExerciseProgressDto calculateProgress(
            ExerciseEntry current, ExerciseEntry previous) {
        
        BigDecimal weightImprovement = current.getWeight() != null && previous.getWeight() != null
                ? current.getWeight().subtract(previous.getWeight())
                : BigDecimal.ZERO;
        
        Integer repImprovement = current.getReps() != null && previous.getReps() != null
                ? current.getReps() - previous.getReps()
                : 0;
        
        long daysBetween = ChronoUnit.DAYS.between(
                previous.getWorkoutLog().getDate(),
                current.getWorkoutLog().getDate());
        
        return WorkoutContextDto.ExerciseProgressDto.builder()
                .exerciseName(current.getExerciseName())
                .previousWeight(previous.getWeight())
                .previousReps(previous.getReps())
                .currentWeight(current.getWeight())
                .currentReps(current.getReps())
                .weightImprovement(weightImprovement)
                .repImprovement(repImprovement)
                .previousWorkoutDate(previous.getWorkoutLog().getDate())
                .daysSinceLast((int) daysBetween)
                .build();
    }
}