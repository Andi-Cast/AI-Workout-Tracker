package backend.AIGymTracker.service;

import backend.AIGymTracker.dto.AuthResponse;
import backend.AIGymTracker.dto.AuthRequest;
import backend.AIGymTracker.entity.Jwt;
import backend.AIGymTracker.entity.User;
import backend.AIGymTracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long)authentication.getPrincipal();

        return userRepository.findById(userId).orElse(null);
    }


    @Override
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public Jwt refreshAccessToken(String refreshToken) {
        var jwt = jwtService.parseToken(refreshToken);
        if (jwt == null || jwt.isExpired()) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        var user = userRepository.findById(jwt.getId()).orElseThrow();
        return jwtService.generateAccessToken(user);
    }
}
