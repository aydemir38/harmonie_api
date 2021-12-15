package com.project.campProject.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @JsonProperty("username")
   private String userName;

   private String password;

   public User() {
   }

   public User(Long id, String userName, String password) {
      this.id = id;
      this.userName = userName;
      this.password = password;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
