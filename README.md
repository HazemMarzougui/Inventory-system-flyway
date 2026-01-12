# 🏭 Inventory Management System

Java Spring Boot & MySQL REST API with Flyway migrations and JWT security  
**License:** MIT

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

Inventory Management System is a Spring Boot application for managing products, suppliers, warehouses, and stock movements.  
It uses **Flyway for database versioning**, **JWT for security**, and provides robust REST APIs for CRUD operations.

---

## ⚡ Features

- Full CRUD for Products, Suppliers, Warehouses  
- Track inventory via Stock Movements  
- Role‑based access control (ADMIN / STAFF)  
- Versioned Flyway database migrations  
- Testable via Postman collection  

---

## 🛠️ Technologies

| Layer          | Technology                        |
|----------------|-----------------------------------|
| Backend        | Java 17, Spring Boot 4.0.1        |
| Database       | MySQL 8.0                         |
| DB Versioning  | Flyway                            |
| Security       | Spring Security + JWT             |
| Build Tool     | Maven                             |
| Testing        | Postman                           |

---

## 🏗️ Architecture
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
text- **Entities**: Domain objects mapped to database tables  
- **Repositories**: Database access (Spring Data JPA)  
- **Services**: Business logic  
- **Controllers**: REST API endpoints  
- **Flyway scripts**: Versioned SQL migrations  

---

## 💾 Database & Migrations

Flyway manages database schema changes automatically on startup.

### Migration version files:

- `V1__initial_tables.sql`: Creates products, suppliers, warehouses, stock movements  
- `V2__constraints_and_indexes.sql`: Adds indexes and constraints  
- `V3__audit_columns_data_integrity.sql`: Adds timestamps & check constraints  
- `V4__create_warehouse_stock_snapshot_table.sql`: Snapshot table for current stock  
- `V5__create_security_schema.sql`: Roles and users tables  

**To add a new migration:**
1. Create a new SQL file in `src/main/resources/db/migration/`  
2. Name it like: `V6__your_description.sql`  
3. Write migration script  
4. Restart the app — Flyway applies it automatically  

---

## ⚙️ Setup & Installation

### 1️⃣ Clone Repository

```bash
git clone https://github.com/HazemMarzougui/Inventory-system-flyway.git
cd Inventory-system-flyway
2️⃣ Configure MySQL
SQLCREATE DATABASE inventory_system;
Update src/main/resources/application.properties:
propertiesspring.datasource.url=jdbc:mysql://localhost:3306/inventory_system?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

# JWT settings
jwt.secret=your_secret_key_here
jwt.expirationMs=86400000

🚀 Running the Application
Bashmvn clean install
mvn spring-boot:run
Application will run at: http://localhost:8080

📡 API Endpoints
Auth




















MethodEndpointDescriptionPOST/api/auth/registerRegister new STAFF userPOST/api/auth/loginLogin and get JWT token
Products



































MethodEndpointDescriptionPOST/api/productsCreate productGET/api/productsList all productsGET/api/products/{id}Get product by IDPUT/api/products/{id}Update productDELETE/api/products/{id}Delete product
Suppliers




















MethodEndpointDescriptionPOST/api/suppliersCreate supplierGET/api/suppliersList suppliers
Warehouses




















MethodEndpointDescriptionPOST/api/warehousesCreate warehouseGET/api/warehousesList warehouses
Stock Movements




















MethodEndpointDescriptionPOST/api/stock-movementsAdd stock movementGET/api/stock-movementsList stock movements
Inventory Snapshot















MethodEndpointDescriptionGET/api/inventory/stock-levelsGet all stock levels
Protected endpoints require Bearer {{admin_token}} or {{staff_token}} depending on RBAC.

🧪 Testing
🧰 API Testing (Postman)
This project includes a ready-to-use Postman collection.
Location:
textpostman/Inventory-API.postman_collection.json
How to use:

Open Postman
Import the collection
Set environment variables:
admin_token
staff_token

Run requests in this order:
Auth → Products → Warehouses → Suppliers → Stock Movements → Snapshot

Run the RBAC tests (Staff vs Admin) to confirm security


🌟 Future Improvements

Add frontend (Angular or React)
Detailed integration tests (Testcontainers)
Improved error handling & logging
Pagination & filtering on GET endpoints


🤝 Contributing
You're welcome to contribute improvements.
To contribute:

Fork the repository
Make your changes locally
Commit with a clear message:

Bashgit add .
git commit -m "Describe your changes"

Push to your fork:

Bashgit push origin main

Open a Pull Request against this repo


📜 License
This project is licensed under the MIT License.
