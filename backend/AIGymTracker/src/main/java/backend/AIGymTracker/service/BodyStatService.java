package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.BodyStatRequest;
import backend.AIGymTracker.entity.BodyStat;

import java.time.LocalDate;
import java.util.List;

public interface BodyStatService {
    BodyStat saveBodyStat(BodyStat bodyStat);
    BodyStat saveBodyStat(BodyStatRequest request);
    BodyStat getBodyStatById(Long id);
    List<BodyStat> getAllBodyStatsByUserId(Long userId);
    List<BodyStat> getBodyStatsByUserIdAndDate(Long userId, LocalDate start, LocalDate end);
    void deleteBodyStatById(Long id);
}
