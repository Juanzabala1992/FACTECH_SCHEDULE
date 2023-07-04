package com.login.authentication.controller;


import com.login.authentication.model.CompanyModel;
import com.login.authentication.model.FollowModel;
import com.login.authentication.model.Profile;
import com.login.authentication.repository.CompanyRepository;
import com.login.authentication.repository.FollowRepository;
import com.login.authentication.service.CompanyService;
import com.login.authentication.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {

    @Autowired
    private final CompanyRepository companyRepository;

    @Autowired
    private final CompanyService companyService;


    @PostMapping("/company/save")
    public ResponseEntity<CompanyModel> saveCompany(@Valid @RequestBody CompanyModel company, BindingResult result) {
        return companyService.setData(company, result);
    }

    @GetMapping("/company/{client}")
    public ResponseEntity <Optional<CompanyModel>>  getCompany(@PathVariable String client){
        Optional<CompanyModel> follow = companyRepository.findByIdClient(client);
        return companyService.getByUserId(follow);
    }

    @GetMapping("/company/all")
    public ResponseEntity <List<CompanyModel>> listAllSchedules(){
        List<CompanyModel> all = companyRepository.findAll();
        return companyService.getAllData(all);
    }

}
