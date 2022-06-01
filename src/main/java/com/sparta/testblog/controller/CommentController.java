package com.sparta.testblog.controller;

import com.sparta.testblog.dto.CommentRequestDto;
import com.sparta.testblog.model.Comments;
import com.sparta.testblog.model.Users;
import com.sparta.testblog.repository.UserRepository;
import com.sparta.testblog.security.TokenUser;
import com.sparta.testblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserRepository userRepository;

    //댓글작성
    @PostMapping("/comments/{postId}")
    public boolean commentsWrite(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto commentRequestDto,
//            @AuthenticationPrincipal UserDetailsImpl userDetails
            HttpServletRequest request) throws Exception {
        TokenUser tokenUser=new TokenUser(userRepository);
        Users users=tokenUser.getUser(request);
        return commentService.commentsWrite(postId,commentRequestDto,users);
    }


    //댓글 수정
    @PutMapping("/comments/{commentId}")
    public boolean commentUpdate(@PathVariable Long commentId,
                                 @RequestBody CommentRequestDto commentRequestDto,
//                                 @AuthenticationPrincipal UserDetailsImpl userDetails
                                 HttpServletRequest request
    ) throws Exception {
        TokenUser tokenUser=new TokenUser(userRepository);
        Users users=tokenUser.getUser(request);
        return commentService.commentUpdate(commentId,commentRequestDto,users);
    }


    //댓글 List조회
    @GetMapping("/comments/{postId}")
    public List<Comments> getComments(@PathVariable Long postId){
        return commentService.getComments(postId);
    }


    //댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public boolean commentDelete(@PathVariable Long commentId,
//                                 @AuthenticationPrincipal UserDetailsImpl userDetails
                                 HttpServletRequest request
    ) throws Exception {
        TokenUser tokenUser=new TokenUser(userRepository);
        Users users=tokenUser.getUser(request);
        return commentService.deleteComment(commentId,users);
    }

}
