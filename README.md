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

**Entities**: Domain objects mapped to database tables  
**Repositories**: Database access using Spring Data JPA  
**Services**: Business logic layer  
**Controllers**: REST API endpoints  
**Flyway scripts**: Versioned SQL migrations

---

## 🚀 Getting Started

### ✅ Prerequisites
Ensure you have the following installed:
- Java Development Kit (JDK) 17 or higher
- Maven 3.x
- MySQL 8.0 or higher
- Git
- Postman (for API testing)

### 📥 Installation

#### 1. Clone the Repository
```bash
git clone https://github.com/HazemMarzougui/Inventory-system-flyway.git
cd Inventory-system-flyway
