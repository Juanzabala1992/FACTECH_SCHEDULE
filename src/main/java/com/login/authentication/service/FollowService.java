package com.login.authentication.service;

import com.login.authentication.exceptions.ApiRequestException;
import com.login.authentication.exceptions.ApiRequestExceptionValid;
import com.login.authentication.model.ActividadesModel;
import com.login.authentication.model.FollowModel;
import com.login.authentication.model.Profile;
import com.login.authentication.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FollowService {

    @Autowired
    private FollowRepository repositorio;

    public ResponseEntity<Optional<FollowModel>> getByUserId(Optional<FollowModel> data) {
        if (data.isEmpty() || data == null) {
            throw new ApiRequestException("No hay datos para el usuario");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }
    }

    public ResponseEntity<List<FollowModel>> setData(List<FollowModel> profileData, BindingResult result) {
        if (result.hasErrors()) {
            if (!result.getFieldError().getDefaultMessage().isEmpty()) {
                throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());

            } else {
                throw new ApiRequestExceptionValid("Datos no validos");
            }

        } else {
            List<FollowModel> savedProfiles = new ArrayList<>();
            for (FollowModel profileDatas : profileData) {
                String id_profile = profileDatas.getFollowId();
                Optional<FollowModel> data = repositorio.findByFollowId(id_profile);
                if (data.isEmpty()) {
                    FollowModel savedProfile = repositorio.save(profileDatas);
                    savedProfiles.add(savedProfile);
                } else {
                    throw new ApiRequestExceptionValid("Perfil ya existe");
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(savedProfiles);
        }
    }
}
