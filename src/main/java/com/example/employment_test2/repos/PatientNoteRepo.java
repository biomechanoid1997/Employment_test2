package com.example.employment_test2.repos;

import com.example.employment_test2.models.PatientNoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientNoteRepo extends JpaRepository<PatientNoteModel, Long> {

}
