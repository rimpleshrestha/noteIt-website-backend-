package com.example.noteitweb.pojo;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class FavouriteNotePojo {
    private Integer id;

    private String title;

    private String content;
}
