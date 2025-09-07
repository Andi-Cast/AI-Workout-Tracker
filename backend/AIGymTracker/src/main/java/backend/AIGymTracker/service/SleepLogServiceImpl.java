package backend.AIGymTracker.service;

import backend.AIGymTracker.entity.SleepLog;
import backend.AIGymTracker.repository.SleepLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SleepLogServiceImpl implements SleepLogService {
    private final SleepLogRepository sleepLogRepository;

    @Override
    public SleepLog saveSleepLog(SleepLog sleepLog) {
        return sleepLogRepository.save(sleepLog);
    }

    @Override
    public List<SleepLog> getSleepLogByUserId(Long userId) {
        return sleepLogRepository.findByUserId(userId);
    }

    @Override
    public List<SleepLog> getSleepLogsByUserIdAndBetween(Long userId, LocalDate start, LocalDate end) {
        return sleepLogRepository.findByUserIdAndDateBetween(userId, start, end);
    }

    @Override
    public SleepLog getSleepLogById(Long id) {
        return sleepLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SleepLog not found"));
    }

    @Override
    public void deleteSleepLogById(Long id) {
        sleepLogRepository.deleteById(id);
    }
}
