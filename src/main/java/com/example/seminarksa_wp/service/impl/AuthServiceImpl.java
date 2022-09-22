package com.example.seminarksa_wp.service.impl;

import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.exceptions.InvalidArgumentsException;
import com.example.seminarksa_wp.model.exceptions.InvalidUserCredentialsException;
import com.example.seminarksa_wp.repository.UserRepository;
import com.example.seminarksa_wp.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();
        return userRepository.findByEmailAndPassword(email, passwordEncoder.encode(password)).orElseThrow(InvalidUserCredentialsException::new);
    }
}
