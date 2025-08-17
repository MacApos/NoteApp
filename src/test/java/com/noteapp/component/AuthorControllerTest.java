package com.noteapp.component;

import com.noteapp.model.Author;
import com.noteapp.service.AuthorService;
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

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class AuthorControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AuthorService authorService;

    String baseUrl ;


    @BeforeEach
    void setUp() {
        baseUrl =  "http://localhost:" + port + "/authors";
        authorService.deleteAll();
    }

    @Test
    void testCreateAuthor() {
        Author author = new Author("John");
        authorService.save(author);

        ResponseEntity<Author> response = restTemplate.postForEntity(baseUrl, author, Author.class);
        Author responseBody = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getId()).isNotNull();
        assertThat(responseBody.getName()).isEqualTo("John");
    }

    @Test
    void testFindAllAuthors() {
        Author author1 = new Author("Bob");
        Author author2 = new Author("Alice");
        authorService.saveAll(List.of(author1, author2));

        ResponseEntity<List<Author>> response = restTemplate.exchange(baseUrl, HttpMethod.GET,
                null, new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(2);
    }

    @Test
    void testFindById() {
        Author author = new Author("Charlie");
        authorService.save(author);

        ResponseEntity<Author> response = restTemplate.getForEntity(baseUrl + "/" + author.getId(), Author.class);
        Author responseBody = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getId()).isNotNull();
        assertThat(responseBody.getName()).isEqualTo("Charlie");
    }
}
