package com.project.campProject.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @Column(length = 100)
    private String title;


    @Lob
    @Column(columnDefinition = "text")
    private String text;

    public Comment() {
    }

    public Comment(Long id, User user, String title, String text) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.text = text;
    }
/*
    public Long getUserId() {   //für toSave)
        return getUserId();
    }*/
}
