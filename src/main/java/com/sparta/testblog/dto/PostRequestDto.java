package com.sparta.testblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String userId;
    private String title;
    private String contents;

    public PostRequestDto(String userId, String title, String contents) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
    }
}