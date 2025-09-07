package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.ExerciseEntryRequest;
import backend.AIGymTracker.entity.ExerciseEntry;
import backend.AIGymTracker.entity.WorkoutLog;
import backend.AIGymTracker.exceptions.ExerciseEntryNotFoundException;
import backend.AIGymTracker.exceptions.WorkoutLogNotFoundException;
import backend.AIGymTracker.repository.ExerciseEntryRepository;
import backend.AIGymTracker.repository.WorkoutLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseEntryServiceImpl implements ExerciseEntryService {
    private final ExerciseEntryRepository exerciseEntryRepository;
    private final WorkoutLogRepository workoutLogRepository;

    @Override
    public ExerciseEntry saveExerciseEntry(ExerciseEntry exerciseEntry) {
        return exerciseEntryRepository.save(exerciseEntry);
    }

    @Override
    public ExerciseEntry saveExerciseEntry(ExerciseEntryRequest request) {
        WorkoutLog workoutLog = workoutLogRepository.findById(request.getWorkoutLogId())
            .orElseThrow(() -> new WorkoutLogNotFoundException("Workout log with ID " + request.getWorkoutLogId() + " not found"));
        
        ExerciseEntry exerciseEntry = new ExerciseEntry();
        exerciseEntry.setWorkoutLog(workoutLog);
        exerciseEntry.setExerciseName(request.getExerciseName());
        exerciseEntry.setSets(request.getSets());
        exerciseEntry.setReps(request.getReps());
        exerciseEntry.setWeight(request.getWeight());
        
        return exerciseEntryRepository.save(exerciseEntry);
    }

    @Override
    public List<ExerciseEntry> getExerciseEntriesByWorkoutLogId(Long workoutLogId) {
        return exerciseEntryRepository.findByWorkoutLogId(workoutLogId);
    }

    @Override
    public ExerciseEntry getExerciseEntryById(Long id) {
        return exerciseEntryRepository.findById(id)
                        .orElseThrow(() -> new ExerciseEntryNotFoundException(id));
    }

    @Override
    public void deleteExerciseEntryById(Long id) {
        ExerciseEntry exerciseEntry = exerciseEntryRepository.findById(id)
            .orElseThrow(() -> new ExerciseEntryNotFoundException(id));
        exerciseEntryRepository.deleteById(id);
    }
}
