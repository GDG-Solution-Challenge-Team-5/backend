package com.example.backend.member.controller;


import com.example.backend.auth.JwtDto;
import com.example.backend.member.dto.LoginRequest;
import com.example.backend.member.dto.SignupRequest;
import com.example.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(memberService.login(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@RequestBody SignupRequest request){
        return ResponseEntity.ok(memberService.signup(request));
    }
}
