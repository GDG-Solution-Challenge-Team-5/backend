package com.example.backend.member.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private Long id;
    private String password;
}
