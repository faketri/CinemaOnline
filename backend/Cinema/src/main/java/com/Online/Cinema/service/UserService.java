package com.Online.Cinema.service;

import com.Online.Cinema.entity.User;
import com.Online.Cinema.entity.enums.ERole;
import com.Online.Cinema.repository.UserDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserDao userDao;

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

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.getRole().stream()
                        .map(role ->
                                new SimpleGrantedAuthority(role.name())
                        )
                        .collect(Collectors.toList())
        );
    }

    public UserDetails loadUserById(Long id){

        User user = findById(id).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Not found user for id - %d", id))
        );

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.getRole().stream()
                        .map(role ->
                                new SimpleGrantedAuthority(role.name())
                        )
                        .collect(Collectors.toList())
        );
    }

    public void save(User user){
        if(user == null) return;

        user.setRole(Set.of(ERole.DEFAULT));
        user.setPassword(
                new BCryptPasswordEncoder().encode(
                        user.getPassword() + "VBNM<>SPIUDO@*&$)@(&J$F()@)($@S)D_")
        );

        userDao.save(user);
    }

    public void removeById(Long id){
        userDao.removeById(id);
    }
}
