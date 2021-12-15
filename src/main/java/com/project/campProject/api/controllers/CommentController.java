package com.project.campProject.api.controllers;

import com.project.campProject.entities.Comment;
import com.project.campProject.services.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/comments")
public class CommentController {

    private final Logger logger = LoggerFactory.getLogger(CommentController.class);

   private CommentService commentService;

    public CommentController (CommentService commentService){
        super();
        this.commentService = commentService;
    }
    @GetMapping( "/comments" )
    public List<Comment> getAllComments(@RequestParam Optional<Integer> userId){
        return commentService.getAllComments(userId);
    }

    @PostMapping("/comments")
    public Comment createOneComment(@RequestBody Comment newComment){
        logger.info("comment to create: {}", newComment.toString());
        return commentService.createOneComment(newComment);
    }

    @GetMapping ("/comments/{commentId}")
    public Comment getOneComment(@PathVariable Integer commentId){
        return commentService.getOneCommentById(commentId);
    }

}
