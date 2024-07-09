package com.example.noteitweb.controller;

import com.example.noteitweb.entity.Note;
import com.example.noteitweb.pojo.NotePojo;
import com.example.noteitweb.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Note")
@RequiredArgsConstructor

public class NoteController {
    private final NoteService noteService;

    @PostMapping("/save")
    public void save(@RequestBody NotePojo notePojo) {
        this.noteService.saveData(notePojo);
    }

    @GetMapping("/get")
    public List<Note> getAll(){
        return noteService.findAll();
    }
}
