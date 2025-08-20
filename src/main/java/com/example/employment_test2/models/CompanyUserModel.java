package com.example.employment_test2.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="company_user")
@Data
public class CompanyUserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    Long id;

    @Column(name = "login")
    String login;

}
