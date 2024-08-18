package com.example.noteitweb;

import com.example.noteitweb.entity.Note;
import com.example.noteitweb.repository.NoteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void addNoteTest() {
        Note note = new Note();
        note.setTitle("Sample Note");
        note.setContent("This is a sample note content");

        note = noteRepository.save(note);

        Assertions.assertThat(note.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void updateNoteTest() {
        Note note = noteRepository.findById(1).get();
        Integer originalId = note.getId();

        note.setTitle("Updated Note Title");
        Note updatedNote = noteRepository.save(note);

        Assertions.assertThat(updatedNote.getTitle()).isEqualTo("Updated Note Title");
        Assertions.assertThat(updatedNote.getId()).isEqualTo(originalId);
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void deleteNoteTest() {
        Note note = noteRepository.findById(1).get();
        Integer noteId = note.getId();

        noteRepository.delete(note);

        Note deletedNote = noteRepository.findById(noteId).orElse(null);
        Assertions.assertThat(deletedNote).isNull();
    }

    @Test
    @Order(4)
    public void checkNoteIdBeforeAndAfterUpdate() {
        Note note = new Note();
        note.setTitle("Original Title");
        note.setContent("Original Content");

        note = noteRepository.save(note);
        Integer originalId = note.getId();

        note.setTitle("Updated Title");
        note.setContent("Updated Content");
        Note updatedNote = noteRepository.save(note);

        Assertions.assertThat(updatedNote.getId()).isEqualTo(originalId);
    }

    @Test
    @Order(5)
    public void verifySameIdForMultipleUpdates() {
        Note note = new Note();
        note.setTitle("First Title");
        note.setContent("First Content");

        note = noteRepository.save(note);
        Integer originalId = note.getId();

        note.setTitle("Second Title");
        Note secondUpdate = noteRepository.save(note);
        Assertions.assertThat(secondUpdate.getId()).isEqualTo(originalId);

        note.setTitle("Third Title");
        Note thirdUpdate = noteRepository.save(note);
        Assertions.assertThat(thirdUpdate.getId()).isEqualTo(originalId);
    }
}
