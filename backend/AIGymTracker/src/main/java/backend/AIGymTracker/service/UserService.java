package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.RegisterRequest;
import backend.AIGymTracker.dto.UpdateUserRequest;
import backend.AIGymTracker.entity.User;

import java.util.Optional;

public interface UserService {
    User saveUser (RegisterRequest request);
    User getUserById(Long id);
    User updateUser(Long userId, UpdateUserRequest request);
    void deleteUserById(Long id);
}
