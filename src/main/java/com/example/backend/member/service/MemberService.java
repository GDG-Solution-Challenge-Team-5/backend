package com.example.backend.member.service;

import com.example.backend.auth.JwtDto;
import com.example.backend.auth.JwtIssuer;
import com.example.backend.member.domain.Member;
import com.example.backend.member.dto.LoginRequest;
import com.example.backend.member.dto.RefreshTokenRequest;
import com.example.backend.member.dto.RefreshTokenResponse;
import com.example.backend.member.dto.SignupRequest;
import com.example.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtIssuer jwtIssuer;

    public JwtDto login(LoginRequest loginRequest) {
        Member member = memberRepository.findById(loginRequest.getId()).get();

        if(member.getPassword().equals(loginRequest.getPassword())){
            return JwtDto.builder()
                    .accessToken(jwtIssuer.createAccessToken(loginRequest.getId()))
                    .refreshToken(jwtIssuer.createRefreshToken(loginRequest.getId()))
                    .build();
        } else {
            throw new IllegalArgumentException("Wrong password");
        }
    }

    @Transactional
    public Long signup(SignupRequest request) {
        Member member = Member.builder()
                .name(request.getName())
                .password(request.getPassword())
                .email(request.getEmail())
                .build();

        return memberRepository.save(member).getId();
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        return new RefreshTokenResponse(
                jwtIssuer.renewAccessToken(request.getRefreshToken())
        );
    }
}
