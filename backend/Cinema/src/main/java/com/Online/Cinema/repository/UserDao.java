package com.Online.Cinema.repository;

import com.Online.Cinema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByLogin(String login);
    void removeById(Long id);
}
