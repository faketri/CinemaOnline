package com.Online.Cinema.repository;

import com.Online.Cinema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserDao extends JpaRepository<User, Long> {

    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByLogin(String login);
    }
