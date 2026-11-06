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
│   ├── backend-ci.yml         # Backend and frontend validation
│   └── security.yml           # Scheduled and pull-request secret scanning
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

The backend requires `DB_USERNAME` and `DB_PASSWORD`; it does not fall back to the MySQL `root` account, an empty password, or legacy credential aliases. You can optionally set `DB_URL`; otherwise the connection URL is assembled from `MYSQLHOST`, `MYSQLPORT`, and `MYSQLDATABASE` (local defaults are `localhost`, `3306`, and `disasterdb`). If either required credential is missing, Spring's required-placeholder resolution fails instead of silently selecting unsafe credentials.

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

## Main API areas

| Area | Example endpoints | Purpose |
|---|---|---|
| Authentication | `POST /auth/register`, `POST /auth/login` | Register users and verify credentials; login does not currently issue a session or token |
| Alerts | `GET /alerts`, `POST /alerts`, `DELETE /alerts/{id}` | Retrieve and manage alerts |
| Reports | `GET /reports`, `POST /reports`, `DELETE /reports/{id}` | Retrieve and manage reports |

Verify exact request fields and authorization behavior against the current backend source before integrating another client.

## Automated tests and CI validation

The `.github/workflows/backend-ci.yml` workflow runs the following checks for pull requests targeting `main` and configured pushes:

**Frontend JavaScript syntax (Node.js 22):**

Run from the repository root:

```bash
node --check frontend/script.js
```

**Backend build and tests (Java 17 / Maven):**

Run from the repository root:

```bash
cd backend
sh ./mvnw -B verify
```

On Windows PowerShell, run the wrapper from `backend/` with:

```powershell
.\mvnw.cmd -B verify
```

**Container build and non-root configuration check:**

The workflow builds the backend Docker image and verifies that its configured runtime user is `10001:10001`:

```bash
docker build --file backend/Dockerfile --tag disaster-emergency-alert-backend:ci backend
image_user="$(docker image inspect disaster-emergency-alert-backend:ci --format '{{.Config.User}}')"
test "$image_user" = "10001:10001"
```

These checks validate frontend syntax, the Maven verification lifecycle, and the container image's configured user. The workflow does **not** start a MySQL service or provide full live API/database integration coverage. Passing CI also does not establish that authentication, authorization, or report privacy controls are complete. Check the [GitHub Actions runs](https://github.com/lomeshpawar/Disaster-Emergency-Alert-System/actions) for the result on the commit being reviewed.

## Deployment

### Frontend

The workflow at `.github/workflows/pages.yml` publishes the `frontend/` directory. After pushing changes to `main`, verify the latest **Deploy Frontend to GitHub Pages** workflow under the repository's **Actions** tab.

### Backend

The Spring Boot API is hosted separately from GitHub Pages. Configure its environment variables and MySQL connection on the backend host, and verify API availability, CORS, and persistence.

## Security and limitations

- Never commit database credentials, tokens, private keys, or real `.env` files.
- Registration hashes passwords with BCrypt, and login verifies the submitted password against that hash.
- Login currently returns user/role information but does not establish a server-verifiable session or issue a token. It therefore does not authenticate subsequent API requests.
- Alert creation/deletion and report listing/deletion do not yet have adequate server-side role/ownership authorization. In particular, the current unauthenticated `GET /reports` endpoint returns all report records, including submitter names and messages. Do not use real personal or incident data or expose this endpoint to untrusted users until an explicit access policy, response-field filtering, and server-side authorization are implemented and tested.
- Protect administrative actions with server-side authorization; hiding an admin link in the frontend is not security.
- Verify backend validation and error handling before production use.
- This is an academic project, not an official emergency-warning service. Do not rely on it as the sole source for real-time emergency information.

## Author

**Lomesh Pawar** · [GitHub](https://github.com/lomeshpawar)
