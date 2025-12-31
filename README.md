Inventory Management System






Table of Contents

Project Overview

Features

Technologies

Architecture

Database & Migrations

Setup & Installation

Running the Application

API Endpoints

Testing

Future Improvements

Contributing

License

Project Overview

Inventory Management System is a Spring Boot application that allows managing products, suppliers, warehouses, and stock movements. It uses Flyway for database versioning and migrations and supports CRUD operations through REST APIs.

Features

Manage Products with category, price, and suppliers.

Manage Suppliers and their associated products.

Manage Warehouses and their inventory levels.

Track Stock Movements with transaction date, quantity, and status.

REST API endpoints for all entities.

Flyway database migration with versioned scripts.

Technologies

Backend: Java 17, Spring Boot 4.0.1, Hibernate/JPA

Database: MySQL 8.0

Database Versioning: Flyway

Build Tool: Maven

Testing: Postman

Architecture
Inventory Management System
├── Entities
│   ├── Product
│   ├── Supplier
│   ├── Warehouse
│   └── StockMovement
├── Repositories
├── Services
├── Controllers
└── Resources
    └── db/migration (Flyway scripts)


Entities: Define tables and relationships

Repositories: Data access layer (Spring Data JPA)

Services: Business logic layer

Controllers: REST API layer

Flyway scripts: Versioned migrations for database

Database & Migrations

Flyway is used to manage database schema changes.

V1 (Initial Tables): Creates tables for Product, Supplier, Warehouse, StockMovement.
src/main/resources/db/migration/V1__initial_tables.sql

V2 (Future Migration): Example — adding description to Product or adding new tables.

Steps to add a migration:

Create a new SQL file in src/main/resources/db/migration/ named V2__your_description.sql.

Write your SQL migration script.

Run the application — Flyway will automatically apply the new migration.

Setup & Installation

Clone the repository:

git clone https://github.com/<your-username>/inventory-management-system.git
cd inventory-management-system


Configure MySQL database:

Create database inventory_system

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/inventory_system?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true


Build the project with Maven:

mvn clean install

Running the Application
mvn spring-boot:run


Application will run on: http://localhost:8080

API Endpoints
Product

POST /api/products → Add product

GET /api/products → Get all products

GET /api/products/{id} → Get product by ID

PUT /api/products/{id} → Update product

DELETE /api/products/{id} → Delete product

Supplier

POST /api/suppliers → Add supplier

GET /api/suppliers → Get all suppliers

Warehouse

POST /api/warehouses → Add warehouse

GET /api/warehouses → Get all warehouses

StockMovement

POST /api/stock-movements → Add stock movement

GET /api/stock-movements → Get all stock movements

Note: Use Postman to test all endpoints. Sample JSON payloads are provided in examples folder.

Testing

Open Postman

Import collection: InventoryManagementSystem.postman_collection.json (optional)

Test endpoints for Product, Supplier, Warehouse, StockMovement.

Future Improvements

Add DTOs and validation

Implement authentication and authorization

Add frontend with Angular or React

Improve error handling and logging

Contributing

Fork the repository

Create a branch: git checkout -b feature/your-feature

Commit changes: git commit -m "Add feature"

Push: git push origin feature/your-feature

Open a Pull Request

License

This project is licensed under the MIT License.
