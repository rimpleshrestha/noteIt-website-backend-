package com.example.noteitweb.service;

import com.example.noteitweb.entity.Note;
import com.example.noteitweb.pojo.NotePojo;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface NoteService {
    void saveData(NotePojo notePojo);

    List<Note> findAll();

    void updateData(Integer id, NotePojo notePojo); // Use Long for id

    void deleteData(Integer id);


}
