package com.Online.Cinema.service;

import com.Online.Cinema.entity.User;
import com.Online.Cinema.entity.enums.ERole;
import com.Online.Cinema.repository.UserDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserDao userDao;

    public User findById(Long id){
        return userDao.findById(id);
    }
    public Optional<User> findByLogin(String login){
        return userDao.findByLogin(login);
    }
    public User findByLoginAndRole(String login, ERole role){
        return findByLoginAndRole(login, role);
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

    public void createNewUser(User user){
        user.setRole(Set.of(ERole.DEFAULT));
        userDao.save(user);
    }
}
