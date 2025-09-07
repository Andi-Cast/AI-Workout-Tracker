package backend.AIGymTracker.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SleepLogRequest {
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;
    
    @NotNull(message = "Hours slept is required")
    @DecimalMin(value = "0.0", message = "Hours slept must be non-negative")
    @DecimalMax(value = "24.0", message = "Hours slept cannot exceed 24 hours")
    @Digits(integer = 2, fraction = 1, message = "Hours slept must have at most 2 integer digits and 1 decimal place")
    private BigDecimal hoursSlept;
}