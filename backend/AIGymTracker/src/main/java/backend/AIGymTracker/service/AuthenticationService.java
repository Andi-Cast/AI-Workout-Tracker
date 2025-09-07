package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.AuthResponse;
import backend.AIGymTracker.dto.AuthRequest;
import backend.AIGymTracker.entity.Jwt;
import backend.AIGymTracker.entity.User;

public interface AuthenticationService {
    User getCurrentUser();
    AuthResponse authenticate(AuthRequest request);
    Jwt refreshAccessToken(String refreshToken);
}
