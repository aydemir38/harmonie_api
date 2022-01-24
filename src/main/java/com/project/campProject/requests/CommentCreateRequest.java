package com.project.campProject.requests;


import lombok.Data;

@Data
public class CommentCreateRequest {

  private  Long id;
  private  String text;
  private  String title;
  private  Long user_id;


}
