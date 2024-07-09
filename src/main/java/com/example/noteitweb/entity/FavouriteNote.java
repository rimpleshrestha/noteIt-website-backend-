package com.example.noteitweb.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="favouriteNotes")

public class FavouriteNote {

    @Id
    @SequenceGenerator(name="users_seq_gen",sequenceName="users_id_seq",allocationSize=1)
    @GeneratedValue(generator="users_seq_gen",strategy = GenerationType.SEQUENCE )
    private Integer id;

    @Column( name="title",nullable=false)
    private String title;

    @Column(name="content",nullable= false)
    private String content;
}
