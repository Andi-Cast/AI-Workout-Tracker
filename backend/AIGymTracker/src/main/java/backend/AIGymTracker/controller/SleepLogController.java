package backend.AIGymTracker.controller;

import backend.AIGymTracker.entity.SleepLog;
import backend.AIGymTracker.service.SleepLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sleep-logs")
@RequiredArgsConstructor
public class SleepLogController {
    private final SleepLogService sleepLogService;

    @PostMapping
    public ResponseEntity<SleepLog> save(@RequestBody SleepLog sleepLog) {
        return ResponseEntity.ok(sleepLogService.saveSleepLog(sleepLog));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SleepLog>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(sleepLogService.getSleepLogByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<SleepLog>> getByUserIdAndRange(@RequestParam Long userId,
                                                              @RequestParam LocalDate start,
                                                              @RequestParam LocalDate end)
    {
        return ResponseEntity.ok(sleepLogService.getSleepLogsByUserIdAndBetween(userId, start, end));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SleepLog> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sleepLogService.getSleepLogById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sleepLogService.deleteSleepLogById(id);
        return ResponseEntity.noContent().build();
    }
}
