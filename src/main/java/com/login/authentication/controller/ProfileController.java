package com.login.authentication.controller;

import com.login.authentication.model.Profile;
import com.login.authentication.model.ScheduleModel;
import com.login.authentication.repository.ProfileRepository;
import com.login.authentication.repository.UserRepository;
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
public class ProfileController {

    @Autowired
    private final ProfileRepository profileRepository;

    @Autowired
    private final ProfileService profileService;

    @PostMapping("/profile/save")
    public ResponseEntity<Profile> saveSchedule(@Valid @RequestBody Profile profile, BindingResult result) {
        return profileService.setData(profile, result);
    }

    @GetMapping("/profile/all")
    public ResponseEntity <List<Profile>> listAllSchedules(){
        List<Profile> all = profileRepository.findAll();
        return profileService.getAllData(all);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity <Optional<Profile>>  getSchByUser(@PathVariable String id){
        Optional<Profile> profile = profileRepository.findByIdUser(id);
        return profileService.getByUserId(profile);
    }
}

