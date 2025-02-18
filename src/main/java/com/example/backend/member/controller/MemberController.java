package com.example.backend.member.controller;


import com.example.backend.auth.JwtDto;
import com.example.backend.common.resolver.memberid.MemberId;
import com.example.backend.common.util.CustomResponseBody;
import com.example.backend.common.util.ResponseUtil;
import com.example.backend.member.dto.LoginRequest;
import com.example.backend.member.dto.RefreshTokenRequest;
import com.example.backend.member.dto.RefreshTokenResponse;
import com.example.backend.member.dto.SignupRequest;
import com.example.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<CustomResponseBody<JwtDto>> login(@RequestBody LoginRequest request) {
        return ResponseUtil.success(memberService.login(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<CustomResponseBody<Long>> signup(@RequestBody SignupRequest request){
        return ResponseUtil.success(memberService.signup(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<CustomResponseBody<RefreshTokenResponse>> refreshToken(@RequestBody RefreshTokenRequest request){
        return ResponseUtil.success(memberService.refreshToken(request));
    }

    // TODO 테스트 종료 후 삭제 필수
    @GetMapping("/test")
    public ResponseEntity<CustomResponseBody<String>> test(@MemberId Long id){
        return ResponseUtil.success(id.toString());
    }

}
