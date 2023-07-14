package com.login.authentication.controller;


import com.login.authentication.model.Notifications;
import com.login.authentication.model.Profile;
import com.login.authentication.repository.NotificationsRepository;
import com.login.authentication.security.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationsController {
    @Autowired
    NotificationsRepository notificationsRepository;

    @Autowired
    NotificationService notificationService;

    @GetMapping("/notifications/{id}")
    public ResponseEntity<List<Notifications>> getByUser(@PathVariable String id){
        List<Notifications> profile = notificationsRepository.findByEmail(id);
        return notificationService.getByUser(profile);
    }
}
