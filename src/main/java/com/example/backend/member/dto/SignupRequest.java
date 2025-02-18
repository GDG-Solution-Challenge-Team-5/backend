package com.example.backend.member.dto;

import lombok.Getter;

@Getter
public class SignupRequest {
    private String name;
    private String password;
    private String email;
}
