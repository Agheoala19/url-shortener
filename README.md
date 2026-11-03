# URL Shortener

A RESTful URL Shortener API built with **Java and Spring Boot**. This was my first project while learning Spring Boot, where I explored building REST APIs and understanding how different layers of a Spring application work together.

The application allows users to shorten long URLs, redirect to their original destinations, and track basic statistics such as the number of clicks.

## Features

- **URL Shortening:** Generate a unique 10-character alphanumeric code for each URL.
- **URL Redirection:** Redirect users to the original URL using its generated short code.
- **Click Tracking:** Automatically increment the click counter whenever a shortened URL is accessed.
- **URL Statistics:** Retrieve information about a shortened URL, including its original URL, short code, creation date, and number of clicks.
- **Input Validation:** Validate incoming URLs using Jakarta Validation.
- **Custom Exceptions:** Handle cases where a shortened URL cannot be found.
- **Unit Testing:** Tests written using JUnit and Mockito.

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Jakarta Validation
- JUnit
- Mockito
- Maven
- Relational Database (JPA)

## Project Structure

The application follows a layered architecture, separating responsibilities into different packages:

```text
src/main/java/com/example/urlshortener
│
├── controller
│   └── UrlController.java
│
├── dto
│   ├── ShortenRequest.java
│   └── ReturnStats.java
│
├── entity
│   └── UrlMapping.java
│
├── exception
│   └── UrlNotFoundException.java
│
├── repository
│   └── UrlRepository.java
│
└── service
    └── UrlService.java
```

### Architecture Overview

- **Controller:** Handles HTTP requests and responses.
- **Service:** Contains the business logic, including short code generation, URL redirection, and click tracking.
- **Repository:** Handles database operations using Spring Data JPA.
- **Entity:** Represents the URL mapping stored in the database.
- **DTO:** Defines the data transferred between the client and the API.
- **Exception:** Contains custom exceptions for handling application errors.

## API Endpoints

### 1. Shorten a URL

Creates a shortened version of the submitted URL.

**Endpoint:**
```http
POST /api/shorten
```

**Request Body:**
```json
{
  "url": "https://www.example.com/some/very/long/url"
}
```

**Response:**
```json
{
  "id": 1,
  "url": "https://www.example.com/some/very/long/url",
  "code": "aB12cD34eF",
  "dateTime": "2026-11-03T12:00:00",
  "noClicks": 0
}
```

### 2. Redirect to Original URL

Redirects the user to the original URL associated with the provided short code.

**Endpoint:**
```http
GET /{code}
```

**Example:**
```http
GET /aB12cD34eF
```

Returns an HTTP **302 Found** response and redirects the user to the original URL. The click counter is incremented with each successful redirect.

### 3. Get URL Statistics

Retrieves statistics for a shortened URL.

**Endpoint:**
```http
GET /api/status/{code}
```

**Example:**
```http
GET /api/status/aB12cD34eF
```

**Response:**
```json
{
  "originalUrl": "https://www.example.com/some/very/long/url",
  "code": "aB12cD34eF",
  "noClicks": 5,
  "createdAt": "2026-11-03T12:00:00"
}
```

## Running the Project

### Prerequisites

- Java (compatible with your Spring Boot version)
- Maven (or the Maven wrapper included in the project)
- A configured relational database

### Steps

1. Clone the repository:

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
```

2. Navigate to the project directory:

```bash
cd YOUR_REPOSITORY
```

3. Configure your database connection in `src/main/resources/application.properties`.

4. Run the application:

```bash
./mvnw spring-boot:run
```

On Windows CMD:

```cmd
mvnw.cmd spring-boot:run
```

The application will start on the default Spring Boot port (8080), unless configured otherwise.

## Testing

The project includes unit tests using **JUnit and Mockito** to test application behavior and service logic.

Run the tests with:

```bash
mvnw.cmd test
```

## What I Learned

Through this project, I gained practical experience with:

- Building RESTful APIs using Spring Boot.
- Understanding layered application architecture.
- Dependency Injection and constructor-based injection.
- Working with Spring Data JPA and repositories.
- Mapping Java objects to database entities.
- Using DTOs to handle request and response data.
- Implementing input validation.
- Creating and handling custom exceptions.
- Writing unit tests using Mockito.
- Generating unique short codes using Java's `SecureRandom`.

## Future Improvements

Some ideas I would like to explore and potentially implement:

- Add expiration dates for shortened URLs.
- Improve error handling with a global exception handler.
- Add integration tests.
- Implement user authentication and personal URL management.
- Add a frontend interface.
- Improve short code collision handling for larger-scale usage.

---

**This project was built as a learning experience while exploring Java and Spring Boot.**
