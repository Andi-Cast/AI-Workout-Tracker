package backend.AIGymTracker.controller;

import backend.AIGymTracker.entity.ExerciseEntry;
import backend.AIGymTracker.service.ExerciseEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercise-entries")
@RequiredArgsConstructor
public class ExerciseEntryController {
    private final ExerciseEntryService exerciseEntryService;

    @PostMapping
    public ResponseEntity<ExerciseEntry> save(@RequestBody ExerciseEntry entry) {
        return ResponseEntity.ok(exerciseEntryService.saveExerciseEntry(entry));
    }

    @GetMapping("/workout-log/{workoutLogId}")
    public ResponseEntity<List<ExerciseEntry>> getByWorkoutLogId(@PathVariable Long workoutLogId) {
        return ResponseEntity.ok(exerciseEntryService.getExerciseEntriesByWorkoutLogId(workoutLogId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseEntry> getById(@PathVariable Long id) {
        return ResponseEntity.ok(exerciseEntryService.getExerciseEntryById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exerciseEntryService.deleteExerciseEntryById(id);
        return ResponseEntity.noContent().build();
    }



}
