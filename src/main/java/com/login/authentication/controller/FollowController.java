package com.login.authentication.controller;


import com.login.authentication.model.FollowModel;
import com.login.authentication.model.Profile;
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
    private final FollowService followService;


    @PostMapping("/follow/save")
    public ResponseEntity<List<FollowModel>> saveSchedule(@Valid @RequestBody List<FollowModel> follow, BindingResult result) {
        return followService.setData(follow, result);
    }

    @GetMapping("/follow/{id}")
    public ResponseEntity <Optional<FollowModel>>  getSchByUser(@PathVariable String id){
        Optional<FollowModel> follow = followRepository.findByFollowId(id);
        return followService.getByUserId(follow);
    }

}
