# 🔐 Task Management System with JWT Authentication

A secure Spring Boot backend application that provides:

- User Signup & Login
- JWT Authentication
- Role-Based Authorization
- Task CRUD Operations
- Spring Security Integration

---

# 🚀 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- MySQL
- Lombok
- Maven

---

# 📌 Features

## 🔑 Authentication
- User Signup
- User Login
- Password Encryption using BCrypt
- JWT Token Generation

---

## 🔐 Authorization
### USER Role
- Create Task
- View Own Tasks
- Update Own Tasks

### ADMIN Role
- View All Tasks
- Delete Any Task

---

# 📂 Project Structure

```text
src/main/java
│
├── config
│   └── SecurityConfig.java
│
├── controller
│   ├── AuthController.java
│   └── TaskController.java
│
├── dto
│   ├── LoginRequestDto.java
│   ├── LoginResponseDto.java
│   ├── SignUpDto.java
│   ├── TaskDto.java
│   └── TaskResponseDto.java
│
├── entity
│   ├── User.java
│   └── Task.java
│
├── mapper
│   └── TaskMapper.java
│
├── repository
│   ├── UserRepository.java
│   └── TaskRepository.java
│
├── security
│   ├── JwtFilter.java
│   ├── JwtUtility.java
│   └── CustomUserDetailsService.java
│
└── service
    ├── AuthService.java
    └── TaskService.java
```

---

# 🔄 Application Flow

```text
Client Request
      ↓
Spring Security Filter Chain
      ↓
JWT Filter
      ↓
SecurityConfig Authorization
      ↓
Controller
      ↓
Service
      ↓
Repository
      ↓
Database
```

---

# 🗄️ Database Relationship

## One User → Many Tasks

```java
@OneToMany(mappedBy = "user")
private List<Task> tasks;
```

```java
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
```

---

# 🔥 API Endpoints

## Authentication APIs

### Signup
```http
POST /auth/signup
```

### Login
```http
POST /auth/login
```

---

## Task APIs

### Create Task
```http
POST /tasks
```

### Get My Tasks
```http
GET /tasks/my
```

### Get All Tasks (ADMIN)
```http
GET /tasks/all
```

### Update Task
```http
PUT /tasks/{id}
```

### Delete Task
```http
DELETE /tasks/{id}
```

---

# 🔐 JWT Authentication Flow

```text
Login
  ↓
Generate JWT Token
  ↓
Send Token to Client
  ↓
Client Sends Token in Header
  ↓
JWT Filter Validates Token
  ↓
Access Protected APIs
```

---

# ⚙️ application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskdb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secretKey=yourverylongsecretkey1234567890123456
```

---

# ▶️ How to Run the Project

## 1️⃣ Clone Repository

```bash
git clone <repository-url>
```

---

## 2️⃣ Create Database

```sql
CREATE DATABASE taskdb;
```

---

## 3️⃣ Run Application

```bash
mvn spring-boot:run
```

---

# 🧪 Postman Testing

## Signup

```http
POST http://localhost:8080/auth/signup
```

### Request Body

```json
{
  "username": "test@gmail.com",
  "password": "123456"
}
```

---

## Login

```http
POST http://localhost:8080/auth/login
```

### Request Body

```json
{
  "username": "test@gmail.com",
  "password": "123456"
}
```

---

## Authorization Header

```text
Authorization: Bearer YOUR_TOKEN
```

---

# 🚨 Common Errors

| Error | Reason |
|------|------|
| 401 Unauthorized | Invalid/Missing Token |
| 403 Forbidden | Access Denied |
| 500 Internal Server Error | Server-side Exception |

---

# 📚 Learning Outcomes

By building this project, you will learn:

- Spring Security
- JWT Authentication
- Role-Based Authorization
- DTO & Mapper Pattern
- Entity Relationships
- CRUD Operations
- Filter Chain
- Debugging Security Issues

---

# 👨‍💻 Author

Mahammad Yasin Nadaf
