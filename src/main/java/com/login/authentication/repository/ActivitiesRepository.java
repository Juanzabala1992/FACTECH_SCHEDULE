package com.login.authentication.repository;

import com.login.authentication.model.ActividadesModel;
import com.login.authentication.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivitiesRepository extends JpaRepository<ActividadesModel, String> {
}
