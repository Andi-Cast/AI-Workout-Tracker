package backend.AIGymTracker.exceptions;

public class ExerciseEntryNotFoundException extends RuntimeException {
    public ExerciseEntryNotFoundException() {
        super("Exercise entry not found");
    }
    
    public ExerciseEntryNotFoundException(String message) {
        super(message);
    }
    
    public ExerciseEntryNotFoundException(Long id) {
        super("Exercise entry with ID " + id + " not found");
    }
}