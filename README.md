# ZipLink

ZipLink is a backend URL shortening service built with Spring Boot and PostgreSQL.

**Live demo:** [https://ziplnk.mamialex.ru](https://ziplnk.mamialex.ru)

The application allows authenticated users to generate short URLs, manage their links, and track click events.
Public users can access short URLs and are redirected to the original destination.

## Key Features

- User registration and authentication (JWT-based)
- URL shortening for authenticated users
- Redirection using short codes
- Click tracking (timestamp)
- User-specific URL management
- Persistent storage using PostgreSQL

## Tech Stack

- Java 17
- Spring Boot
- Spring Web (REST API)
- Spring Security with JWT
- Spring Data JPA
- PostgreSQL
- Maven

## Architecture Overview

The application follows a layered architecture:

- Controller layer  
  Exposes REST endpoints and handles HTTP requests.

- Service layer  
  Contains business logic such as short code generation, validation, and click tracking.

- Repository layer  
  Uses Spring Data JPA to interact with PostgreSQL.

- Security layer  
  Implements JWT authentication, authorization filters, and user details service.

- Domain model  
  Represents core entities such as users, URL mappings, and click events.

## Domain Model

### User
- username
- email
- password
- roles

### UrlMapping
- originalUrl
- shortCode
- owner (User)
- createdAt

### ClickEvent
- timestamp
- urlMapping

## API Endpoints

### Authentication

POST /api/auth/register

POST /api/auth/login

### URL Management (Authenticated)

POST /api/urls/shorten

GET /api/urls

### Public Redirect

GET /{shortCode}

## Security

- JWT-based authentication
- Stateless session management
- Protected endpoints for URL management
- Public access to redirect endpoint
- Custom JWT authentication filter

## Database

- PostgreSQL is used as the primary data store
- JPA entities are mapped to relational tables
- Configuration is environment-based (dev / prod)

## Running the Application

Requirements:
- Java 17+
- Maven
- PostgreSQL

Run locally:

mvn clean spring-boot:run

The application will be available at http://localhost:8080

## Design Decisions

- JWT was chosen for stateless authentication and scalability
- URL mappings are linked to users to support multi-user environments
- Click events are stored as a separate entity to enable analytics
- Layered architecture simplifies future extension and testing

## Possible Improvements

- Add URL expiration (TTL)
- Implement custom short code generation
- Add pagination for user URLs
- Add unit and integration tests
- Add Swagger / OpenAPI documentation
- Introduce rate limiting for public endpoints
- Track client IP address and user-agent for click analytics

## Author

This project was created as a learning and portfolio project to demonstrate backend development skills using Spring Boot, security, and relational databases.
