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
        Note note = new Note();

        note.setId(note.getId());
        note.setTitle(notePojo.getTitle());
        note.setContent(notePojo.getContent());
        User user = userRepository.findById(notePojo.getUser_id()).get();
        note.setUser(user);

        noteRepository.save(note);
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }
}
