package com.login.authentication.service;

import com.login.authentication.exceptions.ApiRequestException;
import com.login.authentication.exceptions.ApiRequestExceptionValid;
import com.login.authentication.model.Profile;
import com.login.authentication.model.ScheduleModel;
import com.login.authentication.repository.ProfileRepository;
import com.login.authentication.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repositorio;

    public ResponseEntity<List<Profile>> getAllData(List<Profile> lista){
        if(lista.isEmpty() || lista == null) {
            throw new ApiRequestException("No hay usuarios en la base");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(lista);
        }
    }

    public ResponseEntity<Optional<Profile>> getByUserId(Optional<Profile> data){
        if(data.isEmpty() || data == null) {
            throw new ApiRequestException("No hay datos para el usuario");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }
    }
    public ResponseEntity<Profile> setData(Profile profileData, BindingResult result){
        if(result.hasErrors()) {
            if(!result.getFieldError().getDefaultMessage().isEmpty()) {
                throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());

            }else {
                throw new ApiRequestExceptionValid("Datos no validos");
            }

        }else {
            String id_profile = profileData.getEmail();
            Optional<Profile> data = repositorio.findByIdUser(id_profile);
            if(data.isEmpty()) {
                Profile schRepo = repositorio.save(profileData);
                return ResponseEntity.status(HttpStatus.OK).body(profileData);
            }else {
                throw new ApiRequestExceptionValid("Perfil ya existe");
            }
        }
    }
}
