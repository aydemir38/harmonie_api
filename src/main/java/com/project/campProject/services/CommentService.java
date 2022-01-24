package com.project.campProject.services;

import com.project.campProject.entities.Comment;
import com.project.campProject.entities.User;
import com.project.campProject.repository.CommentRepository;
import com.project.campProject.requests.CommentCreateRequest;
import com.project.campProject.requests.CommentUpdateRequest;
import com.project.campProject.responses.CommentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final Logger logger = LoggerFactory.getLogger(CommentService.class);

    private CommentRepository commentRepository;
    private UserService userService;

    public CommentService (CommentRepository commentRepository,UserService userService){
        super();
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    /*
    public List<Comment> getAllCommentsOfUser(Long userId) {

            return commentRepository.findByUserId(userId);

    }
    public List<Comment> getAllComments(Optional<Long> userId) {
        if(userId.isPresent())
            return commentRepository.findByUserId(userId.get());
        return commentRepository.findAll();
    } */

    public List<Comment> getAllComments() {
        return  commentRepository.findAll();

    }

        /*
    public List<CommentResponse> getAllComments(Optional<Long> userId) {
        List<Comment> list;
        if (userId.isPresent()) {
            list = commentRepository.findByUserId(userId.get());
        }else
            list = commentRepository.findAll();
        return list.stream().map(p -> new CommentResponse(p)).collect(Collectors.toList());
    }
    */

    public Comment getOneCommentById(Long commentId) {
        return (Comment) commentRepository.findById(commentId).orElse(null);
    }
    /*
    public Comment createOneComment(Comment newComment) {
        logger.info("comment to create: {}", newComment.toString());
        // Comment comment = new Comment();
        //comment.setId(1L);
        //User user =userService.getOneUserById((newComment.getId()) );
        return commentRepository.save(newComment);
    } */

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest updateComment) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment toUpdate = comment.get();
            toUpdate.setText(updateComment.getText());
            toUpdate.setTitle(updateComment.getTitle());
            commentRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

     /*
    public Comment createOneComment2(Comment newComment) {
        logger.info("comment to create: {}", newComment.toString());
        User user =userService.getOneUserById(newComment.getUserId());
        if (user== null)
            return null;
        Comment toSave = new Comment();
        toSave.setId(newComment.getId());
        toSave.setText(newComment.getText());
        toSave.setTitle(newComment.getTitle());
        toSave.setUser(user);
        return commentRepository.save(toSave);
    }  */

    public Comment createOneComment3(CommentCreateRequest newCommentRequest) {
        logger.info("comment to create: {}", newCommentRequest.toString());
        User user = userService.getOneUserById(newCommentRequest.getUser_id());
        if (user == null)
            return null;
        Comment toSave = new Comment();
        toSave.setId(newCommentRequest.getId());
        toSave.setText(newCommentRequest.getText());
        toSave.setTitle(newCommentRequest.getTitle());
        toSave.setUser(user);
        return commentRepository.save(toSave);
    }



}
