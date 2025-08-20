package com.example.employment_test2.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="patient_profile")
@Data
public class PatientProfileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "Id")
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "old_client_guid")
    String oldClientGuid;

    @Column(name = "status_id")
    Integer statusId;
}
