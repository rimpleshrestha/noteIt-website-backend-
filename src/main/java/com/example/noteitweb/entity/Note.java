package com.example.noteitweb.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notes", uniqueConstraints = {
        @UniqueConstraint(name = "UNIQUE_note_id", columnNames = "id")
})
public class Note {

    @Id
    @SequenceGenerator(name = "note_seq_gen", sequenceName = "note_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "note_seq_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
