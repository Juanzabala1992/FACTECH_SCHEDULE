package com.login.authentication.repository;

import com.login.authentication.model.Profile;
import com.login.authentication.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository  extends JpaRepository<Profile, String> {
    Optional<Profile> findByEmail(String email);

    Optional<Profile> findByIdUser(String idUser);
}