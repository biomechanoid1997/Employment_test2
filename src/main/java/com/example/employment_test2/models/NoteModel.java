package com.example.employment_test2.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="noteTable")
@Data
public class NoteModel {

    @Id
    @Column(name = "Guid")
    String Guid;

    @Column(name = "loggedUser")
    String loggedUser;

    @Column(name = "clientGuid")
    String clientGuid;

    @Column(name = "DateTime")
    String dateTime;

    @Column(name = "createdDateTime")
    String createdDateTime;

    @Column(name = "Note")
    String note;

}
