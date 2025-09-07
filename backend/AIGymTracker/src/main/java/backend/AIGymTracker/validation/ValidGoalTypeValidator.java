package backend.AIGymTracker.validation;

import backend.AIGymTracker.entity.GoalType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidGoalTypeValidator implements ConstraintValidator<ValidGoalType, String> {
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null values
        }
        
        try {
            GoalType.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}