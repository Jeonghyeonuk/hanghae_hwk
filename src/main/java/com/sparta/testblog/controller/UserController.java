package com.sparta.testblog.controller;

import com.sparta.testblog.dto.CheckIdRequestDto;
import com.sparta.testblog.dto.SignupRequestDto;
import com.sparta.testblog.model.Token;
import com.sparta.testblog.model.Users;
import com.sparta.testblog.repository.UserRepository;
import com.sparta.testblog.security.JwtTokenProvider;
import com.sparta.testblog.security.UserAuthentication;
import com.sparta.testblog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:8080")
public class UserController{

    private JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserService userService;

    @ResponseBody
    @PostMapping(
            value ="/user/login",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Token.Response login(
            final HttpServletRequest req,
            final HttpServletResponse res,
            @Valid @RequestBody Token.Request request) throws Exception {
        System.out.println(request.toString());

        Users users = userRepository.findByUserId(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("없는 사용자입니다."));

        boolean checkPassword = passwordEncoder.matches(request.getPassword(), users.getPassword());

        if(!checkPassword){
            throw new IllegalArgumentException("비밀번호를 확인하세요.");
        }

        Authentication authentication = new UserAuthentication(request.getUserId(), null, null);
        String token = JwtTokenProvider.generateToken(authentication);

        Token.Response response = Token.Response.builder().token(token).build();

        return response;
    }



    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        System.out.println(requestDto.toString());
         userService.registerUser(requestDto);
        return "login";
    }


//
//    @PostMapping("/user/signup")
//    public Boolean registerUser(@RequestBody SignupRequestDto requestDto) {
//        Boolean result = userService.registerUser(requestDto);
//        return result;
//    }

//    @PostMapping("/user/idCheck")
//    public Boolean checkId(@RequestBody CheckIdRequestDto requestDto) {
//        Boolean result = userService.checkId(requestDto);
//        return result;
//    }
//
//
//    @PostMapping("/user/check")
//    public Users checkUser(HttpServletRequest request) throws Exception {
//        String token = request.getHeader("authorization");
//        String userId = JwtTokenProvider.getUserIdFromJWT(token);
//        Users user = userRepository.findByUserId(userId).orElseThrow(()->new Exception("invalid Token"));
//
//        return user;
//    }


}