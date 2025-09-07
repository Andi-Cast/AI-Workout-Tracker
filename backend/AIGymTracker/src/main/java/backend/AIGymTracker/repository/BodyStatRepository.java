package backend.AIGymTracker.repository;

import backend.AIGymTracker.entity.BodyStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BodyStatRepository extends JpaRepository<BodyStat, Long> {
    List<BodyStat> findAllByUserId(Long userId);
    List<BodyStat> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}
