package com.sparta.testblog.dto;

import com.sparta.testblog.model.Users;
import lombok.Data;

@Data
public class UserResponseDto {

    private String userId;
//    private String nickName;
    //private List<Post> bookmarks;

    public UserResponseDto(Users users) {
        this.userId = users.getUserId();
//        this.nickName = users.getNickname();
        //this.bookmarks = users.getBookmarks();

    }

}
