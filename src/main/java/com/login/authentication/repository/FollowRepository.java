package com.login.authentication.repository;

import com.login.authentication.model.FollowModel;
import com.login.authentication.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<FollowModel, String> {
    Optional<FollowModel> findByFollowId(String id);

    Optional<FollowModel> findByIdUser(String id);
}
