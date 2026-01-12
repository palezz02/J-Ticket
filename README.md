# J-Ticket

**Enterprise-grade ticketing platform** designed for high-performance event management and secure ticket distribution. This project demonstrates advanced backend patterns such as **Clean Architecture**, **Optimistic Locking**, and **Microservices Synchronization**, leveraging the power of **Java 21**.

---

## 🛠 Core Technical Features

* **Java 21 (LTS)**: Utilizing the latest language features (Virtual Threads, Pattern Matching).
* **Clean Architecture**: Strict separation of concerns between domain logic, infrastructure, and delivery layers.
* **Concurrency Control**: Implementation of **Optimistic Locking** (via Hibernate `@Version`) to prevent double-booking during peak ticket sales.
* **Database Versioning**: Managed evolution of the PostgreSQL 18+ schema using **Flyway** migrations.
* **Dockerized Environment**: Fully containerized infrastructure for consistent development and deployment.

---

## ⚙️ Configuration & Environment

The application relies on environment variables for security and portability. You can set these in your OS or through a local `.env` file (not tracked by Git).

| Variable | Description | Recommended Value |
| :--- | :--- | :--- |
| `DB_NAME` | PostgreSQL Database Name | `jticket` |
| `DB_USER` | Database Username | `myuser` |
| `DB_PASSWORD` | Database Password | `your_secure_password` |
| `SERVER_PORT` | Spring Boot Application Port | `8081` |

---

## Quick Start

### 1. Database Setup
Spin up the PostgreSQL 18 container using Docker Compose:
```bash
docker compose up -d
