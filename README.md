# Disaster & Emergency Alert System

A full-stack web application for viewing emergency alerts, submitting disaster reports, and accessing safety information. The project uses a static HTML/CSS/JavaScript frontend and a Java Spring Boot REST API with MySQL persistence.

## Live demo

| Component | URL |
|---|---|
| **Frontend** | [Open frontend](https://lomeshpawar.github.io/Disaster-Emergency-Alert-System/) |
| **Backend API** | [Open backend API](https://disaster-backend-production-1250.up.railway.app) |

**Frontend deployment workflow:** [View GitHub Actions workflow](https://github.com/lomeshpawar/Disaster-Emergency-Alert-System/actions/workflows/pages.yml)

The frontend is published to GitHub Pages using GitHub Actions. The Spring Boot backend is a separate Railway service; GitHub Pages cannot run Java or host MySQL. Backend-dependent features require the Railway service and its database configuration to be available.

## Features

- Responsive pages for home, login, alerts, disaster reporting, and safety tips
- User registration and login
- View emergency alerts
- Submit disaster reports
- Administrative alert/report management interface
- REST API integration with Spring Boot
- MySQL persistence
- Automated frontend deployment workflow

## Technology stack

| Layer | Technology |
|---|---|
| Frontend | HTML5, CSS3, JavaScript |
| Backend | Java 17, Spring Boot |
| Persistence | Spring Data JPA / Hibernate |
| Database | MySQL |
| Build | Maven |
| Frontend hosting | GitHub Pages |
| Backend hosting | Railway |
| Automation | GitHub Actions |

## Repository structure

```text
Disaster-Emergency-Alert-System/
├── frontend/                 # Static frontend pages, styles and JS
├── backend/                  # Spring Boot REST API
├── database.sql              # Database initialization script
├── .github/workflows/
│   ├── pages.yml              # Publish frontend to GitHub Pages
│   └── backend-ci.yml         # Backend CI checks
└── README.md
```

## Run locally

### Prerequisites

- Java 17+
- MySQL
- Maven or the included Maven wrapper
- Modern browser and a static web server (for example, VS Code Live Server)

### 1. Create the database

Follow the SQL script in `database.sql` and confirm the database name matches the backend configuration. Example:

```sql
CREATE DATABASE disasterdb;
```

### 2. Configure the backend

The backend requires `DB_USERNAME` and `DB_PASSWORD`; it does not fall back to the MySQL `root` account, an empty password, or legacy credential aliases. You can optionally set `DB_URL`; otherwise the connection URL is assembled from `MYSQLHOST`, `MYSQLPORT`, and `MYSQLDATABASE` (local defaults are `localhost`, `3306`, and `disasterdb`). If either required credential is missing, required-placeholder resolution fails instead of silently selecting unsafe credentials.

Create a dedicated MySQL application user with only the permissions this application needs. For local development, set the variables in your terminal before starting Spring Boot.

**PowerShell example** (replace the sample values locally; never commit real credentials):

```powershell
$env:DB_URL = "jdbc:mysql://localhost:3306/disasterdb"
$env:DB_USERNAME = "disaster_app"
$env:DB_PASSWORD = "replace-with-your-local-password"
```

**Linux/macOS example:**

```bash
export DB_URL="jdbc:mysql://localhost:3306/disasterdb"
export DB_USERNAME="disaster_app"
export DB_PASSWORD="replace-with-your-local-password"
```

Keep real credentials in environment variables or local ignored configuration; never commit passwords or keys.

### 3. Start the backend

From the `backend/` directory:

**Windows**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux/macOS**

```bash
./mvnw spring-boot:run
```

Use the port configured by the backend (the documented local default is `8081`).

### 4. Start the frontend

Serve the `frontend/` directory using Live Server or another static HTTP server. For local development, verify that the API base URL in the frontend points to the local backend; the committed frontend currently references the deployed Railway backend.

## Run backend tests

The backend CI uses the included Maven wrapper and Java 17. From the repository root, run:

**Linux/macOS:**

```bash
cd backend
./mvnw -B verify
```

**Windows PowerShell:**

```powershell
cd backend
.\mvnw.cmd -B verify
```

The Maven `verify` lifecycle compiles the backend and runs its automated tests. A successful test run does not replace the separate server-side authentication and authorization work documented below.

## Main API areas

| Area | Example endpoints | Purpose |
|---|---|---|
| Authentication | `POST /auth/register`, `POST /auth/login` | Register and authenticate users |
| Alerts | `GET /alerts`, `POST /alerts`, `DELETE /alerts/{id}` | Retrieve and manage alerts |
| Reports | `GET /reports`, `POST /reports`, `DELETE /reports/{id}` | Retrieve and manage reports |

Verify exact request fields and authorization behavior against the current backend source before integrating another client.

## Deployment

### Frontend

The workflow at `.github/workflows/pages.yml` publishes the `frontend/` directory. After pushing changes to `main`, verify the latest **Deploy Frontend to GitHub Pages** workflow under the repository's **Actions** tab.

### Backend

The Spring Boot API is hosted separately from GitHub Pages. Configure its environment variables and MySQL connection on the backend host, and verify API availability, CORS, and persistence.

## Security and limitations

- Never commit database credentials, tokens, private keys, or real `.env` files.
- Passwords are checked with BCrypt, but the current backend does not yet establish a server-verified authenticated identity for subsequent API requests.
- Alert creation/deletion and report deletion are not yet protected by server-side role authorization. The admin interface is a prototype, not a security boundary.
- Do not expose the current backend publicly or use real user/disaster data until [Issue #9 — server-side authentication and authorization](https://github.com/lomeshpawar/Disaster-Emergency-Alert-System/issues/9) is resolved and tested.
- CORS configuration is not authentication or authorization.
- Verify backend validation and error handling before production use.
- This is an academic project, not an official emergency-warning service. Do not rely on it as the sole source for real-time emergency information.

## Author

**Lomesh Pawar** · [GitHub](https://github.com/lomeshpawar)
