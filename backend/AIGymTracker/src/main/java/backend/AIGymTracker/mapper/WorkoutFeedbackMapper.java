package backend.AIGymTracker.mapper;

import backend.AIGymTracker.dto.WorkoutFeedbackRequest;
import backend.AIGymTracker.dto.WorkoutFeedbackResponse;
import backend.AIGymTracker.entity.WorkoutFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkoutFeedbackMapper {

    @Mapping(source = "workoutLog.id", target = "workoutLogId")
    WorkoutFeedbackResponse toWorkoutFeedbackResponse(WorkoutFeedback workoutFeedback);

    @Mapping(source = "workoutLogId", target = "workoutLog.id")
    WorkoutFeedback toEntity(WorkoutFeedbackRequest request);
}
