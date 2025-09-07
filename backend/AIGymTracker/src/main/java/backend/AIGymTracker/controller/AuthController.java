package backend.AIGymTracker.controller;

import backend.AIGymTracker.config.JwtProperties;
import backend.AIGymTracker.dto.AuthResponse;
import backend.AIGymTracker.dto.AuthRequest;
import backend.AIGymTracker.dto.JwtResponse;
import backend.AIGymTracker.dto.RegisterRequest;
import backend.AIGymTracker.service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtProperties jwtProperties;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public JwtResponse login(
            @Valid @RequestBody AuthRequest request,
            HttpServletResponse response) {
        var loginResult = authenticationService.authenticate(request);

        var refreshToken  = loginResult.getRefreshToken().toString();
        var cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/api/auth/refresh");
        cookie.setMaxAge(jwtProperties.getRefreshTokenExpiration());
        cookie.setSecure(true);
        response.addCookie(cookie);

        return new JwtResponse(loginResult.getAccessToken().toString());
    }
}
