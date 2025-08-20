package com.example.employment_test2.repos;

import com.example.employment_test2.models.NoteModel;
import com.example.employment_test2.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<NoteModel, String> {
  //  NoteModel findByGuid(String guid);
}
