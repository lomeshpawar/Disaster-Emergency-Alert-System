# 🚨 Disaster & Emergency Alert System

> Full-stack web application for disaster reporting and emergency alert management using Java 17 and Spring Boot.

## 📌 Overview

The Disaster & Emergency Alert System allows users to view emergency alerts, submit disaster reports, access safety information, and interact with REST APIs.

The application uses a static **HTML/CSS/JavaScript frontend**, a **Spring Boot backend**, and **MySQL** for persistence.

## ✨ Features

- User registration and login
- Emergency alert viewing and management
- Disaster report submission
- Admin dashboard
- REST API integration
- MySQL database persistence
- BCrypt password hashing
- Responsive frontend
- GitHub Pages deployment
- Railway backend deployment
- GitHub Actions frontend deployment

## 🏗️ Architecture

```text
┌─────────────────────────┐
│ HTML / CSS / JavaScript │
│ GitHub Pages            │
└────────────┬────────────┘
             │ REST API
             ▼
┌─────────────────────────┐
│ Spring Boot REST API    │
│ Java 17 / JPA / Maven   │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│ MySQL Database          │
└─────────────────────────┘
```

## 🛠️ Technology Stack

**Frontend**
- HTML5
- CSS3
- JavaScript

**Backend**
- Java 17
- Spring Boot 3.2.5
- Spring Web
- Spring Data JPA
- Hibernate/JPA
- Maven
- Spring Security Crypto / BCrypt

**Database**
- MySQL

**Deployment & Tools**
- Git
- GitHub
- GitHub Actions
- GitHub Pages
- Railway
- Postman

## 📁 Project Structure

```text
Disaster-Emergency-Alert-System/
├── backend/
│   ├── pom.xml
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
├── database.sql
├── .github/workflows/pages.yml
└── README.md
```

## 🔌 REST API

### Authentication

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/auth/register` | Register user |
| POST | `/auth/login` | Authenticate user |

### Alerts

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/alerts` | Retrieve alerts |
| POST | `/alerts` | Create alert |
| DELETE | `/alerts/{id}` | Delete alert |

### Reports

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/reports` | Retrieve reports |
| POST | `/reports` | Submit disaster report |
| DELETE | `/reports/{id}` | Delete report |

## 🌐 Live Demo

**Frontend — GitHub Pages**  
https://lomeshpawar.github.io/Disaster-Emergency-Alert-System/

**Backend API — Railway**  
https://disaster-backend-production-1250.up.railway.app

## ⚙️ Run Locally

### Prerequisites

- Java 17+
- MySQL
- Maven or Maven Wrapper
- Modern web browser

### Database

```sql
CREATE DATABASE disasterdb;
```

Configure the backend using environment variables:

```text
DB_URL=jdbc:mysql://localhost:3306/disasterdb
DB_USERNAME=root
DB_PASSWORD=
SERVER_PORT=8081
```

### Start Backend

Windows:

```bash
cd backend
mvnw.cmd spring-boot:run
```

Linux/macOS:

```bash
cd backend
./mvnw spring-boot:run
```

Backend:

```text
http://localhost:8081
```

Then serve the `frontend/` directory using a local static server.

## 🔐 Security Notes

- Passwords use BCrypt hashing.
- Never commit database credentials or API secrets.
- Keep `.env` and other secret configuration outside version control.
- JWT authentication is not currently implemented.
- Administrative authorization is intentionally lightweight for the current project scope.

## 🔮 Future Improvements

- Add a dedicated service layer
- Add automated unit and integration tests
- Implement role-based authorization
- Add OpenAPI/Swagger documentation
- Improve authentication and authorization
- Add structured logging and monitoring
- Add richer alert notifications

## 👨‍💻 Author

**Lomesh Pawar**  
MCA Student | Java Backend Developer

[GitHub](https://github.com/lomeshpawar)
