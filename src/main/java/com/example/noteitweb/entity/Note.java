package com.example.noteitweb.entity;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="notes" ,uniqueConstraints={
        @UniqueConstraint(name="UNIQUE_note_id",columnNames = "id")
})

public class Note {
    @Id
    @SequenceGenerator(name="users_seq_gen",sequenceName="users_id_seq",allocationSize=1)
    @GeneratedValue(generator="users_seq_gen",strategy = GenerationType.SEQUENCE )
    private Integer id;

    @Column( name="Title",nullable=false)
    private String title;

    @Column(name="Content",nullable= false)
    private String content;


    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
