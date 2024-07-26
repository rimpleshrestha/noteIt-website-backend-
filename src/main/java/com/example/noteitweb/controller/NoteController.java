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
    public ResponseEntity<String> save(@RequestBody NotePojo notePojo) {
        // Validate input
        if (notePojo.getTitle() == null || notePojo.getTitle().trim().isEmpty() ||
                notePojo.getContent() == null || notePojo.getContent().trim().isEmpty() ||
                notePojo.getUser_id() == null) {
            return ResponseEntity.badRequest().body("Title, content, and user ID are required");
        }

        try {
            noteService.saveData(notePojo);
            return ResponseEntity.status(HttpStatus.CREATED).body("Note created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public List<Note> getAll() {
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

    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<List<Note>> getByUser(@PathVariable Integer userId) {
        try {
            List<Note> notes = noteService.findByUserId(userId);
            return ResponseEntity.ok(notes);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
