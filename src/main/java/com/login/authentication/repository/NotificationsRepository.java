package com.login.authentication.repository;

import com.login.authentication.model.FollowModel;
import com.login.authentication.model.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationsRepository extends JpaRepository<Notifications, String> {

    Optional<Notifications> findByIdUser(String id);
    List<Notifications> findByEmail(String id);
}
