package com.Online.Cinema.service;

import com.Online.Cinema.entity.User;
import com.Online.Cinema.entity.enums.ERole;
import com.Online.Cinema.repository.UserDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
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

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = findByLogin(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("No Found User %s", username))
                );

        return new User(
                user.getLogin(),
                user.getPassword(),
                user.getRole()
        );
    }

    public void save(User user){

        user.getRole().add(ERole.DEFAULT);
        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword())
        );
        user.setActive(true);

        userDao.save(user);
    }

    public void removeById(Long id){
        userDao.removeById(id);
    }
}
