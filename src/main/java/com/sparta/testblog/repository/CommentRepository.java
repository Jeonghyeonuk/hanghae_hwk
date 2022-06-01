package com.sparta.testblog.repository;

import com.sparta.testblog.model.Comments;
import com.sparta.testblog.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments,Long> {

    List<Comments> findCommentsByPostId(Long postId);

    void deleteByPostId(Long postId);
}