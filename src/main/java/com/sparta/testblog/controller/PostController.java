package com.sparta.testblog.controller;


import com.sparta.testblog.dto.PostDto;
import com.sparta.testblog.dto.PostRequestDto;
import com.sparta.testblog.model.Posts;
import com.sparta.testblog.model.Users;
import com.sparta.testblog.repository.PostRepository;
import com.sparta.testblog.repository.UserRepository;
import com.sparta.testblog.security.TokenUser;
import com.sparta.testblog.security.UserDetailsImpl;
import com.sparta.testblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    private final UserRepository userRepository;



    @Autowired
    public PostController(PostRepository postRepository, PostService postService, UserRepository userRepository)  {
        this.postRepository = postRepository;
        this.postService = postService;
        this.userRepository = userRepository;
    }

    @PostMapping("/api/contents")
    public void creatBoard(@RequestBody PostRequestDto requestDto, HttpServletRequest request) throws Exception {
        TokenUser tokenUser=new TokenUser(userRepository);
        Users users=tokenUser.getUser(request);
            if (users.getUserId().equals(requestDto.getUserId())) {
                Posts posts = new Posts(requestDto);
                System.out.println(requestDto.toString());
                postRepository.save(posts);
            } else {
                throw new IllegalStateException("로그인이 필요합니다.");
            }
        }
    }
//
//    @GetMapping("/api/contents")
//    public List<PostRequestDto> readPostList(){ // 게시글 목록 조회
//
//        return postService.PostList();
//    }
//    @GetMapping("/api/boards/details")
//    public List<BoardDetailRequestDto> DetailBoardList(){ // 게시글 조회
//
//        return boardService.boardDetail();
//    }
//
//    @GetMapping("/api/boards")
//    public List<Board> readBoard() { // 전부다 조회
//        return boardRepository.findAllByOrderByCreatedAtDesc();
//    }
//
//
//    @PutMapping("/api/boards/{id}") // 비밀번호 비교하여 게시글 수정
//    public Long updateBoard(@PathVariable Long id, @RequestBody BoardUpdateRequestDto requestDto) {
//        return boardService.update(id, requestDto);
//    }
//
//
//    @DeleteMapping("/api/boards/{id}")
//    public Long deleteBoard(@RequestParam("password") String password, @PathVariable Long id) {
//        boardService.delete(password, id);
//        return id;
//    }

