package com.project.campProject.repository;

import com.project.campProject.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository  extends JpaRepository<Comment,String> {
    List<Comment> findByUserId(Integer userId);


    Optional<Object> findById(Integer commentId);
}
