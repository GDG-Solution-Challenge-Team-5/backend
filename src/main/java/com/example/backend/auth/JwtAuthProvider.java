package com.example.backend.auth;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthProvider {
    private final JwtIssuer jwtIssuer;

    public Long getIdByToken(String token) {
        Claims claims = jwtIssuer.getClaims(token);
        return Long.getLong(jwtIssuer.getSubject(claims));
    }
}
