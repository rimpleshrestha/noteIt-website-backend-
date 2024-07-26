package com.example.noteitweb.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotePojo {

    private Integer id;
    private String title;
    private String content;
    private Integer user_id; // Make sure this field matches your front-end's data
}
