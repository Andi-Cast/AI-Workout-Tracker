package backend.AIGymTracker.service;

import backend.AIGymTracker.entity.BodyStat;
import backend.AIGymTracker.repository.BodyStatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyStatServiceImpl implements BodyStatService {
    private final BodyStatRepository bodyStatRepository;

    @Override
    public BodyStat saveBodyStat(BodyStat bodyStat) {
        return bodyStatRepository.save(bodyStat);
    }

    @Override
    public BodyStat getBodyStatById(Long id) {
        return bodyStatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BodyStat not found"));
    }

    @Override
    public List<BodyStat> getAllBodyStatsByUserId(Long userId) {
        return bodyStatRepository.findAllByUserId(userId);
    }

    @Override
    public List<BodyStat> getBodyStatsByUserIdAndDate(Long userId, LocalDate start, LocalDate end) {
        return bodyStatRepository.findByUserIdAndDateBetween(userId, start, end);
    }

    @Override
    public void deleteBodyStatById(Long id) {
        bodyStatRepository.deleteById(id);
    }
}
