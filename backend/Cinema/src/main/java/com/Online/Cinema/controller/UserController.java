package com.Online.Cinema.controller;

import com.Online.Cinema.entity.User;
import com.Online.Cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value="/user/{id}")
    public User getUserById(@PathVariable(value = "id") Long id){
        return userService.findById(id).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Not founded user by id - %d", id))
        );
    }

    @PostMapping(value = "/register")
    public void createUser(@PathVariable(value = "req") String req){

        System.out.println(req);

    }

}
