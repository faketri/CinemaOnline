package com.Online.Cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @PostMapping("/auth/login")
    public String login(Model Model){
        Model.addAttribute("action", "/user/create");
        return "login";
    }

    @PostMapping("/auth/registration")
    public String registration(Model Model){
        Model.addAttribute("action", "/user/register");
        return "login";
    }
}
