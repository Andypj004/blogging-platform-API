# blogging-platform-API

A simple RESTful API built with Java and Spring Boot to manage blog posts. This project supports basic CRUD operations including creating, reading, updating, deleting, and searching blog posts.

## 📌 Features

- Create a new blog post
- Retrieve a single blog post by ID
- Retrieve all blog posts
- Update an existing blog post
- Delete a blog post
- Search blog posts by term (in title, content, or category)
- Input validation and error handling

## 🛠️ Technologies Used

- Java 21
- Spring Boot 3.4.3
- Spring Data JPA
- PostgreSQL
- Lombok
- Jakarta Validation
- Maven

## 📦 Requirements

- Java 21+
- Maven 3.9+
- PostgreSQL (running locally or remotely)

## 🚀 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/Andypj004/blogging-platform-API.git
   cd blogging-platform-API
   ```
2. Configure PostgreSQL:
   - Make sure PostgreSQL is running and create a database for the app (database/BlogPost.sql):

3. Set up application properties:
   - Edit src/main/resources/application.properties:
   ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/blogging_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```
4. Build and run the project
   ```bash
   ./mvnw spring-boot:run
   ```

## 📚 API Endpoints
- Create Post
   ```bash
   POST /api/post
   ```
- Request Body
  ```bash
   {
  "title": "My First Blog Post",
  "content": "This is the content of my first blog post.",
  "category": "Technology",
  "tags": ["Tech", "Programming"]
   }
   ```
- Update Post
   ```bash
   PUT /api/post/{id}
   ```
- Delete Post
   ```bash
   DELETE /api/post/{id}
   ```
- Get Single Post
   ```bash
   GET /api/post/{id}
   ```
- Get All Posts
   ```bash
   GET /api/post
   ```
- Filter Posts by Term
   ```bash
   GET /api/post?term=tech
   ```