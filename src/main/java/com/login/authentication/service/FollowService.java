package com.login.authentication.service;

import com.login.authentication.exceptions.ApiRequestException;
import com.login.authentication.exceptions.ApiRequestExceptionValid;
import com.login.authentication.model.*;
import com.login.authentication.repository.CompanyRepository;
import com.login.authentication.repository.FollowExpandRepository;
import com.login.authentication.repository.FollowRepository;
import com.login.authentication.repository.ProfileRepository;
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

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private FollowExpandRepository followExpandRepository;

    public ResponseEntity<Optional<FollowModelExpand>> getByUserId(Optional<FollowModelExpand> data) {
        if (data.isEmpty() || data == null) {
            throw new ApiRequestException("No hay datos para el usuario");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }
    }

    public ResponseEntity<List<FollowModelExpand>> getAllData(List<FollowModelExpand> lista){
        if(lista.isEmpty() || lista == null) {
            throw new ApiRequestException("No hay usuarios en la base");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(lista);
        }
    }
    public ResponseEntity<FollowModelExpand> setData(FollowModelExpand followData, BindingResult result){
        if(result.hasErrors()) {
            if(!result.getFieldError().getDefaultMessage().isEmpty()) {
                throw new ApiRequestExceptionValid(result.getFieldError().getDefaultMessage());

            }else {
                throw new ApiRequestExceptionValid("Datos no validos");
            }

        }else {
            String id_fll = followData.getFollowId();
            Optional<FollowModelExpand> data = followExpandRepository.findByFollowId(id_fll);
            if(data.isEmpty()) {
                FollowModelExpand fllRepo = followExpandRepository.save(followData);

                List<FollowModel> actividades = followData.getFollow();
                for (FollowModel actividad : actividades) {
                    actividad.setFollowModel(fllRepo);
                    repositorio.save(actividad);
                }


                return ResponseEntity.status(HttpStatus.OK).body(followData);
            }else {
                throw new ApiRequestExceptionValid("Horario ya existe");
            }
        }
    }
    public ResponseEntity <List<ProfileCompanyJoin>> joinTablesByCliente(String cliente) {

        List<Profile> profiles = profileRepository.findByCliente(cliente);

        List<CompanyModel> companies = companyRepository.findByClient(cliente);

        List<ProfileCompanyJoin> joinList = new ArrayList<>();

        for (Profile profile : profiles) {
            for (CompanyModel company : companies) {
                joinList.add(new ProfileCompanyJoin(profile, company));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(joinList);
    }

    public ResponseEntity<List<ProfileCompanyJoin>> joinTablesByAllClients() {
        List<Profile> profiles = profileRepository.findAll();
        List<CompanyModel> companies = companyRepository.findAll();
        List<ProfileCompanyJoin> joinList = new ArrayList<>();

        for (Profile profile : profiles) {
            for (CompanyModel company : companies) {
                if (profile.getCliente().equals(company.getClient())) {
                    joinList.add(new ProfileCompanyJoin(profile, company));
                }
            }
        }

        return ResponseEntity.ok(joinList);
    }
}
