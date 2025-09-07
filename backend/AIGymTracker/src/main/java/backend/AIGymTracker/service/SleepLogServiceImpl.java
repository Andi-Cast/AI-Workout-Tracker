package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.SleepLogRequest;
import backend.AIGymTracker.entity.SleepLog;
import backend.AIGymTracker.entity.User;
import backend.AIGymTracker.exceptions.SleepLogNotFoundException;
import backend.AIGymTracker.exceptions.UserNotFoundException;
import backend.AIGymTracker.repository.SleepLogRepository;
import backend.AIGymTracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SleepLogServiceImpl implements SleepLogService {
    private final SleepLogRepository sleepLogRepository;
    private final UserRepository userRepository;

    @Override
    public SleepLog saveSleepLog(SleepLog sleepLog) {
        return sleepLogRepository.save(sleepLog);
    }

    @Override
    public SleepLog saveSleepLog(SleepLogRequest request) {
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User with ID " + request.getUserId() + " not found"));
        
        SleepLog sleepLog = new SleepLog();
        sleepLog.setUser(user);
        sleepLog.setDate(request.getDate());
        sleepLog.setHoursSlept(request.getHoursSlept());
        
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
                .orElseThrow(() -> new SleepLogNotFoundException(id));
    }

    @Override
    public void deleteSleepLogById(Long id) {
        SleepLog sleepLog = sleepLogRepository.findById(id)
            .orElseThrow(() -> new SleepLogNotFoundException(id));
        sleepLogRepository.deleteById(id);
    }
}
