package com.Online.Cinema.repository;

import com.Online.Cinema.entity.User;
import com.Online.Cinema.entity.enums.ERole;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserDao extends Repository<User, Long> {

    Optional<User> findAll();
    User findById(Long id);
    Optional<User> findByLogin(String login);
    Optional<User> findByLoginAndRole(String login, ERole role);
    void save(User user);
}
