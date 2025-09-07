package backend.AIGymTracker.service;

import backend.AIGymTracker.entity.ExerciseEntry;
import backend.AIGymTracker.repository.ExerciseEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseEntryServiceImpl implements ExerciseEntryService {
    private final ExerciseEntryRepository exerciseEntryRepository;

    @Override
    public ExerciseEntry saveExerciseEntry(ExerciseEntry exerciseEntry) {
        return exerciseEntryRepository.save(exerciseEntry);
    }

    @Override
    public List<ExerciseEntry> getExerciseEntriesByWorkoutLogId(Long workoutLogId) {
        return exerciseEntryRepository.findByWorkoutLogId(workoutLogId);
    }

    @Override
    public ExerciseEntry getExerciseEntryById(Long id) {
        return exerciseEntryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Exercise entry not found"));
    }

    @Override
    public void deleteExerciseEntryById(Long id) {
        exerciseEntryRepository.deleteById(id);
    }
}
