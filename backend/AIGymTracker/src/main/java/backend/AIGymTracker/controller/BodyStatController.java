package backend.AIGymTracker.controller;

import backend.AIGymTracker.entity.BodyStat;
import backend.AIGymTracker.service.BodyStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/body-stats")
@RequiredArgsConstructor
public class BodyStatController {
    private final BodyStatService bodyStatService;

    @PostMapping
    public ResponseEntity<BodyStat> save(@RequestBody BodyStat bodyStat) {
        return ResponseEntity.ok(bodyStatService.saveBodyStat(bodyStat));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BodyStat> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bodyStatService.getBodyStatById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BodyStat>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(bodyStatService.getAllBodyStatsByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<BodyStat>> getByUserIdAndDateRange(@RequestParam Long userId,
                                                                  @RequestParam LocalDate start,
                                                                  @RequestParam LocalDate end) {
        return ResponseEntity.ok(bodyStatService.getBodyStatsByUserIdAndDate(userId, start, end));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bodyStatService.deleteBodyStatById(id);
        return ResponseEntity.noContent().build();
    }
}
