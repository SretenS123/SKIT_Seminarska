package com.example.seminarksa_wp.service.impl;

import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.enumerations.Role;
import com.example.seminarksa_wp.model.exceptions.*;
import com.example.seminarksa_wp.repository.UserRepository;
import com.example.seminarksa_wp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername (String searchKey) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(searchKey).orElseThrow(()->new UserEmailNotExistException(searchKey));
    }

    @Override
    public User register(String name, String surname, String phoneNumber, String email, String imageUrl, String password, String reTypePassword, String country, Role role) {
        if(name==null || name.isEmpty())
            throw new InvalidArgumentsException();
        if(!password.equals(reTypePassword))
            throw new PassworDoNotMatchException();
        if(this.userRepository.findByEmail(email).isPresent())
        {
            throw new EmailAlreadyExistsException(email);
        }
        User u = new User(name,surname,phoneNumber,email,imageUrl,passwordEncoder.encode(password),country,role);
        userRepository.save(u);
        return u;
    }

    @Override
    public User register1(String name, String surname, String phoneNumber, String email, MultipartFile file, String password, String reTypePassword, String country, Role role) {
        if(name==null || name.isEmpty())
            throw new InvalidArgumentsException();
        if(!password.equals(reTypePassword))
            throw new PassworDoNotMatchException();
        if(this.userRepository.findByEmail(email).isPresent())
        {
            throw new EmailAlreadyExistsException(email);
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("Not a proper file!");
        }
        String imageUrl = null;
        try {
            imageUrl = Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        User u = new User(name,surname,phoneNumber,email,imageUrl,passwordEncoder.encode(password),country,role);
        userRepository.save(u);
        return u;
    }

    @Override
    public User edit(Long id, String name, String surname, String phoneNumber, String email,String imageUrl, String password, String country) {
        User user = this.userRepository.findById(id).orElseThrow(()->new UserIdNotFoundException(id));
        user.setName(name);
        user.setSurname(surname);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setPassword(password);
        user.setCountry(country);
        user.setImageUrl(imageUrl);
        return this.userRepository.save(user);


    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
