package com.project.campProject.api.controllers;

import com.project.campProject.entities.Comment;
import com.project.campProject.entities.User;
import com.project.campProject.repository.CommentRepository;
import com.project.campProject.requests.CommentCreateRequest;
import com.project.campProject.requests.CommentUpdateRequest;
import com.project.campProject.responses.CommentResponse;
import com.project.campProject.services.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final Logger logger = LoggerFactory.getLogger(CommentController.class);

   private final CommentService commentService;


    public CommentController (CommentService commentService){
        this.commentService = commentService;
    }
    /*
    @GetMapping( "/users/{userId}/comments" )
    public List<Comment> getAllComments(@PathVariable  Long userId){
        logger.info("comment to fetch from userId: {}", userId);
        return commentService.getAllCommentsOfUser(userId);
    }

    @GetMapping( "/comments" )
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId){
        logger.info("comment to fetch from userId: {}", userId);
        return commentService.getAllComments(userId);
    }

    @PostMapping("/users/{id}/comments")
    public Comment createOneComment(@RequestBody Comment newComment){
        //newComment.setUser();
        logger.info("comment to create: {}", newComment.toString());
        return commentService.createOneComment(newComment);
    } */

    @GetMapping
    public @ResponseBody
    List<Comment> getAllComments(){
        //logger.info("comment to fetch from userId: {}", userId);
        return commentService.getAllComments();
    }


    @PostMapping
    //public ResponseEntity<Comment> createOneComment3(@RequestBody CommentCreateRequest newCommentRequest){
    public Comment createOneComment3(@RequestBody CommentCreateRequest newCommentRequest){
        //Comment newComment = commentService.createOneComment3(newCommentRequest);
        return commentService.createOneComment3(newCommentRequest);
//        try {
//            return new ResponseEntity<Comment>(
//                    newComment,
//                    HttpStatus.CREATED);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<Comment>(
//                    newComment,
//                    HttpStatus.CONFLICT
//            );
//        }

    }

    @GetMapping ("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){

        return commentService.getOneCommentById(commentId);
    }




    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId,@RequestBody CommentUpdateRequest updateComment){
        return commentService.updateOneCommentById(commentId,updateComment);
    }



    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneCommentById(commentId);
    }

}
