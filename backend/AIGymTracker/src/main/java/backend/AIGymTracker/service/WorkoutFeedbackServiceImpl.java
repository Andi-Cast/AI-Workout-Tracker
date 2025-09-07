package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.WorkoutFeedbackRequest;
import backend.AIGymTracker.dto.WorkoutFeedbackResponse;
import backend.AIGymTracker.entity.WorkoutFeedback;
import backend.AIGymTracker.entity.WorkoutLog;
import backend.AIGymTracker.exceptions.WorkoutFeedbackNotFoundException;
import backend.AIGymTracker.exceptions.WorkoutLogNotFoundException;
import backend.AIGymTracker.mapper.WorkoutFeedbackMapper;
import backend.AIGymTracker.repository.WorkoutFeedbackRepository;
import backend.AIGymTracker.repository.WorkoutLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutFeedbackServiceImpl implements WorkoutFeedbackService {
    private final WorkoutFeedbackRepository workoutFeedbackRepository;
    private final WorkoutLogRepository workoutLogRepository;
    private final WorkoutFeedbackMapper workoutFeedbackMapper;

    @Override
    public WorkoutFeedbackResponse saveWorkoutFeedback(WorkoutFeedbackRequest request) {

        System.out.println("Saving feedback for workoutLogId = " + request.getWorkoutLogId());

        WorkoutLog workoutLog = workoutLogRepository.findById(request.getWorkoutLogId())
                .orElseThrow(WorkoutLogNotFoundException::new);

        WorkoutFeedback workoutFeedback = WorkoutFeedback.builder()
                .workoutLog(WorkoutLog.builder().id(request.getWorkoutLogId()).build())
                .energyLevel(request.getEnergyLevel())
                .mood(request.getMood())
                .difficulty(request.getDifficulty())
                .notes(request.getNotes())
                .build();

        workoutFeedbackRepository.save(workoutFeedback);

        return workoutFeedbackMapper.toWorkoutFeedbackResponse(workoutFeedback);
    }

    @Override
    public WorkoutFeedbackResponse updateWorkoutFeedback(Long id, WorkoutFeedbackRequest request) {
        WorkoutFeedback feedback = workoutFeedbackRepository.findById(id)
                .orElseThrow(WorkoutFeedbackNotFoundException::new);

        feedback.setEnergyLevel(request.getEnergyLevel());
        feedback.setMood(request.getMood());
        feedback.setDifficulty(request.getDifficulty());
        feedback.setNotes(request.getNotes());

        workoutFeedbackRepository.save(feedback);

        return workoutFeedbackMapper.toWorkoutFeedbackResponse(feedback);
    }

    @Override
    public WorkoutFeedbackResponse getFeedbackById(Long id) {
        WorkoutFeedback workoutFeedback =  workoutFeedbackRepository.findById(id)
                .orElseThrow(WorkoutFeedbackNotFoundException::new);

        return workoutFeedbackMapper.toWorkoutFeedbackResponse(workoutFeedback);
    }

    @Override
    public WorkoutFeedbackResponse getWorkoutFeedbackByWorkoutLogId(Long workoutLogId) {
        workoutLogRepository.findById(workoutLogId)
                .orElseThrow(WorkoutLogNotFoundException::new);

        WorkoutFeedback workoutFeedback = workoutFeedbackRepository.findByWorkoutLogId(workoutLogId)
                .orElseThrow(WorkoutFeedbackNotFoundException::new);

        return workoutFeedbackMapper.toWorkoutFeedbackResponse(workoutFeedback);

    }

    @Override
    public void deleteWorkoutFeedbackById(Long id) {
        if(!workoutFeedbackRepository.existsById(id)) {
            throw new WorkoutFeedbackNotFoundException();
        }
        workoutFeedbackRepository.deleteById(id);
    }
}
