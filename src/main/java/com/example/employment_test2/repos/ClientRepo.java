package com.example.employment_test2.repos;

import com.example.employment_test2.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<ClientModel, String> {

    List<ClientModel> findAllByGuid(String guid);
    ClientModel findByGuid(String guid);
}
