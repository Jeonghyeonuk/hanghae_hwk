package com.sparta.testblog.service;

import com.sparta.testblog.dto.CommentRequestDto;
import com.sparta.testblog.model.Comments;
import com.sparta.testblog.model.Posts;
import com.sparta.testblog.model.Users;
import com.sparta.testblog.repository.CommentRepository;
import com.sparta.testblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postsRepository;

    @Transactional
    public boolean commentsWrite(Long postId, CommentRequestDto commentRequestDto, Users users) {
        Posts posts=postsRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("댓글을 작성할 게시글이 없습니다."));

        Comments comments=new Comments(commentRequestDto.getComment(),postId,users.getUserId());
        commentRepository.save(comments);
        return true;
    }

    @Transactional
    public boolean commentUpdate(Long commentId, CommentRequestDto commentRequestDto, Users users) {
        Comments comments= commentRepository.findById(commentId).orElseThrow(
                ()->new NullPointerException("수정할 댓글이 없습니다."));
        if(comments.getUserId().equals(users.getUserId())) {
            comments.setComment(commentRequestDto.getComment());
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public boolean deleteComment(Long commentId,Users users) {
        Comments comments= commentRepository.findById(commentId).orElseThrow(
                ()->new NullPointerException("삭제할 댓글이 없습니다."));
        if(comments.getUserId().equals(users.getUserId())){
            commentRepository.deleteById(comments.getId());
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public List<Comments> getComments(Long postId) {
        Posts posts=postsRepository.findById(postId).orElseThrow(
                ()->new NullPointerException("댓글을 작성할 게시글이 없습니다.")
        );

        return commentRepository.findCommentsByPostId(postId);
    }
}
