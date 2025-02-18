package com.example.backend.auth;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtDto {
    private String accessToken;
    private String refreshToken;

    private UUID userId;
    private String name;
    private String email;
    private String picture;
}
