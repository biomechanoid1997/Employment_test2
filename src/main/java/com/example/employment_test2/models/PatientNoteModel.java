package com.example.employment_test2.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="patient_note")
@Data
public class PatientNoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "created_date_time")
    String creationTime;

    @Column(name = "last_modified_date_time")
    String lastTimeModified;

    @Column(name = "created_by_user_id ")
    Long createdBy;

    @Column(name = "last_modified_by_user_id ")
    Long lastModifiedBy;

    @Column(name = "note")
    String note;

    @Column(name ="patient_id")
    Long patientId;
}
