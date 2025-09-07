package backend.AIGymTracker.repository;

import backend.AIGymTracker.entity.WorkoutFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutFeedbackRepository extends JpaRepository<WorkoutFeedback, Long> {
    Optional<WorkoutFeedback> findByWorkoutLogId(Long id);
}
