package backend.AIGymTracker.mapper;

import backend.AIGymTracker.dto.WorkoutLogResponse;
import backend.AIGymTracker.entity.WorkoutLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkoutLogMapper {

    @Mapping(source = "user.id", target = "userId")
    WorkoutLogResponse toWorkoutLogResponse(WorkoutLog workoutLog);

    @Mapping(source = "userId", target = "user.id")
    WorkoutLog toWorkoutLog(WorkoutLogResponse workoutLogResponse);
}
