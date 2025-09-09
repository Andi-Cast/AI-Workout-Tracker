package backend.AIGymTracker.repository;

import backend.AIGymTracker.entity.WorkoutInsight;
import backend.AIGymTracker.entity.WorkoutLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutInsightRepository extends JpaRepository<WorkoutInsight, Long> {
    
    Optional<WorkoutInsight> findByWorkoutLog(WorkoutLog workoutLog);
    
    List<WorkoutInsight> findByWorkoutLog_User_IdOrderByCreatedAtDesc(Long userId);
    
    @Query("SELECT wi FROM WorkoutInsight wi WHERE wi.workoutLog.user.id = :userId AND wi.createdAt >= :startDate")
    List<WorkoutInsight> findRecentInsights(@Param("userId") Long userId, 
                                          @Param("startDate") java.time.LocalDateTime startDate);
}