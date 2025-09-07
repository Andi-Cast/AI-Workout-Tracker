package backend.AIGymTracker.exceptions;

public class WorkoutLogNotFoundException extends RuntimeException {
    public WorkoutLogNotFoundException () {
      super("Workout log not found");
    }
}
