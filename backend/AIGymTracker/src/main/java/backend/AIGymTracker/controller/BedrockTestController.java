package backend.AIGymTracker.controller;

import backend.AIGymTracker.service.BedrockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class BedrockTestController {
    
    private final BedrockService bedrockService;
    
    @PostMapping("/bedrock")
    public ResponseEntity<String> testBedrock(@RequestBody String prompt) {
        try {
            String response = bedrockService.generateWorkoutFeedback(prompt);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/bedrock/hello")
    public ResponseEntity<String> testBedrockHello() {
        try {
            String response = bedrockService.generateWorkoutFeedback("Say hello in a friendly way!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}