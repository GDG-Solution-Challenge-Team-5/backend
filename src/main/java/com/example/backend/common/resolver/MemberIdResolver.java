package com.example.backend.common.resolver;

import com.example.backend.auth.JwtAuthProvider;
import com.example.backend.common.resolver.memberid.MemberId;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Slf4j
@Component
public class MemberIdResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private JwtAuthProvider jwtAuthProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        MemberId annotation = parameter.getParameterAnnotation(MemberId.class);
        return annotation != null && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String authorization = request.getHeader("Authorization");
        Long idByToken = jwtAuthProvider.getIdByToken(authorization.substring(7));
        return idByToken;
    }
}
