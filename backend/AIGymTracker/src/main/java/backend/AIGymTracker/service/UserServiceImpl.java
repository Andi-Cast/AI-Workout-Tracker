package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.RegisterRequest;
import backend.AIGymTracker.dto.UpdateUserRequest;
import backend.AIGymTracker.entity.GoalType;
import backend.AIGymTracker.entity.User;
import backend.AIGymTracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        var user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAge(request.getAge());
        user.setGoalType(GoalType.valueOf(request.getGoalType().toUpperCase()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long userId, UpdateUserRequest request) {
        var user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
