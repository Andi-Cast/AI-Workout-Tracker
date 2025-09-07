package backend.AIGymTracker.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidGoalTypeValidator.class)
@Documented
public @interface ValidGoalType {
    String message() default "Goal type must be one of: BULK, RECOMPOSITION, MAINTAIN, CUT";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}