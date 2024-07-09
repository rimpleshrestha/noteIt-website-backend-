package com.example.noteitweb.repository;


import com.example.noteitweb.entity.FavouriteNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FavouriteNoteRepository extends JpaRepository<FavouriteNote, Integer> {
}
