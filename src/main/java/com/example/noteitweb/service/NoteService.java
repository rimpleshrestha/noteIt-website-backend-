package com.example.noteitweb.service;

import com.example.noteitweb.entity.Note;
import com.example.noteitweb.pojo.NotePojo;

import java.util.List;

public interface NoteService {
    void saveData(NotePojo notePojo);

    List<Note> findAll();
}
