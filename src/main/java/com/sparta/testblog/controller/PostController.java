package com.sparta.testblog.controller;


import com.sparta.testblog.dto.PostRequestDto;
import com.sparta.testblog.model.Posts;
import com.sparta.testblog.repository.PostRepository;
import com.sparta.testblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;




    @Autowired
    public PostController(PostRepository postRepository, PostService postService)  {
        this.postRepository = postRepository;
        this.postService = postService;
    }
    @PostMapping("/api/contents")
    public void creatBoard(@RequestBody PostRequestDto requestDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        if (userDetails != null){
            if (userDetails.getUsername().equals(requestDto.getUserId())) {
                Posts posts = new Posts(requestDto);
                postRepository.save(posts);
            } else {
                throw new IllegalStateException("로그인이 필요합니다.");
            }
        }
    }
//
//    @GetMapping("/api/boards/list")
//    public List<BoardListRequestDto> readBoardList(){ // 게시글 목록 조회
//
//        return boardService.boardList();
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

}