package com.noteapp.service;

import com.noteapp.model.Note;
import com.noteapp.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public Note findById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public void save(Note note) {
        noteRepository.save(note);
    }

    public void delete(Note note) {
        noteRepository.delete(note);
    }
}