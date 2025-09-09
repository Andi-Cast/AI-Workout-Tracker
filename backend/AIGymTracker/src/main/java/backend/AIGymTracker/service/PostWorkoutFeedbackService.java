package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.WorkoutContextDto;
import backend.AIGymTracker.entity.WorkoutInsight;
import backend.AIGymTracker.entity.WorkoutLog;
import backend.AIGymTracker.repository.WorkoutInsightRepository;
import backend.AIGymTracker.repository.WorkoutLogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostWorkoutFeedbackService {
    
    private final WorkoutAnalyticsService workoutAnalyticsService;
    private final BedrockService bedrockService;
    private final WorkoutInsightRepository workoutInsightRepository;
    private final WorkoutLogRepository workoutLogRepository;
    private final ObjectMapper objectMapper;
    
    public String generatePostWorkoutFeedback(Long workoutLogId) {
        
        WorkoutLog workoutLog = workoutLogRepository.findById(workoutLogId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));
        
        Optional<WorkoutInsight> existingInsight = workoutInsightRepository
                .findByWorkoutLog(workoutLog);
        
        if (existingInsight.isPresent()) {
            // Update existing insight with new feedback
            WorkoutInsight insight = existingInsight.get();
            WorkoutContextDto context = workoutAnalyticsService.buildWorkoutContext(workoutLogId);
            String prompt = buildPostWorkoutPrompt(context);
            
            try {
                String aiResponse = bedrockService.generateWorkoutFeedback(prompt);
                insight.setGeneratedFeedback(aiResponse);
                insight.setContextData(objectMapper.writeValueAsString(context));
                workoutInsightRepository.save(insight);
                return aiResponse;
            } catch (Exception e) {
                log.error("Error updating post-workout feedback for workout {}: {}", workoutLogId, e.getMessage());
                return insight.getGeneratedFeedback(); // Return existing if update fails
            }
        }
        
        try {
            log.info("Building workout context for workout {}", workoutLogId);
            WorkoutContextDto context = workoutAnalyticsService.buildWorkoutContext(workoutLogId);
            log.info("Built context with {} exercises", context.getCurrentExercises() != null ? context.getCurrentExercises().size() : 0);
            
            String prompt = buildPostWorkoutPrompt(context);
            log.info("Generated prompt of length: {}", prompt.length());
            
            String aiResponse = bedrockService.generateWorkoutFeedback(prompt);
            log.info("Received AI response of length: {}", aiResponse.length());
            
            saveWorkoutInsight(workoutLogId, aiResponse, context);
            
            return aiResponse;
            
        } catch (Exception e) {
            log.error("Error generating post-workout feedback for workout {}: {}", workoutLogId, e.getMessage(), e);
            return "Unable to generate feedback at this time. Please try again later.";
        }
    }
    
    private String buildPostWorkoutPrompt(WorkoutContextDto context) {
        StringBuilder prompt = new StringBuilder();
        
        prompt.append("Generate encouraging post-workout feedback for this fitness session:\n\n");
        
        // Workout summary
        prompt.append("WORKOUT COMPLETED:\n");
        if (context.getCurrentExercises() != null && !context.getCurrentExercises().isEmpty()) {
            for (WorkoutContextDto.ExerciseContextDto exercise : context.getCurrentExercises()) {
                prompt.append("- ").append(exercise.getExerciseName())
                      .append(": ").append(exercise.getSets()).append(" sets x ")
                      .append(exercise.getReps()).append(" reps");
                if (exercise.getWeight() != null && exercise.getWeight().compareTo(BigDecimal.ZERO) > 0) {
                    prompt.append(" @ ").append(exercise.getWeight()).append(" lbs");
                }
                prompt.append("\n");
            }
        }
        
        // User context
        if (context.getUserGoal() != null) {
            prompt.append("\nUSER GOAL: ").append(context.getUserGoal().toString()).append("\n");
        }
        
        // Progress analysis
        if (context.getExerciseProgress() != null && !context.getExerciseProgress().isEmpty()) {
            prompt.append("\nPROGRESS ANALYSIS:\n");
            for (Map.Entry<String, WorkoutContextDto.ExerciseProgressDto> entry : context.getExerciseProgress().entrySet()) {
                WorkoutContextDto.ExerciseProgressDto progress = entry.getValue();
                prompt.append("- ").append(progress.getExerciseName()).append(": ");
                
                if (progress.getWeightImprovement().compareTo(BigDecimal.ZERO) > 0) {
                    prompt.append("+").append(progress.getWeightImprovement()).append(" lbs improvement");
                } else if (progress.getRepImprovement() > 0) {
                    prompt.append("+").append(progress.getRepImprovement()).append(" reps improvement");
                } else {
                    prompt.append("maintained from last session ").append(progress.getDaysSinceLast()).append(" days ago");
                }
                prompt.append("\n");
            }
        }
        
        // Subjective feedback
        if (context.getFeedback() != null) {
            prompt.append("\nSUBJECTIVE FEEDBACK:\n");
            if (context.getFeedback().getEnergyLevel() != null) {
                prompt.append("- Energy Level: ").append(context.getFeedback().getEnergyLevel()).append("/10\n");
            }
            if (context.getFeedback().getMood() != null) {
                prompt.append("- Mood: ").append(context.getFeedback().getMood()).append("/10\n");
            }
            if (context.getFeedback().getDifficulty() != null) {
                prompt.append("- Difficulty: ").append(context.getFeedback().getDifficulty()).append("/10\n");
            }
            if (context.getFeedback().getNotes() != null && !context.getFeedback().getNotes().trim().isEmpty()) {
                prompt.append("- Notes: ").append(context.getFeedback().getNotes()).append("\n");
            }
        }
        
        // Instructions for AI
        prompt.append("\nProvide encouraging, personalized feedback that:\n");
        prompt.append("1. Celebrates specific achievements and progress\n");
        prompt.append("2. Addresses any concerning patterns (low energy, high difficulty)\n");
        prompt.append("3. Gives 1-2 specific suggestions for the next workout\n");
        prompt.append("4. Matches the user's fitness goals\n");
        prompt.append("5. Keeps the tone motivational but realistic\n");
        prompt.append("6. Limits response to 3-4 sentences\n");
        
        return prompt.toString();
    }
    
    private void saveWorkoutInsight(Long workoutLogId, String aiResponse, WorkoutContextDto context) {
        try {
            WorkoutLog workoutLog = workoutLogRepository.findById(workoutLogId)
                    .orElseThrow(() -> new RuntimeException("Workout not found"));
            
            String contextJson = objectMapper.writeValueAsString(context);
            
            WorkoutInsight insight = WorkoutInsight.builder()
                    .workoutLog(workoutLog)
                    .generatedFeedback(aiResponse)
                    .contextData(contextJson)
                    .build();
            
            workoutInsightRepository.save(insight);
            
        } catch (JsonProcessingException e) {
            log.error("Error serializing context data for workout {}: {}", workoutLogId, e.getMessage());
        }
    }
}