package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.BodyStatRequest;
import backend.AIGymTracker.entity.BodyStat;
import backend.AIGymTracker.entity.User;
import backend.AIGymTracker.exceptions.BodyStatNotFoundException;
import backend.AIGymTracker.exceptions.UserNotFoundException;
import backend.AIGymTracker.repository.BodyStatRepository;
import backend.AIGymTracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyStatServiceImpl implements BodyStatService {
    private final BodyStatRepository bodyStatRepository;
    private final UserRepository userRepository;

    @Override
    public BodyStat saveBodyStat(BodyStat bodyStat) {
        return bodyStatRepository.save(bodyStat);
    }

    @Override
    public BodyStat saveBodyStat(BodyStatRequest request) {
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User with ID " + request.getUserId() + " not found"));
        
        BodyStat bodyStat = new BodyStat();
        bodyStat.setUser(user);
        bodyStat.setDate(request.getDate());
        bodyStat.setWeight(request.getWeight());
        bodyStat.setBodyFatPercentage(request.getBodyFatPercentage());
        
        return bodyStatRepository.save(bodyStat);
    }

    @Override
    public BodyStat getBodyStatById(Long id) {
        return bodyStatRepository.findById(id)
                .orElseThrow(() -> new BodyStatNotFoundException(id));
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
        BodyStat bodyStat = bodyStatRepository.findById(id)
            .orElseThrow(() -> new BodyStatNotFoundException(id));
        bodyStatRepository.deleteById(id);
    }
}
