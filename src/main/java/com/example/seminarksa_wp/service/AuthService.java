package com.example.seminarksa_wp.service;

import com.example.seminarksa_wp.model.User;

public interface AuthService {
    User login(String email, String password);



}
