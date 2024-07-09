package com.example.noteitweb.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "UNIQUE_user_id", columnNames = "id")
})
public class User {

    @Id
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_seq_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    // Other fields and annotations as needed
}
