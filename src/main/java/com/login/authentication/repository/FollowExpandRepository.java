package com.login.authentication.repository;

import com.login.authentication.model.FollowModel;
import com.login.authentication.model.FollowModelExpand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowExpandRepository extends JpaRepository<FollowModelExpand, String> {
    Optional<FollowModelExpand> findByFollowId(String id);
}
