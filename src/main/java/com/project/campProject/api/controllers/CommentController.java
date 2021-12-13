package com.project.campProject.api.controllers;

import com.project.campProject.entities.Comment;
import com.project.campProject.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

   private CommentService commentService;

    public CommentController (CommentService commentService){
        super();
        this.commentService = commentService;
    }
    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Integer> userId){
        return commentService.getAllComments(userId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody Comment newComment){
        return commentService.createOneComment(newComment);
    }

    @GetMapping ("/comments/{commentId}")
    public Comment getOneComment(@PathVariable Integer commentId){
        return commentService.getOneCommentById(commentId);
    }

}
