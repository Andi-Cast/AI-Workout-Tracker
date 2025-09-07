package backend.AIGymTracker.exceptions;

public class BodyStatNotFoundException extends RuntimeException {
    public BodyStatNotFoundException() {
        super("Body stat not found");
    }
    
    public BodyStatNotFoundException(String message) {
        super(message);
    }
    
    public BodyStatNotFoundException(Long id) {
        super("Body stat with ID " + id + " not found");
    }
}