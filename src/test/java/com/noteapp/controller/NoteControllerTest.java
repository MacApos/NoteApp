package com.noteapp.controller;

import com.noteapp.model.Author;
import com.noteapp.model.Note;
import com.noteapp.service.AuthorService;
import com.noteapp.service.NoteService;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class NoteControllerTest {

    private String baseUrl;

    private Author author;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    protected NoteService noteService;

    @Autowired
    protected AuthorService authorService;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/notes";
        author = new Author("John");
        authorService.save(author);
    }

    @AfterEach
    void tearDown() {
        noteService.deleteAll();
        authorService.deleteAll();
    }

    @Test
    void create() {
        Note note = new Note("New note", "New note content", author);
        noteService.save(note);

        ResponseEntity<Note> response = restTemplate.postForEntity(baseUrl, note, Note.class);
        Note responseBody = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getId()).isNotNull();
        assertThat(responseBody.getAuthor()).isEqualTo(author);
        assertThat(responseBody.getTitle()).isEqualTo("New note");
        assertThat(responseBody.getContent()).isEqualTo("New note content");
    }

    @Test
    void findAll() {
        Note firstNote = new Note("First note", "First note content", author);
        Note secondNote = new Note("Second note", "Second note content", author);
        noteService.saveAll(List.of(firstNote, secondNote));

        ResponseEntity<List<Note>> response = restTemplate.exchange(baseUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {});
        List<Note> responseBody = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseBody).isNotNull();
        assertThat(responseBody).hasSize(2);
    }

    @Test
    void findById() {
        Note note = new Note("Found note", "Found note content", author);
        noteService.save(note);

        ResponseEntity<Note> response = restTemplate.getForEntity(baseUrl + "/" + note.getId(), Note.class);
        Note responseBody = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getId()).isNotNull();
        assertThat(responseBody.getAuthor()).isEqualTo(author);
        assertThat(responseBody.getTitle()).isEqualTo("Found note");
        assertThat(responseBody.getContent()).isEqualTo("Found note content");
    }

    @Test
    void delete() {
        Note note = new Note("Deleted note", "Deleted note content", author);
        noteService.save(note);

        restTemplate.delete(baseUrl + "/" + note.getId());

        ResponseEntity<List<Note>> response = restTemplate.exchange(baseUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {});
        List<Note> responseBody = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseBody).isNotNull();
        assertThat(responseBody).isEmpty();
    }
}