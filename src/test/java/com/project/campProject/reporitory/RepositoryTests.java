package com.project.campProject.reporitory;

import com.project.campProject.entities.Comment;
import com.project.campProject.entities.User;
import com.project.campProject.repository.CommentRepository;
import com.project.campProject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void whenInsertUser_thenReturnUser() {
        User user = new User(1L,"foo","bar");
        userRepository.save(user);
        Optional<User> optionalUser = userRepository.findById(1L);
        assertThat(optionalUser.isPresent()).isTrue();
    }


    @Test
    public void whenInsertComment_thenReturnComment() {
        Comment comment = new Comment();
        comment.setId(53L);
        comment.setTitle("Comment Title SMA");
        comment.setText("Text of comment");

        User user = new User(1L,"foo","bar");
        comment.setUser(user);

        Comment comment1 = commentRepository.save(comment);
        assertThat(comment1).isNotNull();
    }


    @Test
    public void whenFindComment_thenReturnUser() {
        Optional<Comment> optionalComment = commentRepository.findById(1L);
        assertThat(optionalComment.isPresent()).isNotNull();

        Comment comment = optionalComment.get();
        assertThat(comment.getText()).isNotNull();


    }




}
