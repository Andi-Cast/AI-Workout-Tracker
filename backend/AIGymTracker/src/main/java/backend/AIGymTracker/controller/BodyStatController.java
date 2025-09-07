package backend.AIGymTracker.controller;

import backend.AIGymTracker.dto.BodyStatRequest;
import backend.AIGymTracker.entity.BodyStat;
import backend.AIGymTracker.service.AuthorizationService;
import backend.AIGymTracker.service.BodyStatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/body-stats")
@RequiredArgsConstructor
public class BodyStatController {
    private final BodyStatService bodyStatService;
    private final AuthorizationService authorizationService;

    @PostMapping
    public ResponseEntity<BodyStat> save(@Valid @RequestBody BodyStatRequest request) {
        authorizationService.validateUserAccess(request.getUserId(), "body stats");
        return ResponseEntity.ok(bodyStatService.saveBodyStat(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BodyStat> getById(@PathVariable Long id) {
        authorizationService.validateBodyStatAccess(id);
        return ResponseEntity.ok(bodyStatService.getBodyStatById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BodyStat> update(@PathVariable Long id, @Valid @RequestBody BodyStatRequest request) {
        authorizationService.validateBodyStatAccess(id);
        authorizationService.validateUserAccess(request.getUserId(), "body stats");
        
        // Get existing entry and update
        BodyStat existingBodyStat = bodyStatService.getBodyStatById(id);
        existingBodyStat.setDate(request.getDate());
        existingBodyStat.setWeight(request.getWeight());
        existingBodyStat.setBodyFatPercentage(request.getBodyFatPercentage());
        
        return ResponseEntity.ok(bodyStatService.saveBodyStat(existingBodyStat));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BodyStat>> getByUserId(@PathVariable Long userId) {
        authorizationService.validateUserAccess(userId, "body stats");
        return ResponseEntity.ok(bodyStatService.getAllBodyStatsByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<BodyStat>> getByUserIdAndDateRange(@RequestParam Long userId,
                                                                  @RequestParam LocalDate start,
                                                                  @RequestParam LocalDate end) {
        authorizationService.validateUserAccess(userId, "body stats");
        return ResponseEntity.ok(bodyStatService.getBodyStatsByUserIdAndDate(userId, start, end));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorizationService.validateBodyStatAccess(id);
        bodyStatService.deleteBodyStatById(id);
        return ResponseEntity.noContent().build();
    }
}
