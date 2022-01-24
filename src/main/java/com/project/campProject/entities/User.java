package com.project.campProject.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user")
@Data
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @JsonProperty("id")
   private Long id;

   @JsonProperty("username")
   private String userName;

   @JsonProperty("password")
   private String password;

   public User() {
   }

   public User(Long id, String userName, String password) {
      this.id = id;
      this.userName = userName;
      this.password = password;
   }


}
