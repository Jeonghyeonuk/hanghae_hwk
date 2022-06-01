package com.sparta.testblog.security;

import com.sparta.testblog.model.Users;
import com.sparta.testblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
@RequiredArgsConstructor
public class TokenUser {
    private final UserRepository userRepository;
    public Users getUser(HttpServletRequest request) throws Exception {
        String token = request.getHeader("authorization");
        String userId = JwtTokenProvider.getUserIdFromJWT(token);
        return userRepository.findByUserId(userId).orElseThrow(()->new Exception("invalid Token"));
    }
}