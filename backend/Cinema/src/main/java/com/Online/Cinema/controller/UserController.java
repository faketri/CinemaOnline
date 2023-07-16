package com.Online.Cinema.controller;

import com.Online.Cinema.entity.User;
import com.Online.Cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value="/user/profile/{id}")
    public String  getUserById(@PathVariable(value = "id") Long id,
                               Model model){

        model.addAttribute("user",
                userService
                .findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException(""))
        );

        return "userProfile";
    }

    @PostMapping("/create")
    public void createUser(User user){
        userService.save(user);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        userService.removeById(id);
    }

}
