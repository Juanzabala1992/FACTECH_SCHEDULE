package com.login.authentication.controller;


import com.login.authentication.model.*;
import com.login.authentication.repository.FollowExpandRepository;
import com.login.authentication.repository.FollowRepository;
import com.login.authentication.repository.ProfileRepository;
import com.login.authentication.service.FollowService;
import com.login.authentication.service.ProfileService;
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
public class FollowController {

    @Autowired
    private final FollowRepository followRepository;

    @Autowired
    private final FollowExpandRepository followExpandRepository;

    @Autowired
    private final FollowService followService;

    @PostMapping("/follow/save")
    public ResponseEntity<FollowModelExpand> saveFollow(@Valid @RequestBody FollowModelExpand follow, BindingResult result) {
        return followService.setData(follow, result);
    }

    @GetMapping("/follow/{id}")
    public ResponseEntity <Optional<FollowModelExpand>>  getFollow(@PathVariable String id){
        Optional<FollowModelExpand> follow = followExpandRepository.findByFollowId(id);
        return followService.getByUserId(follow);
    }

    @GetMapping("/follow/all")
    public ResponseEntity <List<FollowModelExpand>> listAllSchedules(){
        List<FollowModelExpand> all = followExpandRepository.findAll();
        return followService.getAllData(all);
    }

    @GetMapping("/follow/client/{cliente}")
    public ResponseEntity <List<ProfileCompanyJoin>>  getFollowInfo(@PathVariable String cliente){
        return followService.joinTablesByCliente(cliente);
    }

    @GetMapping("/follow/client/all")
    public ResponseEntity<List<ProfileCompanyJoin>> joinTablesByAllClients() {
        return followService.joinTablesByAllClients();
    }

}
