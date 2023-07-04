package com.login.authentication.repository;

import com.login.authentication.model.CompanyModel;
import com.login.authentication.model.FollowModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyModel, String> {

    Optional<CompanyModel> findByIdClient(String id);
    List<CompanyModel> findByClient(String id);

    Optional<CompanyModel> findOptionalByClient(String id);
}
