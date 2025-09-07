package backend.AIGymTracker.exceptions;

import backend.AIGymTracker.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFound(UserNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(WorkoutLogNotFoundException.class)
    public ResponseEntity<ErrorDto> handleWorkoutLogNotFound(WorkoutLogNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(WorkoutFeedbackNotFoundException.class)
    public ResponseEntity<ErrorDto> handleWorkoutFeedbackNotFound(WorkoutFeedbackNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGeneralException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    }

    private ResponseEntity<ErrorDto> buildResponse(HttpStatus status, String message) {
        ErrorDto response = new ErrorDto(
                status.value(),
                status.getReasonPhrase(),
                message,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }
}
