package backend.AIGymTracker.controller;

import backend.AIGymTracker.dto.WorkoutLogRequest;
import backend.AIGymTracker.dto.WorkoutLogResponse;
import backend.AIGymTracker.entity.WorkoutLog;
import backend.AIGymTracker.service.AuthorizationService;
import backend.AIGymTracker.service.WorkoutLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/workout-logs")
@RequiredArgsConstructor
public class WorkoutLogController {
    private final WorkoutLogService workoutLogService;
    private final AuthorizationService authorizationService;

    @PostMapping
    public ResponseEntity<WorkoutLogResponse> save(@Valid @RequestBody WorkoutLogRequest request) {
        authorizationService.validateUserAccess(request.getUserId(), "workout logs");
        return ResponseEntity.ok(workoutLogService.saveWorkoutLog(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutLogResponse>> getByUserId(@PathVariable Long userId) {
        authorizationService.validateUserAccess(userId, "workout logs");
        return ResponseEntity.ok(workoutLogService.getWorkoutLogsByUserId(userId));
    }

    @GetMapping("/user/{userId}/range")
    public ResponseEntity<List<WorkoutLogResponse>> getByUserIdAndDateRange(@PathVariable Long userId,
                                                                    @RequestParam LocalDate start,
                                                                    @RequestParam LocalDate end)
    {
        authorizationService.validateUserAccess(userId, "workout logs");
        return ResponseEntity.ok(workoutLogService.getWorkoutLogsByUserIdAndBetween(userId, start, end));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutLogResponse> getWorkoutLogById(@PathVariable Long id) {
        authorizationService.validateWorkoutLogAccess(id);
        return ResponseEntity.ok(workoutLogService.getWorkoutLogById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutLogResponse> updateWorkoutLog(@PathVariable Long id, @Valid @RequestBody WorkoutLogRequest request) {
        authorizationService.validateWorkoutLogAccess(id);
        authorizationService.validateUserAccess(request.getUserId(), "workout logs");
        
        return ResponseEntity.ok(workoutLogService.updateWorkoutLog(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogById(@PathVariable Long id) {
        authorizationService.validateWorkoutLogAccess(id);
        workoutLogService.deleteWorkoutLog(id);
        return ResponseEntity.noContent().build();
    }
}
