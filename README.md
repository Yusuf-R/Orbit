# 🪐 Orbit

> A task management API — because your to-dos should orbit around you, not the other way around.

Orbit is a Spring Boot REST API for managing tasks (called "Orbits"). It's a learning project built to understand core Spring Boot patterns — layered architecture, DTOs, mapping, validation, and centralized exception handling — the way you'd structure a production-grade service, even at small scale.

## Why "Orbit"?

Every task in this app is called an **Orbit** — something that revolves around a fixed point, the same way your to-dos revolve around you and your day. Less obvious than "TaskList," a little more fun to build.

## Tech Stack

- **Java** with **Spring Boot**
- **Spring Web** — REST controllers
- **Spring Data JPA** — persistence
- **Jakarta Bean Validation** (via Hibernate Validator) — request validation
- Java **Records** — used throughout for DTOs and internal request objects (immutable, concise)

## Architecture

Orbit follows a layered architecture with a deliberate separation between what the client sends/receives over HTTP and what the internal business logic actually works with:

```
HTTP Request
     │
     ▼
Request DTO  (validated shape client is allowed to send)
     │  (Mapper)
     ▼
Request Object  (plain internal command, no HTTP/validation baggage)
     │
     ▼
Service Layer  (business logic — defaults, rules, orchestration)
     │
     ▼
Entity  (persisted via Repository)
     │  (Mapper)
     ▼
Response DTO  (shape returned to client)
     │
     ▼
HTTP Response
```

**Why the extra layers?** The service layer never needs to know about Jakarta validation annotations, JSON, or HTTP — it only deals with plain internal objects. This keeps business logic reusable and testable independent of the web layer, and is a pattern that scales well if the project ever grows beyond a single REST entry point.

### Project structure

```
com.naviroq.orbit
├── controller/          → REST endpoints + global exception handling
├── domain/
│   ├── dto/              → Request DTOs (validated, HTTP-facing input)
│   │                       Response DTOs (HTTP-facing output)
│   ├── entity/           → JPA entities (Orbit, OrbitPriority, OrbitStatus)
│   └── *Request.java     → Internal command objects (service-facing, no HTTP/validation coupling)
├── exception/            → Custom domain exceptions (e.g. OrbitNotFoundException)
├── mapper/               → Translates between DTOs ⇄ internal requests ⇄ entities
├── repository/           → Spring Data JPA repositories
└── service/              → Business logic
```

## Core Concepts

| Layer | Role |
|---|---|
| **Entity** (`Orbit`) | What's actually stored in the database |
| **Request DTO** (`CreateOrbitRequestDto`, `UpdateOrbitRequestDto`) | What a client is *allowed* to send, with validation rules attached |
| **Internal Request** (`CreateOrbitRequest`, `UpdateOrbitRequest`) | Plain object the service layer works with — no validation/HTTP concerns |
| **Response DTO** (`OrbitDto`) | What's returned to the client — the same shape whether the data came from a create, read, or update |
| **Mapper** (`OrbitMapper`) | Translates between the shapes above, keeping conversion logic in one place instead of scattered across controllers/services |
| **Error DTO** (`ErrorDto`) | Consistent error shape returned for both validation failures and business errors (e.g. an Orbit that doesn't exist) |

### Error handling

A `@ControllerAdvice`-based `GlobalExceptionHandler` centralizes error responses across the API:

- **Validation failures** (`MethodArgumentNotValidException`, thrown automatically by `@Valid`) → `400 Bad Request` with the first failing field's custom message.
- **Not-found errors** (`OrbitNotFoundException`, thrown explicitly by the service layer when an id doesn't resolve to a real Orbit) → `404 Not Found`.

Both return the same `ErrorDto` shape, so clients have one consistent error format to handle regardless of failure type.

## Domain Model

An Orbit has:

- `id` — unique identifier (UUID)
- `title` — required, up to 255 characters
- `description` — optional, up to 1000 characters
- `dueDate` — optional, must be present or in the future
- `priority` — required (`HIGH`, `MEDIUM`, `LOW`)
- `status` — the task's current state

## Status

🚧 Actively being built as a learning project — currently working through the DTO/mapper/service layering and exception handling. Frontend not yet started.

## Getting Started

```bash
# clone and enter the project
git clone <repo-url>
cd Orbit

# run the app
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

*Built while learning Spring Boot, one layer at a time.*