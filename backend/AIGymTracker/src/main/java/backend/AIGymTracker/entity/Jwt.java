package backend.AIGymTracker.entity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

import javax.crypto.SecretKey;
import java.util.Date;

@AllArgsConstructor
public class Jwt {
    private final Claims claims;
    private final SecretKey secretKey;

    public boolean isExpired() {
        try {
            return claims.getExpiration().before(new Date());
        } catch (JwtException ex) {
            return false;
        }
    }

    public Long getId() {
        return Long.valueOf(claims.getSubject());
    }

    public String toString() {
        return Jwts.builder().setClaims(claims).signWith(secretKey).compact();
    }
}
