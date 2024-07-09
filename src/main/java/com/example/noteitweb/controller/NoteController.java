package com.example.noteitweb.controller;

import com.example.noteitweb.entity.Note;
import com.example.noteitweb.pojo.NotePojo;
import com.example.noteitweb.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody NotePojo notePojo) {
        try {
            noteService.updateData(id, notePojo);
            return ResponseEntity.ok("Note updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            noteService.deleteData(id);
            return ResponseEntity.ok("Note deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}