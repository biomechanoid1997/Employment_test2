package com.example.employment_test2.repos;

import com.example.employment_test2.models.CompanyUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyUserRepo extends JpaRepository<CompanyUserModel, Long > {

    CompanyUserModel findByLogin(String login);

}
