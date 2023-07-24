package com.example.securityservice.service;

import com.example.securityservice.entity.UserCredential;
import com.example.securityservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserCredentialRepository repository;

    public UserCredential findByName(String name){
        return repository.findByName(name).orElseThrow();
    }

}
