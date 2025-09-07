package backend.AIGymTracker.controller;

import backend.AIGymTracker.dto.WorkoutFeedbackRequest;
import backend.AIGymTracker.dto.WorkoutFeedbackResponse;
import backend.AIGymTracker.service.AuthorizationService;
import backend.AIGymTracker.service.WorkoutFeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/workout-feedback")
@RequiredArgsConstructor
public class WorkoutFeedbackController {
    private final WorkoutFeedbackService workoutFeedbackService;
    private final AuthorizationService authorizationService;

    @PostMapping
    public ResponseEntity<WorkoutFeedbackResponse> save(@Valid @RequestBody WorkoutFeedbackRequest request) {
        authorizationService.validateWorkoutLogAccess(request.getWorkoutLogId());
        return ResponseEntity.ok(workoutFeedbackService.saveWorkoutFeedback(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutFeedbackResponse> update(@PathVariable Long id, @Valid @RequestBody WorkoutFeedbackRequest request) {
        authorizationService.validateWorkoutLogAccess(request.getWorkoutLogId());
        return ResponseEntity.ok(workoutFeedbackService.updateWorkoutFeedback(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutFeedbackResponse> getById(@PathVariable Long id) {
        authorizationService.validateWorkoutFeedbackAccess(id);
        return ResponseEntity.ok(workoutFeedbackService.getFeedbackById(id));
    }

    @GetMapping("/log/{id}")
    public ResponseEntity<WorkoutFeedbackResponse> getByLogId(@PathVariable Long id) {
        authorizationService.validateWorkoutLogAccess(id);
        return ResponseEntity.ok(workoutFeedbackService.getWorkoutFeedbackByWorkoutLogId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorizationService.validateWorkoutFeedbackAccess(id);
        workoutFeedbackService.deleteWorkoutFeedbackById(id);
        return ResponseEntity.noContent().build();
    }
}
