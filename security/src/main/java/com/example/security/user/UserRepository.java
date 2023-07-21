package com.example.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserRepository extends JpaRepository <User,Integer>{
    Optional<User> findByEmail(String email);
}
