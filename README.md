```markdown
# 🏭 Inventory Management System

## 🚀 Overview
Inventory Management System is a Spring Boot application for managing products, suppliers, warehouses, and stock movements. It uses **Flyway for database versioning**, **JWT for security**, and provides robust REST APIs for CRUD operations.

---

## ✨ Features
- 🔹 **Full CRUD Operations**: Complete Create, Read, Update, Delete for Products, Suppliers, Warehouses
- 📊 **Inventory Tracking**: Track inventory changes via Stock Movements
- 🔐 **Role-Based Access Control**: ADMIN and STAFF roles with different permissions
- 🗄️ **Database Versioning**: Flyway manages database schema changes
- 🧪 **Ready-to-Use Testing**: Complete Postman collection for API testing

---

## 🛠️ Technologies Used

| Layer | Technology |
|-------|------------|
| **Backend** | Java 17, Spring Boot 4.0.1 |
| **Database** | MySQL 8.0 |
| **Database Versioning** | Flyway |
| **Security** | Spring Security + JWT |
| **Build Tool** | Maven |
| **Testing** | Postman |
| **API Documentation** | Built-in Spring Boot |

---

## 🏗️ Architecture
```bash
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
Repositories: Database access using Spring Data JPA
Services: Business logic layer
Controllers: REST API endpoints
Flyway scripts: Versioned SQL migrations

🚀 Getting Started
✅ Prerequisites
bash
# Check if you have required tools installed
java -version  # Should be JDK 17+
mvn -version   # Should be Maven 3.x
mysql --version # Should be MySQL 8.0+
📥 Installation
1. Clone the Repository
bash
git clone https://github.com/HazemMarzougui/Inventory-system-flyway.git
cd Inventory-system-flyway
2. Configure Database
sql
-- In MySQL terminal
CREATE DATABASE inventory_system;
Update the database configuration in src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_system?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

# JWT settings
jwt.secret=your_secret_key_here
jwt.expirationMs=86400000
3. Build and Run the Application
bash
mvn clean install
mvn spring-boot:run
4. Access the Application
bash
# Application will be available at:
curl http://localhost:8080
📡 API Endpoints
🔐 Authentication
Method	Endpoint	Description
POST	/api/auth/register	Register new STAFF user
POST	/api/auth/login	Login and get JWT token
📦 Products
Method	Endpoint	Description
POST	/api/products	Create a new product
GET	/api/products	List all products
GET	/api/products/{id}	Get product by ID
PUT	/api/products/{id}	Update product
DELETE	/api/products/{id}	Delete product
🏢 Suppliers
Method	Endpoint	Description
POST	/api/suppliers	Create a new supplier
GET	/api/suppliers	List all suppliers
🏭 Warehouses
Method	Endpoint	Description
POST	/api/warehouses	Create a new warehouse
GET	/api/warehouses	List all warehouses
📊 Stock Movements
Method	Endpoint	Description
POST	/api/stock-movements	Add stock movement
GET	/api/stock-movements	List stock movements
📈 Inventory Snapshot
Method	Endpoint	Description
GET	/api/inventory/stock-levels	Get all stock levels
Note: Protected endpoints require Bearer token with appropriate role (ADMIN or STAFF).

🧪 Testing
Postman Collection
bash
# Postman collection is located at:
postman/Inventory-API.postman_collection.json
Setup Instructions:

Open Postman

Import the collection file

Set environment variables:

admin_token (for ADMIN role tests)

staff_token (for STAFF role tests)

Execute requests in sequence:

bash
# Test execution order:
Auth → Products → Warehouses → Suppliers → Stock Movements → Snapshot
💾 Database Migrations
bash
# Migration files are located at:
src/main/resources/db/migration/
Migration Files:
V1__initial_tables.sql - Creates core tables

V2__constraints_and_indexes.sql - Adds constraints and indexes

V3__audit_columns_data_integrity.sql - Adds timestamps and check constraints

V4__create_warehouse_stock_snapshot_table.sql - Creates stock snapshot table

V5__create_security_schema.sql - Creates roles and users tables

Creating New Migrations:
bash
# 1. Navigate to migrations directory
cd src/main/resources/db/migration/

# 2. Create new migration file
touch V6__your_description.sql

# 3. Write your SQL migration script
# 4. Restart application - Flyway applies it automatically
🌟 Future Improvements
🔍 Advanced Search: Add pagination, filtering, and sorting to GET endpoints

📊 Analytics Dashboard: Provide insights into inventory trends

📱 Frontend Application: Develop React or Angular frontend

🧪 Enhanced Testing: Add Testcontainers for integration tests

📝 Improved Logging: Comprehensive logging and error handling

🔄 Real-time Updates: WebSocket support for live inventory updates

🤝 Contributing
bash
# 1. Fork the repository
# 2. Clone your fork
git clone https://github.com/YOUR-USERNAME/Inventory-system-flyway.git

# 3. Create feature branch
git checkout -b feature/your-feature-name

# 4. Make changes and commit
git add .
git commit -m "Add: Description of your changes"

# 5. Push to your fork
git push origin feature/your-feature-name
📜 License
This project is licensed under the MIT License. See the LICENSE file for details.
