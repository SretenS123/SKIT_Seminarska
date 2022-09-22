package com.example.seminarksa_wp.service;

import com.example.seminarksa_wp.model.Dto.UserDto;
import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    UserDetails loadUserByUsername(String searchKey);

    User register(String name, String surname, String phoneNumber, String email, String imageUrl, String password, String reTypePassword, String country, Role role);
    User register1(String name, String surname, String phoneNumber, String email, MultipartFile file, String password, String reTypePassword, String country, Role role);

    User edit(Long id, String name, String surname, String phoneNumber, String email,String imageUrl, String password, String country);

    void deleteById(Long id);
}
