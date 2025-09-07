package backend.AIGymTracker.service;

import backend.AIGymTracker.config.JwtProperties;
import backend.AIGymTracker.entity.Jwt;
import backend.AIGymTracker.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {
    private final JwtProperties jwtProperties;

    public Jwt generateAccessToken(User user) {
        return generateToken(user, jwtProperties.getAccessTokenExpiration());
    }

    public Jwt generateRefreshToken(User user) {
        return generateToken(user, jwtProperties.getRefreshTokenExpiration());
    }

    private Jwt generateToken(User user, long expiration) {
        SecretKey secretKey = jwtProperties.getSecretKey();
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration);

        Claims claims = Jwts.claims();
        claims.setSubject(user.getId().toString());
        claims.put("email", user.getEmail());
        claims.put("firstname", user.getFirstname());
        claims.put("lastname", user.getLastname());
        claims.put("goalType", user.getGoalType());
        claims.setIssuedAt(now);
        claims.setExpiration(expirationDate);

        return new Jwt(claims, secretKey);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Jwt parseToken(String token) {
        try {
          Claims claims = extractAllClaims(token);
          return new Jwt(claims, jwtProperties.getSecretKey());
        } catch (JwtException e) {
            System.out.println("Token parsing failed: " + e.getMessage());
            return null;
        }
    }
}
