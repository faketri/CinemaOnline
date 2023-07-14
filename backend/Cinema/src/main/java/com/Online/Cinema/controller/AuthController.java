package com.Online.Cinema.controller;

import com.Online.Cinema.entity.User;
import com.Online.Cinema.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/auth/login")
    public String login(){
        return "login";
    }

    @GetMapping("/auth/register")
    public String register(){
        return "login";
    }

    @PostMapping("/auth/login/")
    public String PostLogin(){
        return "redirect:/index";
    }

    @PostMapping("/auth/register/")
    public String registration(Model Model, User user){
        userService.save(user);
        return "login";
    }
}
