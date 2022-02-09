package com.netflixmoviematch.service.impl;

import com.netflixmoviematch.model.registration.RegistrationRequest;
import com.netflixmoviematch.model.user.AppUser;
import com.netflixmoviematch.model.user.AppUserRole;
import com.netflixmoviematch.service.validators.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RegistrationService {

    private EmailValidator emailValidator;

    private final AppUserService appUserService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("Email not valid!");
        }

        AppUser appUser = new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER

        );

        return appUserService.signUpUser(appUser);
    }
}
