package com.netflixmoviematch.controller;

import com.netflixmoviematch.model.registration.RegistrationRequest;
import com.netflixmoviematch.service.impl.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegistrationController {


    private RegistrationService registrationService;

    @PostMapping(path = "/api/v1/registration")
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

}
