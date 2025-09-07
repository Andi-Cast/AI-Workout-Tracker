package backend.AIGymTracker.repository;

import backend.AIGymTracker.entity.SleepLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SleepLogRepository extends JpaRepository<SleepLog, Long> {
    List<SleepLog> findByUserId(Long userId);
    List<SleepLog> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}
