package com.example.noteitweb.service.impl;

import com.example.noteitweb.entity.User;
import com.example.noteitweb.pojo.UserPojo;
import com.example.noteitweb.repository.UserRepository;
import com.example.noteitweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveData(UserPojo userPojo) {
        User user = new User();
        user.setFullName(userPojo.getFullName());
        user.setEmail(userPojo.getEmail());
        user.setPassword(passwordEncoder.encode(userPojo.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User registerUser(UserPojo userPojo) {
        User user = new User();
        user.setFullName(userPojo.getFullName());
        user.setEmail(userPojo.getEmail());
        user.setPassword(passwordEncoder.encode(userPojo.getPassword()));
        return userRepository.save(user); // Save and automatically generate ID
    }

    @Override
    public User authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user; // User object now includes the generated ID
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new UserDetailsImpl(user);
    }
}
