package com.example.noteitweb.controller;

import com.example.noteitweb.pojo.UserPojo;
import com.example.noteitweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor


public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public void save(@RequestBody UserPojo userPojo){
        this.userService.saveData(userPojo);
    }
}
