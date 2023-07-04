package com.login.authentication.service;


import com.login.authentication.exceptions.ApiRequestException;
import com.login.authentication.exceptions.ApiRequestExceptionValid;
import com.login.authentication.model.CompanyModel;
import com.login.authentication.model.Profile;
import com.login.authentication.repository.CompanyRepository;
import com.login.authentication.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repositorio;

    public ResponseEntity<List<CompanyModel>> getAllData(List<CompanyModel> lista){
        if(lista.isEmpty() || lista == null) {
            throw new ApiRequestException("No hay usuarios en la base");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(lista);
        }
    }

    public ResponseEntity<Optional<CompanyModel>> getByUserId(Optional<CompanyModel> data){

        if(data.isEmpty() || data == null) {
            throw new ApiRequestException("No hay datos para el usuario");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }
    }
    public ResponseEntity<CompanyModel> setData(CompanyModel clientData, BindingResult result){
        if(result.hasErrors()) {
            if(!result.getFieldError().getDefaultMessage().isEmpty()) {
                throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());

            }else {
                throw new ApiRequestExceptionValid("Datos no validos");
            }

        }else {
            String client = clientData.getClient();
            String id = clientData.getIdClient();
            Optional<CompanyModel> data = repositorio.findByIdClient(id);
            Optional<CompanyModel> data_client = repositorio.findOptionalByClient(client);

            if(data.isEmpty() && data_client.isEmpty()) {
                CompanyModel schRepo = repositorio.save(clientData);
                return ResponseEntity.status(HttpStatus.OK).body(clientData);
            }else {
                throw new ApiRequestExceptionValid("Perfil ya existe");
            }
        }
    }

}
