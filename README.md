# Disaster & Emergency Alert System

A full-stack disaster reporting and emergency alert application built with **Java 17, Spring Boot, Spring Data JPA, MySQL, HTML, CSS, and JavaScript**. The system provides REST APIs and a responsive browser-based interface for viewing emergency alerts, submitting disaster reports, and managing alert/report data.

## Live Demo

- **Frontend:** https://disaster-frontend-production.up.railway.app
- **Backend API:** https://disaster-backend-production-1250.up.railway.app

The application is deployed on Railway with separate frontend and backend services.

## Features

- User registration and login APIs
- BCrypt password hashing
- Emergency alert listing, creation, and deletion
- Disaster report submission, listing, and deletion
- Admin-oriented alert and report management pages
- MySQL persistence with Spring Data JPA
- Responsive browser-based frontend
- Environment-based database and server configuration
- Automated backend build verification with GitHub Actions

## Technology Stack

### Backend

- Java 17
- Spring Boot 3.2.5
- Spring Web
- Spring Data JPA
- Hibernate / JPA
- Maven
- BCrypt password hashing via Spring Security Crypto

### Database

- MySQL
- SQL schema provided in `database.sql`

### Frontend

- HTML5
- CSS3
- JavaScript

### DevOps

- Git & GitHub
- GitHub Actions
- Railway

## Project Structure

```text
Disaster-Emergency-Alert-System/
├── backend/
│   ├── pom.xml
│   ├── mvnw
│   ├── mvnw.cmd
│   └── src/main/
│       ├── java/com/example/demo/
│       │   ├── MainApplication.java
│       │   ├── AuthController.java
│       │   ├── DataInitializer.java
│       │   ├── Alert.java
│       │   ├── AlertController.java
│       │   ├── AlertRepository.java
│       │   ├── Report.java
│       │   ├── ReportController.java
│       │   ├── ReportRepository.java
│       │   ├── User.java
│       │   └── UserRepository.java
│       └── resources/
│           └── application.properties
├── frontend/
│   ├── index.html
│   ├── login.html
│   ├── alerts.html
│   ├── report.html
│   ├── safety.html
│   ├── admin.html
│   ├── style.css
│   └── script.js
├── database.sql
├── .gitignore
└── README.md
```

## REST API

### Authentication

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/auth/register` | Register a user account |
| POST | `/auth/login` | Authenticate a user |

### Alerts

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/alerts` | Retrieve alerts |
| POST | `/alerts` | Create an alert |
| DELETE | `/alerts/{id}` | Delete an alert |

### Reports

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/reports` | Retrieve submitted reports |
| POST | `/reports` | Submit a disaster report |
| DELETE | `/reports/{id}` | Delete a report |

## Database

The project uses MySQL. The repository includes `database.sql` with the core alert and report table definitions.

For local development, create the database before starting the application:

```sql
CREATE DATABASE disasterdb;
```

JPA is configured with `ddl-auto=update`, so required tables can also be created or updated automatically during application startup.

## Configuration

The backend supports environment variables while retaining local-development defaults:

```text
DB_URL=jdbc:mysql://localhost:3306/disasterdb
DB_USERNAME=root
DB_PASSWORD=
SERVER_PORT=8081
```

Never commit real database passwords, API keys, access tokens, or other secrets.

## Initial Accounts

Initial credentials are supplied through environment variables rather than committed to source code:

```text
ADMIN_USERNAME=<your-admin-username>
ADMIN_PASSWORD=<your-admin-password>
USER_USERNAME=<your-user-username>
USER_PASSWORD=<your-user-password>
```

Passwords are stored using BCrypt hashing.

## Run Locally

### Prerequisites

- Java 17 or later
- MySQL
- Maven, or the included Maven wrapper
- A modern web browser

### Start the backend

From the `backend` directory:

**Windows**

```bash
mvnw.cmd spring-boot:run
```

**Linux/macOS**

```bash
./mvnw spring-boot:run
```

The default backend port is `8081`.

### Start the frontend

Serve the `frontend` directory with a local static web server and ensure the frontend API configuration points to the running Spring Boot backend.

## Architecture

```text
Browser UI
    │
    ▼
REST Controllers
    │
    ▼
Spring Data JPA
    │
    ▼
MySQL Database
```

The deployed application separates the frontend and backend into independent Railway services.

## CI/CD

GitHub Actions runs automated backend verification on repository changes. Production deployment is handled through Railway.

## Security Notes

- User passwords are BCrypt-hashed.
- Initial credentials are provided through environment variables.
- Database credentials are supplied through environment variables in deployed environments.
- JWT authentication is not currently implemented.
- Administrative authorization is intentionally lightweight in the current project scope.

## Future Improvements

- Add a dedicated service layer between controllers and repositories.
- Add automated unit and integration tests.
- Introduce role-based authorization for administrative operations.
- Add API documentation with OpenAPI/Swagger.
- Improve authentication with a complete Spring Security configuration.
- Add production-grade monitoring and structured logging.

## Author

**Lomesh Pawar**

GitHub: https://github.com/lomeshpawar
