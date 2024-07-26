package com.example.noteitweb.service;

import com.example.noteitweb.entity.Note;
import com.example.noteitweb.pojo.NotePojo;

import java.util.List;

public interface NoteService {
    void saveData(NotePojo notePojo);

    List<Note> findAll();

    void updateData(Integer id, NotePojo notePojo); // Use Integer for id

    void deleteData(Integer id);

    List<Note> findByUserId(Integer userId); // New method to fetch notes by user ID
}
