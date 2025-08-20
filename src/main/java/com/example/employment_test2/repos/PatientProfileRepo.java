package com.example.employment_test2.repos;

import com.example.employment_test2.models.PatientProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientProfileRepo extends JpaRepository<PatientProfileModel, Long> {
    PatientProfileModel findByOldClientGuid(String OldClientGuid);

}
