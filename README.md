# NoteApp
Simple app for creating and managing notes as well as their authors.

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
### Author
Create an author
   ```sh
   curl -X POST http://localhost:8080/authors \
      -H "Content-Type: application/json" \
      -d '{"name": "John Doe"}'
   ```
