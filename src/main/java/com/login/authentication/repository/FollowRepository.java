package com.login.authentication.repository;

import com.login.authentication.model.FollowModel;
import com.login.authentication.model.Profile;
import com.login.authentication.model.ProfileCompanyJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<FollowModel, String> {
    //Optional<FollowModel> findByFollowId(String id);
    Optional<FollowModel> findByIdUser(String id);
}
