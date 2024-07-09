package com.example.noteitweb.service.impl;

import com.example.noteitweb.entity.User;
import com.example.noteitweb.pojo.UserPojo;
import com.example.noteitweb.repository.UserRepository;
import com.example.noteitweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveData(UserPojo userPojo) {

        User user = new User();
        user.setId(userPojo.getId());
        user.setFullName(userPojo.getFullName());

        userRepository.save(user);

    }
}
