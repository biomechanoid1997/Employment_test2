package com.example.employment_test2.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Clients")
@Data
public class ClientModel {

    @Id
    @Column(name = "Guid")
    String guid;

    @Column(name = "agency")
    String agency;

    @Column(name = "firstName")
    String firstName;

    @Column(name = "lastName")
    String lastName;

    @Column(name = "status")
    String status;

    @Column(name = "dob")
    String dob;

    @Column(name = "createdDateTime")
    String createdDateTime;

}
