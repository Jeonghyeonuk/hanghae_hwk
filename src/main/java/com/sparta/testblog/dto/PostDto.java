package com.sparta.testblog.dto;

import com.sparta.testblog.model.Posts;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDto {
    //private Long id;
    private String userId;
    private String title;
    private String postDesc;
    private Long fileId;
    private String filePath;

    /*public Posts toEntity(){
        Posts build = Posts.builder()
                .user_id(userId)
                .title(title)
                .desc(desc)
                .fileId(fileId)
                .build();
        return build;
    }
    @Builder
    public PostDto(String userId, String title, String desc, Long fileId){
        this.userId = userId;
        this.title = title;
        this.desc = desc;
        this.fileId = fileId;
    }*/
}
