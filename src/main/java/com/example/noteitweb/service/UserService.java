package com.example.noteitweb.service;

import com.example.noteitweb.entity.User;
import com.example.noteitweb.pojo.UserPojo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void saveData(UserPojo userPojo);

    User registerUser(UserPojo userPojo);

    User authenticateUser(String email, String password);

    User findByEmail(String email);
}