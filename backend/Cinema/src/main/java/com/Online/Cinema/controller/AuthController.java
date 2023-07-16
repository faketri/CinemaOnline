package com.Online.Cinema.controller;

import com.Online.Cinema.entity.User;
import com.Online.Cinema.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/auth/login/")
    public String login(){
        return "login";
    }

    @GetMapping("/auth/register/")
    public String registration(){
        return "register";
    }

    @PostMapping("/auth/login/")
    public String PostLogin(){
        return "index";
    }

    @PostMapping("/auth/register/")
    public String registration(User user){
        userService.save(user);
        return "login";
    }

    @GetMapping("/auth/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null)
            request.getSession().invalidate();

        return "redirect:/";
    }
}
