package com.example.noteitweb.service.impl;

import com.example.noteitweb.entity.Note;
import com.example.noteitweb.entity.User;
import com.example.noteitweb.pojo.NotePojo;
import com.example.noteitweb.repository.NoteRepository;
import com.example.noteitweb.repository.UserRepository;
import com.example.noteitweb.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Override
    public void saveData(NotePojo notePojo) {
        // Validate user existence
        User user = userRepository.findById(notePojo.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create and save note
        Note note = new Note();
        note.setTitle(notePojo.getTitle());
        note.setContent(notePojo.getContent());
        note.setUser(user); // Set the user relationship
        noteRepository.save(note);
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Override
    public void updateData(Integer id, NotePojo notePojo) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        note.setTitle(notePojo.getTitle());
        note.setContent(notePojo.getContent());

        User user = userRepository.findById(notePojo.getUser_id())
                .orElseThrow(() -> new RuntimeException("User not found"));
        note.setUser(user);

        noteRepository.save(note);
    }

    @Override
    public void deleteData(Integer id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        noteRepository.delete(note);
    }

    // Add this method to find notes by user ID
    public List<Note> findByUserId(Integer userId) {
        return noteRepository.findAllByUser_Id(userId);
    }
}
