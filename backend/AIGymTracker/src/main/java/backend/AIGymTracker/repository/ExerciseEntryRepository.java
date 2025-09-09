package backend.AIGymTracker.repository;

import backend.AIGymTracker.entity.ExerciseEntry;
import backend.AIGymTracker.entity.WorkoutLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseEntryRepository extends JpaRepository<ExerciseEntry, Long> {
    List<ExerciseEntry> findByWorkoutLogId(Long workoutId);
    List<ExerciseEntry> findByWorkoutLog(WorkoutLog workoutLog);
}
