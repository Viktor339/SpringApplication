package com.springapplication.repository;

import com.springapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsernameAndPassword(String username,String password);
    Optional<User> findUserById(Long id);
    Optional<User> findByUsername(String username);
}
