package com.Online.Cinema.service;

import com.Online.Cinema.entity.Image;
import com.Online.Cinema.entity.User;
import com.Online.Cinema.entity.enums.ERole;
import com.Online.Cinema.repository.ImageDao;
import com.Online.Cinema.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findById(Long id){
        return userDao.findById(id);
    }
    public Optional<User> findByLogin(String login){
        return userDao.findByLogin(login);
    }

    public void save(User user){

        /*Image image = new Image();
        image.setPhoto(new byte[1]);
        image.setUser(user);

        user.setAvatar(
                image
        );*/

        user.getRole().add(ERole.DEFAULT);
        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword())
        );

        userDao.save(user);
    }

    public void removeById(Long id){
        userDao.removeById(id);
    }
}
