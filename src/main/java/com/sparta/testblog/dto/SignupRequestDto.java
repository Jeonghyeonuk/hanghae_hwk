package com.sparta.testblog.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SignupRequestDto {
    private String userId;
    private String password;
    private String checkPassword;

    public SignupRequestDto(String userId, String password, String checkPassword) {
        this.userId = userId;
        this.password = password;
        this.checkPassword = checkPassword;
    }
}