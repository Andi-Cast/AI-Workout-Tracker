package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.ExerciseEntryRequest;
import backend.AIGymTracker.entity.ExerciseEntry;

import java.util.List;

public interface ExerciseEntryService {
    ExerciseEntry saveExerciseEntry(ExerciseEntry entry);
    ExerciseEntry saveExerciseEntry(ExerciseEntryRequest request);
    List<ExerciseEntry> getExerciseEntriesByWorkoutLogId(Long workoutLogId);
    ExerciseEntry getExerciseEntryById(Long id);
    void deleteExerciseEntryById(Long id);

}
