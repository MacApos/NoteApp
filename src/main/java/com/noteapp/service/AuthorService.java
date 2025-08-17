package com.noteapp.service;

import com.noteapp.exception.EntityNotFound;
import com.noteapp.model.Author;
import com.noteapp.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFound(
                "Author", id));
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void save(Author author) {
        authorRepository.save(author);
    }

    public void delete(Author author) {
        authorRepository.delete(author);
    }
}