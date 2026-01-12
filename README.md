# 🏭 Inventory Management System

## 🚀 Overview

The **Inventory Management System** is a cloud-native backend designed to manage products, warehouses, suppliers, and stock movements in a **secure, scalable, and production-ready** way.

This project focuses on **clean architecture**, **versioned database design**, and **enterprise-grade security** using Spring Boot and modern DevSecOps practices. It is actively under development, with core backend and security layers already implemented.

Repository: **Inventory-system-flyway**

---

## ✨ Features

### ✅ Implemented

* 🔐 **Authentication & Authorization**

  * JWT-based authentication
  * Role-Based Access Control (RBAC): `ADMIN`, `STAFF`
  * Method-level security with `@PreAuthorize`

* 🗄 **Versioned Database Schema**

  * Flyway migrations (V1 → V5)
  * Reproducible and traceable schema evolution

* 📦 **Inventory Core Modules**

  * Products
  * Warehouses
  * Suppliers
  * Stock Movements
  * Stock Snapshots

* 📄 **API Documentation**

  * Swagger / OpenAPI integration

* 🧱 **Clean Backend Architecture**

  * DTO pattern
  * Centralized exception handling
  * Consistent `ResponseEntity` responses

### 🛠 Planned / In Progress

* 🚀 Redis caching for high-read stock queries
* 🧪 Integration testing with Testcontainers (MySQL + Redis)
* 📊 Observability with Spring Boot Actuator, Prometheus & Grafana
* 🐳 Docker & Docker Compose
* ☸ Kubernetes deployment manifests
* 🔄 CI/CD pipelines with GitHub Actions
* 🔍 Code quality & security scanning with SonarQube

---

## 🛠 Tech Stack

* **Backend**: Spring Boot 3, Java 17
* **Database**: MySQL
* **Schema Versioning**: Flyway
* **Security**: Spring Security, JWT
* **Caching**: Redis (planned)
* **API Docs**: Swagger / OpenAPI
* **Build Tool**: Maven
* **Testing**: JUnit, Testcontainers (planned)
* **DevOps**: Docker, Kubernetes, GitHub Actions (planned)
* **Observability**: Prometheus, Grafana (planned)

---

## 🔐 Security Overview

The system uses **stateless JWT authentication** combined with **Spring Security**.

### Authentication Flow

1. User logs in via `/api/auth/login`
2. Server generates a signed JWT containing user roles
3. Client sends the token in the `Authorization` header
4. Requests are authorized based on roles and endpoint rules

### Roles

* **ADMIN**

  * Full access (users, inventory, admin endpoints)
* **STAFF**

  * Inventory-related access only

### Protected Routes Example

* `/api/admin/**` → `ADMIN`
* `/api/users/**` → `ADMIN`
* `/api/products/**`, `/api/inventory/**`, `/api/warehouses/**` → `ADMIN`, `STAFF`

Security is enforced using:

* `SecurityFilterChain`
* Custom `JwtAuthenticationFilter`
* `JwtUtils` for token generation & validation

---

## 📬 API Testing (Postman)

A complete Postman collection is provided for testing all endpoints.

* 📁 **Collection name**: `Inventory-API.postman_collection`
* 📌 Includes:

  * Authentication endpoints
  * Inventory CRUD operations
  * Role-based access testing

### Environment Variables

The Postman environment uses:

* `ADMIN_TOKEN`
* `STAFF_TOKEN`

These tokens are automatically reused across secured requests.

---

## 🚀 Getting Started

### ✅ Prerequisites

* Java 17+
* Maven 3.x
* MySQL
* Git

### 📥 Installation

1. Clone the repository

```bash
git clone https://github.com/<your-username>/Inventory-system-flyway.git
cd Inventory-system-flyway
```

2. Configure database & JWT settings in `application.yml`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/inventory_db
    username: root
    password: password

jwt:
  secret:
    key: your-secret-key
  expiration:
    time: 3600000
```

3. Run Flyway migrations and start the app

```bash
mvn clean install
mvn spring-boot:run
```

4. Access Swagger UI

```
http://localhost:8080/swagger-ui.html
```

---

## 📌 Project Status

This project is **actively under development**.

Current focus:

* Completing performance layer (Redis + caching)
* Adding integration tests
* Preparing Docker & Kubernetes deployment

---

## 🤝 Contributing

Contributions are welcome!

1. Fork the repository
2. Create a feature branch

```bash
git checkout -b feature/my-feature
```

3. Commit your changes

```bash
git commit -m "Add new feature"
```

4. Push and open a Pull Request

---

## 📄 License

This project is open-source and available for learning, experimentation, and extension.
