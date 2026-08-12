# Disaster & Emergency Alert System

A full-stack disaster reporting and emergency alert application built with **Java 17, Spring Boot, Spring Data JPA, MySQL, HTML, CSS, and JavaScript**. The system provides APIs and a browser-based interface for viewing alerts, submitting disaster reports, and managing alert/report data.

## Project Overview

The application is designed around a simple emergency-information workflow:

- Users can register and log in.
- Users can view emergency alerts.
- Users can submit disaster reports.
- Admin-oriented pages can manage alerts and reports through the backend APIs.
- Data is persisted in MySQL through Spring Data JPA.

## Key Features

- User registration and login APIs
- BCrypt password hashing for newly registered and environment-seeded users
- Disaster alert listing, creation, and deletion APIs
- Emergency report submission, listing, and deletion APIs
- MySQL persistence using Spring Data JPA
- Browser-based frontend with user, alert, report, safety, login, and admin pages
- Configurable database connection and server port through environment variables

## Technology Stack

### Backend
- Java 17
- Spring Boot 3.2.5
- Spring Web
- Spring Data JPA
- Hibernate/JPA
- Maven
- BCrypt password hashing via Spring Security Crypto

### Database
- MySQL
- SQL schema provided in `database.sql`

### Frontend
- HTML5
- CSS3
- JavaScript

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
| POST | `/auth/register` | Register a USER account |
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

The project uses a MySQL database named `disasterdb` by default. The repository includes `database.sql` with the alert and report table definitions.

Create the database before starting the application:

```sql
CREATE DATABASE disasterdb;
```

Then run the SQL in `database.sql` if you want to create the tables manually. JPA is also configured with `ddl-auto=update`.

## Configuration

The backend supports environment variables while retaining the existing local-development defaults:

```text
DB_URL=jdbc:mysql://localhost:3306/disasterdb
DB_USERNAME=root
DB_PASSWORD=
SERVER_PORT=8081
```

Do not commit real database passwords, API keys, tokens, or other secrets.

## Initial Accounts

The application no longer contains hardcoded usernames or passwords in source code.

To create an initial account automatically, set the following environment variables before starting the backend:

```text
ADMIN_USERNAME=<your-admin-username>
ADMIN_PASSWORD=<your-admin-password>
USER_USERNAME=<your-user-username>
USER_PASSWORD=<your-user-password>
```

Passwords are stored using BCrypt hashing.

## How to Run

### 1. Prerequisites

- Java 17 or later
- MySQL
- Maven, or use the included Maven wrapper
- A modern web browser

### 2. Configure MySQL

Create the `disasterdb` database and verify the database connection settings in `application.properties` or through environment variables.

### 3. Start the backend

From the `backend` directory:

**Windows:**

```bash
mvnw.cmd spring-boot:run
```

**Linux/macOS:**

```bash
./mvnw spring-boot:run
```

The default backend port is `8081`.

### 4. Open the frontend

Open `frontend/index.html` in a browser or serve the `frontend` directory with a local static web server.

Make sure the frontend API configuration points to the running Spring Boot backend.

## Architecture

The current implementation follows a lightweight Spring Boot structure:

```text
Browser UI
    ↓
REST Controllers
    ↓
Spring Data JPA Repositories
    ↓
MySQL Database
```

Authentication is handled by `AuthController`, while alert and report operations are exposed through dedicated REST controllers.

## Security Notes

- Passwords for newly registered users are BCrypt-hashed.
- Initial credentials are supplied through environment variables rather than committed source code.
- Database credentials should be supplied through environment variables in non-local environments.
- This project does not currently implement JWT-based authentication or a full Spring Security authorization configuration.

## Future Improvements

Potential next steps include:

- Add a dedicated service layer between controllers and repositories.
- Add request validation and centralized exception handling.
- Add automated unit and integration tests.
- Introduce role-based authorization for administrative operations.
- Add API documentation with OpenAPI/Swagger.
- Improve authentication with a complete Spring Security security configuration.

## Author

**Lomesh Pawar**

GitHub: [@lomeshpawar](https://github.com/lomeshpawar)
