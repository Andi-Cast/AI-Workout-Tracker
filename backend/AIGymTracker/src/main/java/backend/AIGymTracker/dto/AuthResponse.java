package backend.AIGymTracker.dto;

import backend.AIGymTracker.entity.Jwt;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private Jwt accessToken;
    private Jwt refreshToken;
}
