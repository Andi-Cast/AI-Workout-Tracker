package backend.AIGymTracker.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BodyStatRequest {
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;
    
    @NotNull(message = "Weight is required")
    @DecimalMin(value = "20.0", message = "Weight must be at least 20kg")
    @DecimalMax(value = "500.0", message = "Weight must not exceed 500kg")
    @Digits(integer = 3, fraction = 2, message = "Weight must have at most 3 integer digits and 2 decimal places")
    private BigDecimal weight;
    
    @DecimalMin(value = "0.0", message = "Body fat percentage must be non-negative")
    @DecimalMax(value = "100.0", message = "Body fat percentage cannot exceed 100%")
    @Digits(integer = 3, fraction = 2, message = "Body fat percentage must have at most 3 integer digits and 2 decimal places")
    private BigDecimal bodyFatPercentage;
}