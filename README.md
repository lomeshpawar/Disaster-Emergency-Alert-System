# Disaster & Emergency Alert System

A full-stack **Disaster & Emergency Alert System** built with **Java 17, Spring Boot, Spring Data JPA, MySQL, HTML5, CSS3, and JavaScript**.

The system allows users to view emergency alerts, submit disaster reports, access safety information, and interact with backend REST APIs. The frontend is deployed as a static website on **GitHub Pages**, while the Spring Boot backend remains deployed on **Railway**.

## 🌐 Live Demo

| Component | URL |
|---|---|
| **Frontend – GitHub Pages** | https://lomeshpawar.github.io/Disaster-Emergency-Alert-System/ |
| **Backend API – Railway** | https://disaster-backend-production-1250.up.railway.app |

### Frontend Pages

- **Home:** `index.html`
- **Login / Registration:** `login.html`
- **Emergency Alerts:** `alerts.html`
- **Report Disaster:** `report.html`
- **Safety Information:** `safety.html`
- **Admin Dashboard:** `admin.html`

## ✨ Features

- Responsive disaster and emergency alert interface
- User registration and login
- Emergency alert viewing and management
- Disaster report submission and management
- Safety information page
- Admin dashboard
- REST API integration between frontend and Spring Boot backend
- MySQL database persistence
- BCrypt password hashing
- Static frontend deployment through GitHub Pages
- Spring Boot backend deployment through Railway
- GitHub Actions workflow for automatic frontend deployment

## 🛠️ Technology Stack

### Frontend

- HTML5
- CSS3
- JavaScript
- Static GitHub Pages hosting

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
- SQL schema: `database.sql`

### Deployment & Tools

- Git
- GitHub
- GitHub Actions
- GitHub Pages
- Railway

## 📁 Project Structure

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
│
├── frontend/
│   ├── index.html
│   ├── login.html
│   ├── alerts.html
│   ├── report.html
│   ├── safety.html
│   ├── admin.html
│   ├── style.css
│   └── script.js
│
├── database.sql
├── .gitignore
├── .github/
│   └── workflows/
│       └── pages.yml
└── README.md
```

## 🔌 REST API

The frontend communicates with the deployed Spring Boot backend.

### Authentication

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/auth/register` | Register a user |
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
| GET | `/reports` | Retrieve disaster reports |
| POST | `/reports` | Submit a disaster report |
| DELETE | `/reports/{id}` | Delete a report |

## 🗄️ Database Setup

The project uses MySQL.

Create the database locally:

```sql
CREATE DATABASE disasterdb;
```

The repository includes `database.sql` for the database setup. Hibernate/JPA is configured to create or update required tables during application startup according to the backend configuration.

## ⚙️ Configuration

For local development, the backend supports environment-based configuration:

```text
DB_URL=jdbc:mysql://localhost:3306/disasterdb
DB_USERNAME=root
DB_PASSWORD=
SERVER_PORT=8081
```

For deployment, configure the required environment variables in the hosting platform.

**Never commit real passwords, database credentials, API keys, access tokens, or other secrets.**

## 🚀 Run Locally

### Prerequisites

- Java 17 or later
- MySQL
- Maven or the included Maven wrapper
- Modern web browser

### 1. Start MySQL

Create the database:

```sql
CREATE DATABASE disasterdb;
```

Configure the database credentials for the backend environment.

### 2. Start the Backend

From the `backend` directory:

**Windows**

```bash
mvnw.cmd spring-boot:run
```

**Linux/macOS**

```bash
./mvnw spring-boot:run
```

The default backend port is:

```text
http://localhost:8081
```

### 3. Run the Frontend

The frontend is plain HTML/CSS/JavaScript and does not require a frontend framework or build system.

You can serve the `frontend` directory using any local static web server.

For example, with VS Code, use a static-server extension such as Live Server.

For local development, make sure the frontend API configuration points to your local Spring Boot backend.

## ☁️ Deployment

### Frontend – GitHub Pages

The `frontend/` directory is deployed directly as a static GitHub Pages site.

The workflow is:

```text
GitHub Repository
       │
       ▼
GitHub Actions
       │
       ▼
frontend/
       │
       ▼
GitHub Pages
```

Any push to `main` that changes the frontend or Pages workflow can trigger the deployment.

### Backend – Railway

The Spring Boot backend remains deployed separately on Railway:

```text
Frontend – GitHub Pages
          │
          │ REST API
          ▼
Backend – Railway
          │
          ▼
MySQL
```

GitHub Pages is used only for the static frontend; the Java/Spring Boot backend is **not** deployed to GitHub Pages.

## 🔄 Updating the Frontend

After making frontend changes:

```bash
git add frontend .github/workflows/pages.yml
git commit -m "update: frontend"
git push origin main
```

GitHub Actions will deploy the updated `frontend/` directory to GitHub Pages.

## 🔐 Security Notes

- Passwords are stored using BCrypt hashing.
- Deployment credentials should be stored as environment variables/secrets.
- Do not commit `.env` files or secret configuration.
- The backend and database credentials must remain outside the public repository.
- JWT authentication is not currently implemented.
- Administrative authorization is intentionally lightweight within the current project scope.

## 🔮 Future Improvements

- Add a dedicated service layer.
- Add automated unit and integration tests.
- Implement role-based authorization for administrative operations.
- Add OpenAPI/Swagger API documentation.
- Improve authentication and authorization with Spring Security.
- Add production-grade monitoring and structured logging.
- Add richer disaster-alert functionality and notification mechanisms.

## 👨‍💻 Author

**Lomesh Pawar**

GitHub: https://github.com/lomeshpawar

---

⭐ If you find this project useful, consider giving the repository a star.
