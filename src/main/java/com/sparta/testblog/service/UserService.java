package com.sparta.testblog.service;

import com.sparta.testblog.dto.CheckIdRequestDto;
import com.sparta.testblog.dto.SignupRequestDto;
import com.sparta.testblog.model.Users;
import com.sparta.testblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;


    private final UserRepository userRepository;


    public void registerUser(SignupRequestDto requestDto) {
        if (requestDto == null){
            throw new IllegalStateException("오류입니다");
        }

        String regExp = "^[a-zA-z0-9]{3,20}$";//아이디 검사 영문숫자,3~20자
        String userId = requestDto.getUserId();
        boolean regex = Pattern.matches(regExp,userId);

        String regExp2 = "^[a-zA-z0-9]{4,20}$";// 비밀번호 검사 영문숫자,4~20자
        boolean regex2 = Pattern.matches(regExp2, requestDto.getPassword());
        if(!requestDto.getPassword().equals(requestDto.getCheckPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다");
        }
        Optional<Users> found = userRepository.findByUserId(userId);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 아이가 존재합니다.");
        }

        if(requestDto.getPassword().contains(userId)){
            throw new IllegalStateException("비밀번호에 아이디값이 포함될 수 없습니다.");
        }

        if(!regex) {
            throw new IllegalArgumentException("아이디는 숫자와 영문자 조합으로 3~20자리를 사용해야 합니다.");
        }

        if(!regex2) {
            throw new IllegalArgumentException("비밀번호는 숫자와 영문자 조합으로 4~20자리를 사용해야 합니다.");
        }
        String password = passwordEncoder.encode(requestDto.getPassword());
        Users users = new Users(userId, password);
        userRepository.save(users);

    }

}
