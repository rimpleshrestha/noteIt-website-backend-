package com.example.noteitweb.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {
    private Integer id;

    private String email;

    private String fullName;

    private String password;
}
