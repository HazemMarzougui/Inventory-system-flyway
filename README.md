🏭 Inventory Management System

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java)](https://www.java.com/) 
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.1-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot) 
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql)](https://www.mysql.com/) 
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

📖 Table of Contents

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

📝 Project Overview

Inventory Management System is a Spring Boot application for managing products, suppliers, warehouses, and stock movements.

It uses Flyway for database versioning and migrations and provides full CRUD operations via REST APIs.

⚡ Features

Manage Products with category, price, and suppliers

Manage Suppliers and their associated products

Manage Warehouses and inventory levels

Track Stock Movements (quantity, status, date)

REST API endpoints for all entities

Flyway database migration with versioned scripts

🛠️ Technologies
Layer	Technology
Backend	Java 17, Spring Boot 4.0.1, Hibernate/JPA
Database	MySQL 8.0
DB Versioning	Flyway
Build Tool	Maven
Testing	Postman
🏗️ Architecture
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


Entities: Define database tables and relationships

Repositories: Data access layer (Spring Data JPA)

Services: Business logic layer

Controllers: REST API layer

Flyway Scripts: Versioned DB migrations

💾 Database & Migrations

Flyway manages database schema changes.

V1 (Initial Tables)
Creates tables for Product, Supplier, Warehouse, StockMovement.
src/main/resources/db/migration/V1__initial_tables.sql

V2 (Example Future Migration)
Add description to Product or new tables.

Steps for new migration:

Create SQL file: V2__description.sql in src/main/resources/db/migration/

Write migration script

Run the application — Flyway applies the migration automatically

⚙️ Setup & Installation
1️⃣ Clone Repository
git clone https://github.com/HazemMarzougui/inventory-management-system.git
cd inventory-management-system

2️⃣ Configure MySQL Database
CREATE DATABASE inventory_system;


Update src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/inventory_system?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

3️⃣ Build the Project
mvn clean install

🚀 Running the Application
mvn spring-boot:run


App will run at: http://localhost:8080

📡 API Endpoints
Product
Method	Endpoint	Description
POST	/api/products	Add product
GET	/api/products	Get all products
GET	/api/products/{id}	Get product by ID
PUT	/api/products/{id}	Update product
DELETE	/api/products/{id}	Delete product
Supplier
Method	Endpoint	Description
POST	/api/suppliers	Add supplier
GET	/api/suppliers	Get all suppliers
Warehouse
Method	Endpoint	Description
POST	/api/warehouses	Add warehouse
GET	/api/warehouses	Get all warehouses
Stock Movement
Method	Endpoint	Description
POST	/api/stock-movements	Add stock movement
GET	/api/stock-movements	Get all stock movements

Use Postman to test endpoints. Sample payloads are in examples/ folder.

🧪 Testing
API Testing (Postman)

This project includes a ready-to-use Postman collection.

📁 Location:
postman/Inventory-API.postman_collection.json

How to use:

Import the collection into Postman

Set environment variables:

admin_token

staff_token

Run requests in order:
Auth → Products → Warehouses → Stock

🌟 Future Improvements

Add DTOs and validation

Implement authentication & authorization (JWT)

Add frontend with Angular or React

Improve error handling & logging

🤝 Contributing

You’re welcome to contribute improvements.

To contribute:

Fork the repository

Make your changes locally

Commit with a clear message:

git add .
git commit -m "Describe your changes"


Push to your fork:

git push origin main


Open a Pull Request against this repo

📜 License

This project is licensed under the MIT License.
