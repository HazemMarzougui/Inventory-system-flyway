🏭 Inventory Management System

Java Spring Boot & MySQL REST API with Flyway migrations and JWT security
License: MIT

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
It uses Flyway for database versioning, JWT for security, and exposes robust REST APIs for CRUD operations.

⚡ Features

Full CRUD for Products, Suppliers, Warehouses

Track inventory via Stock Movements

Role-based access control (ADMIN / STAFF)

Versioned Flyway database migrations

Testable via Postman collection

🛠️ Technologies
Layer	Technology
Backend	Java 17, Spring Boot 4.0.1
Database	MySQL 8.0
DB Versioning	Flyway
Security	Spring Security + JWT
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
    └── db/migration (Flyway SQL scripts)


Entities: Domain objects mapped to database tables

Repositories: Database access (Spring Data JPA)

Services: Business logic

Controllers: REST API endpoints

Flyway scripts: Versioned SQL migrations

💾 Database & Migrations

Flyway manages database schema changes automatically on startup.

Migration files

V1__initial_tables.sql – Creates core tables

V2__constraints_and_indexes.sql – Adds constraints & indexes

V3__audit_columns_data_integrity.sql – Audit fields & checks

V4__create_warehouse_stock_snapshot_table.sql – Stock snapshot

V5__create_security_schema.sql – Users & roles

Add a new migration

Create a SQL file in:

src/main/resources/db/migration/


Name it:

V6__your_description.sql


Write the SQL

Restart the app → Flyway runs it automatically

⚙️ Setup & Installation
1️⃣ Clone Repository
git clone https://github.com/HazemMarzougui/Inventory-system-flyway.git
cd Inventory-system-flyway

2️⃣ Configure MySQL

Create the database:

CREATE DATABASE inventory_system;


Update src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/inventory_system?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

# JWT settings
jwt.secret=your_secret_key_here
jwt.expirationMs=86400000

🚀 Running the Application
mvn clean install
mvn spring-boot:run


Application runs at:
👉 http://localhost:8080

📡 API Endpoints
🔐 Authentication
Method	Endpoint	Description
POST	/api/auth/register	Register STAFF user
POST	/api/auth/login	Login & receive JWT
📦 Products
Method	Endpoint	Description
POST	/api/products	Create product
GET	/api/products	List all products
GET	/api/products/{id}	Get product by ID
PUT	/api/products/{id}	Update product
DELETE	/api/products/{id}	Delete product
🏢 Suppliers
Method	Endpoint	Description
POST	/api/suppliers	Create supplier
GET	/api/suppliers	List suppliers
🏬 Warehouses
Method	Endpoint	Description
POST	/api/warehouses	Create warehouse
GET	/api/warehouses	List warehouses
🔄 Stock Movements
Method	Endpoint	Description
POST	/api/stock-movements	Add stock movement
GET	/api/stock-movements	List stock movements
📊 Inventory Snapshot
Method	Endpoint	Description
GET	/api/inventory/stock-levels	Get stock levels

🔒 Protected endpoints require
Authorization: Bearer {{admin_token}} or {{staff_token}}

🧪 Testing
🧰 Postman API Testing

Postman collection included:

postman/Inventory-API.postman_collection.json

Steps

Open Postman

Import the collection

Set environment variables:

admin_token

staff_token

Run in order:
Auth → Products → Warehouses → Suppliers → Stock Movements → Snapshot

Verify RBAC (Admin vs Staff)

🌟 Future Improvements

Frontend (Angular or React)

Integration tests (Testcontainers)

Improved error handling & logging

Pagination & filtering

🤝 Contributing

Contributions are welcome 🚀

git add .
git commit -m "Describe your changes"
git push origin main


Then open a Pull Request.

📜 License

This project is licensed under the MIT License.
