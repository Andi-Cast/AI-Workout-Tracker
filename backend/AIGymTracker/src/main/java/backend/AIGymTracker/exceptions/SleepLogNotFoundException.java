package backend.AIGymTracker.exceptions;

public class SleepLogNotFoundException extends RuntimeException {
    public SleepLogNotFoundException() {
        super("Sleep log not found");
    }
    
    public SleepLogNotFoundException(String message) {
        super(message);
    }
    
    public SleepLogNotFoundException(Long id) {
        super("Sleep log with ID " + id + " not found");
    }
}