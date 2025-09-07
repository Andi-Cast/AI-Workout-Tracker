package backend.AIGymTracker.controller;

import backend.AIGymTracker.dto.WorkoutLogRequest;
import backend.AIGymTracker.dto.WorkoutLogResponse;
import backend.AIGymTracker.entity.WorkoutLog;
import backend.AIGymTracker.service.WorkoutLogService;
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

    @PostMapping
    public ResponseEntity<WorkoutLogResponse> save(@RequestBody WorkoutLogRequest request) {
        return ResponseEntity.ok(workoutLogService.saveWorkoutLog(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutLogResponse>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(workoutLogService.getWorkoutLogsByUserId(userId));
    }

    @GetMapping("/user/{userId}/range")
    public ResponseEntity<List<WorkoutLogResponse>> getByUserIdAndDateRange(@PathVariable Long userId,
                                                                    @RequestParam LocalDate start,
                                                                    @RequestParam LocalDate end)
    {
        return ResponseEntity.ok(workoutLogService.getWorkoutLogsByUserIdAndBetween(userId, start, end));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutLogResponse > getWorkoutLogById(@PathVariable Long id) {
        return ResponseEntity.ok(workoutLogService.getWorkoutLogById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLogById(@PathVariable Long id) {
        workoutLogService.deleteWorkoutLog(id);
        return ResponseEntity.noContent().build();
    }
}
