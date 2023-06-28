package com.login.authentication.controller;


import com.login.authentication.repository.ScheduleRepository;
import com.login.authentication.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class Forecast {
    @Autowired
    private ScheduleRepository repositorio;

    @Autowired
    private ScheduleService scheduleService;

}
