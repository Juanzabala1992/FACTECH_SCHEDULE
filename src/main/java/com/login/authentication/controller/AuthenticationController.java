package com.login.authentication.controller;

import com.login.authentication.model.AuthenticationResponse;
import com.login.authentication.model.AuthenticatonRequest;
import com.login.authentication.model.RegisterRequest;
import com.login.authentication.model.ScheduleModel;
import com.login.authentication.repository.UserRepository;
import com.login.authentication.service.AuthenticationService;
import com.login.authentication.user.User;
import lombok.RequiredArgsConstructor;
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
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request , BindingResult result
    ){
        return ResponseEntity.ok(service.register(request, result));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticatonRequest request, BindingResult result
    ){
        return ResponseEntity.ok(service.authenticate(request, result));
    }
}
