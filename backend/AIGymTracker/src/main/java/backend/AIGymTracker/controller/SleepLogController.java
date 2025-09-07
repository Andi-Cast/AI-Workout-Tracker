package backend.AIGymTracker.controller;

import backend.AIGymTracker.dto.SleepLogRequest;
import backend.AIGymTracker.entity.SleepLog;
import backend.AIGymTracker.service.AuthorizationService;
import backend.AIGymTracker.service.SleepLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sleep-logs")
@RequiredArgsConstructor
public class SleepLogController {
    private final SleepLogService sleepLogService;
    private final AuthorizationService authorizationService;

    @PostMapping
    public ResponseEntity<SleepLog> save(@Valid @RequestBody SleepLogRequest request) {
        authorizationService.validateUserAccess(request.getUserId(), "sleep logs");
        return ResponseEntity.ok(sleepLogService.saveSleepLog(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SleepLog>> getByUserId(@PathVariable Long userId) {
        authorizationService.validateUserAccess(userId, "sleep logs");
        return ResponseEntity.ok(sleepLogService.getSleepLogByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<SleepLog>> getByUserIdAndRange(@RequestParam Long userId,
                                                              @RequestParam LocalDate start,
                                                              @RequestParam LocalDate end)
    {
        authorizationService.validateUserAccess(userId, "sleep logs");
        return ResponseEntity.ok(sleepLogService.getSleepLogsByUserIdAndBetween(userId, start, end));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SleepLog> getById(@PathVariable Long id) {
        authorizationService.validateSleepLogAccess(id);
        return ResponseEntity.ok(sleepLogService.getSleepLogById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SleepLog> update(@PathVariable Long id, @Valid @RequestBody SleepLogRequest request) {
        authorizationService.validateSleepLogAccess(id);
        authorizationService.validateUserAccess(request.getUserId(), "sleep logs");
        
        // Get existing entry and update
        SleepLog existingSleepLog = sleepLogService.getSleepLogById(id);
        existingSleepLog.setDate(request.getDate());
        existingSleepLog.setHoursSlept(request.getHoursSlept());
        
        return ResponseEntity.ok(sleepLogService.saveSleepLog(existingSleepLog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorizationService.validateSleepLogAccess(id);
        sleepLogService.deleteSleepLogById(id);
        return ResponseEntity.noContent().build();
    }
}
