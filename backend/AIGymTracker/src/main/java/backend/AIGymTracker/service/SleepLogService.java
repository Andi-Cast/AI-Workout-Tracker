package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.SleepLogRequest;
import backend.AIGymTracker.entity.SleepLog;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SleepLogService {
    SleepLog saveSleepLog(SleepLog sleepLog);
    SleepLog saveSleepLog(SleepLogRequest request);
    List<SleepLog> getSleepLogByUserId(Long userId);
    List<SleepLog> getSleepLogsByUserIdAndBetween(Long userId, LocalDate start, LocalDate end);
    SleepLog getSleepLogById(Long id);
    void deleteSleepLogById(Long id);
}
