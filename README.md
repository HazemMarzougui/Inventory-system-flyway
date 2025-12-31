# 🏭 Inventory Management System

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java)](https://www.java.com/) 
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.1-brightgreen?logo=springboot)](https://spring.io/projects/spring-boot) 
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql)](https://www.mysql.com/) 
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

---

## 📖 Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies](#technologies)
- [Architecture](#architecture)
- [Database & Migrations](#database--migrations)
- [Setup & Installation](#setup--installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Future Improvements](#future-improvements)
- [Contributing](#contributing)
- [License](#license)

---

## 📝 Project Overview
Inventory Management System is a **Spring Boot application** for managing products, suppliers, warehouses, and stock movements.  
It uses **Flyway** for database versioning and migrations and provides **CRUD operations via REST APIs**.

---

## ⚡ Features
- Manage **Products** with category, price, and suppliers.
- Manage **Suppliers** and their associated products.
- Manage **Warehouses** and inventory levels.
- Track **Stock Movements** with transaction date, quantity, and status.
- REST API endpoints for all entities.
- **Flyway** database migration with versioned scripts.

---

## 🛠️ Technologies
| Layer | Technology |
|-------|------------|
| Backend | Java 17, Spring Boot 4.0.1, Hibernate/JPA |
| Database | MySQL 8.0 |
| Database Versioning | Flyway |
| Build Tool | Maven |
| Testing | Postman |

---

## 🏗️ Architecture
Inventory Management System
├── Entities
│ ├── Product
│ ├── Supplier
│ ├── Warehouse
│ └── StockMovement
├── Repositories
├── Services
├── Controllers
└── Resources
└── db/migration (Flyway scripts)

yaml
Copier le code
- **Entities**: Define tables and relationships  
- **Repositories**: Data access layer (Spring Data JPA)  
- **Services**: Business logic layer  
- **Controllers**: REST API layer  
- **Flyway scripts**: Versioned database migrations  

---

## 💾 Database & Migrations
Flyway manages database schema changes.

**V1 (Initial Tables):**  
Creates tables for Product, Supplier, Warehouse, StockMovement.  
`src/main/resources/db/migration/V1__initial_tables.sql`

**V2 (Future Migration Example):**  
Adding description to Product or new tables.  

**Steps for new migration:**
1. Create SQL file: `V2__description.sql` in `src/main/resources/db/migration/`
2. Write migration script.
3. Run the app — Flyway applies migration automatically.

---

## ⚙️ Setup & Installation

```bash
# Clone repository
git clone https://github.com/HazemMarzougui/inventory-management-system.git
cd inventory-management-system
Configure MySQL database:

sql
Copier le code
CREATE DATABASE inventory_system;
Update application.properties:

properties
Copier le code
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_system?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
Build the project:

bash
Copier le code
mvn clean install
🚀 Running the Application
bash
Copier le code
mvn spring-boot:run
App will run on: http://localhost:8080

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

Use Postman to test endpoints. Sample payloads are in examples/.

🧪 Testing
Open Postman

Import InventoryManagementSystem.postman_collection.json (optional)

Test endpoints for Product, Supplier, Warehouse, StockMovement

🌟 Future Improvements
Add DTOs and validation

Implement authentication & authorization

Add frontend with Angular or React

Improve error handling & logging

🤝 Contributing
Fork the repository

Create a branch: git checkout -b feature/your-feature

Commit changes: git commit -m "Add feature"

Push: git push origin feature/your-feature

Open a Pull Request

📜 License
This project is licensed under the MIT License.

yaml
Copier le code

---

If you want, I can also make it **even fancier with emojis in headers, collapsible sections for API endpoints, and badges for build status or coverage**, so it looks very professional on GitHub.  

Do you want me to do that next?
