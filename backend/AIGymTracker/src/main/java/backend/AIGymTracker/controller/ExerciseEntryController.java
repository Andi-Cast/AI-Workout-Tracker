package backend.AIGymTracker.controller;

import backend.AIGymTracker.dto.ExerciseEntryRequest;
import backend.AIGymTracker.entity.ExerciseEntry;
import backend.AIGymTracker.service.AuthorizationService;
import backend.AIGymTracker.service.ExerciseEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise-entries")
@RequiredArgsConstructor
public class ExerciseEntryController {
    private final ExerciseEntryService exerciseEntryService;
    private final AuthorizationService authorizationService;

    @PostMapping
    public ResponseEntity<ExerciseEntry> save(@Valid @RequestBody ExerciseEntryRequest request) {
        authorizationService.validateWorkoutLogAccess(request.getWorkoutLogId());
        return ResponseEntity.ok(exerciseEntryService.saveExerciseEntry(request));
    }

    @GetMapping("/workout-log/{workoutLogId}")
    public ResponseEntity<List<ExerciseEntry>> getByWorkoutLogId(@PathVariable Long workoutLogId) {
        authorizationService.validateWorkoutLogAccess(workoutLogId);
        return ResponseEntity.ok(exerciseEntryService.getExerciseEntriesByWorkoutLogId(workoutLogId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseEntry> getById(@PathVariable Long id) {
        authorizationService.validateExerciseEntryAccess(id);
        return ResponseEntity.ok(exerciseEntryService.getExerciseEntryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseEntry> update(@PathVariable Long id, @Valid @RequestBody ExerciseEntryRequest request) {
        authorizationService.validateExerciseEntryAccess(id);
        authorizationService.validateWorkoutLogAccess(request.getWorkoutLogId());
        
        // Get existing entry and update
        ExerciseEntry existingEntry = exerciseEntryService.getExerciseEntryById(id);
        existingEntry.setExerciseName(request.getExerciseName());
        existingEntry.setSets(request.getSets());
        existingEntry.setReps(request.getReps());
        existingEntry.setWeight(request.getWeight());
        
        return ResponseEntity.ok(exerciseEntryService.saveExerciseEntry(existingEntry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorizationService.validateExerciseEntryAccess(id);
        exerciseEntryService.deleteExerciseEntryById(id);
        return ResponseEntity.noContent().build();
    }
}
