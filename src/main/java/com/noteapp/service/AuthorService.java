package com.noteapp.service;

import com.noteapp.exception.EntityNotFound;
import com.noteapp.model.Author;
import com.noteapp.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->
                new EntityNotFound("Author", id));
    }

    @Transactional
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Transactional
    public void saveAll(List<Author> authors) {
        authorRepository.saveAll(authors);
    }

    public void deleteAll() {
        authorRepository.deleteAll(findAll());
    }

}