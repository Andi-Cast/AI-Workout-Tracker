package backend.AIGymTracker.repository;

import backend.AIGymTracker.entity.WorkoutLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutLogRepository extends JpaRepository<WorkoutLog, Long> {
    List<WorkoutLog> findAllByUserId(Long userId);
    List<WorkoutLog> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
    List<WorkoutLog> findByUser_IdAndDateBetweenOrderByDateDesc(Long userId, LocalDate startDate, LocalDate endDate);

}
