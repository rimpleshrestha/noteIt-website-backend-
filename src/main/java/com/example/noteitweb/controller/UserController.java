package com.example.noteitweb.controller;

import com.example.noteitweb.pojo.UserPojo;
import com.example.noteitweb.entity.User;
import com.example.noteitweb.service.UserService;
import com.example.noteitweb.utils.JwtUtil;
import com.example.noteitweb.controller.PasswordChangeRequest; // Ensure the package path is correct
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserPojo userPojo) {
        User existingUser = userService.findByEmail(userPojo.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        User registeredUser = userService.registerUser(userPojo);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserPojo userPojo) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userPojo.getEmail(), userPojo.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtil.generateToken(userDetails);

            // Retrieve the user object to get the user ID
            User user = userService.findByEmail(userPojo.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("token", jwt);
            response.put("userId", user.getId()); // Add user ID to the response

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordChangeRequest request) {
        try {
            boolean isUpdated = userService.updatePassword(request.getEmail(), request.getOldPassword(), request.getNewPassword());
            if (isUpdated) {
                return ResponseEntity.ok("Password updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password is incorrect");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating password");
        }
    }
}
