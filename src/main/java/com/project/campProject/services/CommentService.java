package com.project.campProject.services;

import com.project.campProject.entities.Comment;
import com.project.campProject.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final Logger logger = LoggerFactory.getLogger(CommentService.class);

    private CommentRepository commentRepository;

    public CommentService (CommentRepository commentRepository){
        super();
        this.commentRepository = commentRepository;
    }


    public List<Comment> getAllComments(Optional<Integer> userId) {
        if(userId.isPresent())
            return commentRepository.findByUserId(userId.get());
        return commentRepository.findAll();
    }

    public Comment getOneCommentById(Integer commentId) {
        return (Comment) commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(Comment newComment) {
        logger.info("comment to create: {}", newComment.toString());
        return commentRepository.save(newComment);
    }
}
