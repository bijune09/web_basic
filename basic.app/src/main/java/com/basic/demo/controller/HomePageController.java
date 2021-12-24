package com.basic.demo.controller;

import com.basic.demo.dto.SignInAccountDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping
    public String homepage(){
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login/login-page";
    }

    @GetMapping("/signin")
    public String signInPage(Model model){
        model.addAttribute("signInAccountDto",new SignInAccountDto());
        return "login/sign-in-page";
    }

}
