package com.noteapp.service;

import com.noteapp.exception.EntityNotFound;
import com.noteapp.model.Note;
import com.noteapp.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final AuthorService authorService;
    private final NoteRepository noteRepository;

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note findById(Long id) {
        return noteRepository.findById(id).orElseThrow(()->new EntityNotFound("Note", id));
    }

    public void save(Note note) {
        authorService.findById(note.getAuthor().getId());
        noteRepository.save(note);
    }

    public void deleteById(Long id) {
        Note note = findById(id);
        noteRepository.delete(note);
    }
}