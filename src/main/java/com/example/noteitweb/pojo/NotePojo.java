package com.example.noteitweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class NotePojo {
    private Integer id;

    private String title;

    private String content;

    private Integer user_id;
}
