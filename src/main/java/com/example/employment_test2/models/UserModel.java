package com.example.employment_test2.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="old_user_table")
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserTableId")
   private Long id;

    @Column(name = "Login")
   private String login;

    @Column(name = "User_Name")
   private String userName;

    @Column(name = "Password")
  private String password;


}
