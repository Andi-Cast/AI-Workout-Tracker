package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.WorkoutLogRequest;
import backend.AIGymTracker.dto.WorkoutLogResponse;
import backend.AIGymTracker.entity.User;
import backend.AIGymTracker.entity.WorkoutLog;
import backend.AIGymTracker.exceptions.UserNotFoundException;
import backend.AIGymTracker.exceptions.WorkoutLogNotFoundException;
import backend.AIGymTracker.mapper.WorkoutLogMapper;
import backend.AIGymTracker.repository.UserRepository;
import backend.AIGymTracker.repository.WorkoutLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutLogServiceImpl implements WorkoutLogService {
    private final WorkoutLogRepository workoutLogRepository;
    private final WorkoutLogMapper workoutLogMapper;
    private final UserRepository userRepository;

    @Override
    public WorkoutLogResponse saveWorkoutLog(WorkoutLogRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(UserNotFoundException::new);

        WorkoutLog workoutLog = WorkoutLog.builder()
                .user(User.builder().id(request.getUserId()).build())
                .date(request.getDate())
                .notes(request.getNotes())
                .build();
        workoutLogRepository.save(workoutLog);

        return workoutLogMapper.toWorkoutLogResponse(workoutLog);
    }

    @Override
    public List<WorkoutLogResponse> getWorkoutLogsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        List<WorkoutLog> logs = workoutLogRepository.findAllByUserId(userId);
        return logs.stream()
                .map(workoutLogMapper::toWorkoutLogResponse)
                .toList();
    }

    @Override
    public List<WorkoutLogResponse> getWorkoutLogsByUserIdAndBetween(Long userId, LocalDate start, LocalDate end) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        List<WorkoutLog> logs = workoutLogRepository.findByUserIdAndDateBetween(userId, start, end);
        return logs.stream()
                .map(workoutLogMapper::toWorkoutLogResponse)
                .toList();
    }

    @Override
    public WorkoutLogResponse getWorkoutLogById(Long id) {
        WorkoutLog workoutLog = workoutLogRepository.findById(id).orElseThrow(WorkoutLogNotFoundException::new);
        return workoutLogMapper.toWorkoutLogResponse(workoutLog);
    }

    @Override
    public void deleteWorkoutLog(Long id) {
        if(!workoutLogRepository.existsById(id)) {
            throw new WorkoutLogNotFoundException();
        }
        workoutLogRepository.deleteById(id);
    }
}
