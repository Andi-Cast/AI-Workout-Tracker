package backend.AIGymTracker.exceptions;

public class WorkoutFeedbackNotFoundException extends RuntimeException {
    public WorkoutFeedbackNotFoundException() {
      super("Workout Feedback Not Found");
    }
}
