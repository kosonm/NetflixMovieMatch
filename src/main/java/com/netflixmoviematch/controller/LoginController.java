package com.netflixmoviematch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @GetMapping("")
    public ModelAndView homeView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }


    @RequestMapping("/login")
    public ModelAndView loginView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginPage");
        return modelAndView;
    }

    @RequestMapping("/login?error=true")
    public ModelAndView loginError(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginPage");
        modelAndView.addObject("loginError", true);
        return modelAndView;

    }

}
