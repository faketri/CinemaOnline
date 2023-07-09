package com.Online.Cinema.controller;

import com.Online.Cinema.entity.User;
import com.Online.Cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value="/user/profile/{id}")
    public User getUserById(@PathVariable(value = "id") Long id){
        return userService.findById(id).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Not founded user by id - %d", id))
        );
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
