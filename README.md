# NoteApp

This is a simple app for creating and managing notes as well as their authors.

## Technologies

* Java 21
* Spring Boot 3.5.3
* Spring Data JPA 3.5.2
* Jakarta Validation 3.0.2
* H2 Database Engine 2.3.232
* Lombok 1.18.38
* Apache Maven 3.9.11

## Installation

1. Clone the repository
   ```sh
   git clone https://github.com/MacApos/NodeApp.git
   ```
2. Go to the project's main directory
   ```sh
   cd NodeApp
   ```
3. Run Spring Boot application
   ```sh
   ./mvnw spring-boot:run
   ```

## API Endpoints

### Author endpoints

Create an author

   ```sh
   curl -X POST http://localhost:8080/authors \
      -H "Content-Type: application/json" \
      -d '{"name": "John Doe"}'
   ```

Get all authors

   ```sh
   curl -X GET http://localhost:8080/authors
   ```

Get author by id

   ```sh
   curl -X GET http://localhost:8080/authors/1
   ```

### Notes endpoints

Create a note

   ```sh
   curl -X POST http://localhost:8080/notes \
      -H "Content-Type: application/json" \
      -d \
      '{
        "title": "Groceries",
        "content": "Do the groceries",
        "author": {
          "id": 1
        }
      }'
   ```

Get all notes

   ```sh
   curl -X GET http://localhost:8080/notes
   ```

Get note by id

   ```sh
   curl -X GET http://localhost:8080/notes/1
   ```
Delete note by id

   ```sh
   curl -X DELETE http://localhost:8080/notes/1
   ```