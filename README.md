
 A stateless, containerized authentication microservice built in Spring Boot and PostgreSQL that handles user registration, credential hashing with BCrypt, role-based authorization, and JWT token issuance and validation.

---

  Features :

- Stateless Authentication: Issues signed JWT tokens using HMAC-SHA256 upon user authentication.
- Custom Security Filter: Intercepts requests via a custom `OncePerRequestFilter` to validate Bearer tokens.
- Role-Based Access Control (RBAC): Enforces endpoint-level authorization rules (e.g., `/user/**` vs. `/admin/**`).
- Secure Password Hashing: Utilizes `BCryptPasswordEncoder` for credential hashing before persistence.
- Containerized Database: Runs a dedicated PostgreSQL instance via Docker.
- ORM Persistence : Manages entities and data access through **Spring Data JPA** and **Hibernate**.

---

 Tech Stack

- Language: Java
- Framework: Spring Boot 4, Spring Security
- Security: JJWT (JSON Web Token)
- Database: PostgreSQL (Dockerized)
- Build Tool: Apache Maven

---

Getting Started

 1. Start the PostgreSQL Container

```bash
docker run --name postgres-auth -e POSTGRES_DB=authdb -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
