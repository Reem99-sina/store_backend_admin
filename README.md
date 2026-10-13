# Store Admin Backend

A RESTful backend API for managing an e-commerce store administration system, built with **Java, Spring Boot, Spring Security, and PostgreSQL**.

The application provides APIs for managing products, categories, users, promotions, and authentication, with role-based access control to protect administrative operations.

## 🚀 Tech Stack

- **Java** – Backend development
- **Spring Boot** – REST API development
- **Spring Security** – Authentication and authorization
- **JWT** – Token-based authentication
- **Spring Data JPA / Hibernate** – Database access and ORM
- **PostgreSQL** – Relational database
- **Maven** – Dependency management and build automation
- **Apache Tomcat** – Embedded application server

## ✨ Features

### 🔐 Authentication & Authorization
- User registration and login
- JWT-based authentication
- Role-based access control using Spring Security

### 📦 Product Management
- Retrieve all products and product details
- Create new products
- Update existing products
- Delete products

### 🗂️ Category Management
- Create, retrieve, update, and delete categories
- Manage product categories and their statuses

### 👥 User Management
- Retrieve users
- Create and update users
- Delete users
- Manage administrative access

### 🎟️ Promotions Management
- Retrieve and manage promotions
- Support promotional discount codes
- Configure promotion types and statuses
- Manage percentage-based and order-level discounts

### 📢 Campaigns Management
- Manage marketing campaigns
- Retrieve campaign details
- Associate promotions with campaigns
- Manage campaign information and statuses

### 📊 Inventory Management
- Manage inventory records
- Retrieve inventory information
- Track product stock quantities
- Create, update, and delete inventory records

### 🛠️ Technologies
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven

## 📁 Project Structure

```text
store-backend-admin/
├── src/
│   └── main/
│       ├── java/
│       │   └── store_backend_admin/
│       │       ├── config/
│       │       ├── controller/
│       │       ├── entity/
│       │       ├── repository/
│       │       ├── security/
│       │       └── StoreBackendAdminApplication.java
│       └── resources/
│           └── application.properties
├── pom.xml
└── README.md
```

## ⚙️ Prerequisites

Make sure you have installed:

- Java JDK
- Maven
- PostgreSQL
- Git

## 🛠️ Installation & Setup

### 1. Clone the repository

```bash
git clone https://github.com/Reem99-sina/store_backend_admin
cd store-backend-admin
```

Replace `YOUR_REPOSITORY_URL` with your GitHub repository URL.

### 2. Create the database

Create a PostgreSQL database named:

```sql
CREATE DATABASE store_admin;
```

Configure your database connection in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5435/store_admin
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

Update the database credentials and port according to your local environment.

**Note:** The database schema must already exist when using `ddl-auto=none`.

### 3. Configure environment variables

Set the required database credentials and JWT secret according to your application's configuration.

Never commit database passwords, JWT secrets, or other sensitive credentials to GitHub.

### 4. Run the application

Using Maven:

```bash
mvn spring-boot:run
```

Or run `StoreBackendAdminApplication.java` directly from your IDE.

The API will be available at:

```text
http://localhost:8080
```

The port may differ depending on your application configuration.

## 🔗 API Endpoints

### 📦 Products

| Method | Endpoint | Description |
|---|---|---|
| GET | `/products` | Get all products |
| GET | `/products/{id}` | Get product by ID |
| POST | `/products` | Create a new product |
| PATCH | `/products/{id}` | Update a product |
| DELETE | `/products/{id}` | Delete a product |

### 🗂️ Categories

| Method | Endpoint | Description |
|---|---|---|
| GET | `/categories` | Get all categories |
| GET | `/categories/{id}` | Get category by ID |
| POST | `/categories` | Create a new category |
| PATCH | `/categories/{id}` | Update a category |
| DELETE | `/categories/{id}` | Delete a category |

### 👥 Users

| Method | Endpoint | Description |
|---|---|---|
| GET | `/users` | Get all users |
| GET | `/users/{id}` | Get user by ID |
| POST | `/users` | Create a new user |
| PATCH | `/users/{id}` | Update a user |
| DELETE | `/users/{id}` | Delete a user |

### 🎟️ Promotions

| Method | Endpoint | Description |
|---|---|---|
| GET | `/promotions` | Get all promotions |
| GET | `/promotions/{id}` | Get promotion by ID |
| POST | `/promotions` | Create a new promotion |
| PATCH | `/promotions/{id}` | Update a promotion |
| DELETE | `/promotions/{id}` | Delete a promotion |

### 📢 Campaigns

| Method | Endpoint | Description |
|---|---|---|
| GET | `/campaigns` | Get all campaigns |
| GET | `/campaigns/{id}` | Get campaign by ID |
| POST | `/campaigns` | Create a new campaign |
| PATCH | `/campaigns/{id}` | Update a campaign |
| DELETE | `/campaigns/{id}` | Delete a campaign |

### 📊 Inventory

| Method | Endpoint | Description |
|---|---|---|
| GET | `/inventory` | Get all inventory records |
| GET | `/inventory/{id}` | Get inventory record by ID |
| POST | `/inventory` | Create an inventory record |
| PATCH | `/inventory/{id}` | Update an inventory record |
| DELETE | `/inventory/{id}` | Delete an inventory record |

## 🔐 Authentication

Protected endpoints require a valid JWT access token.

```http
Authorization: Bearer YOUR_JWT_TOKEN
```

## 🧪 Example Requests

Get all products:

```bash
curl http://localhost:8080/products
```

Get a product by ID:

```bash
curl http://localhost:8080/products/1
```

Create a product:

```bash
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{"name":"Sample Product"}'
```

Update a product:

```bash
curl -X PATCH http://localhost:8080/products/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{"name":"Updated Product"}'
```

Delete a product:

```bash
curl -X DELETE http://localhost:8080/products/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Note:** These examples assume the listed routes and request fields match your implementation. Replace sample payloads with the actual fields required by your entities or DTOs.


## 🔐 Security

The application uses Spring Security and JWT-based authentication to protect secured endpoints.

- Public endpoints can be accessed without authentication when configured accordingly.
- Protected endpoints require a valid JWT.
- Administrative operations are restricted by role-based authorization.

For protected endpoints, include the token in the request header:

```http
Authorization: Bearer YOUR_JWT_TOKEN
```

## 🧪 Testing the API

You can test endpoints using Postman, cURL, or another HTTP client.

Example:

```bash
curl http://localhost:8080/products
```

For protected endpoints:

```bash
curl -H "Authorization: Bearer YOUR_JWT_TOKEN" \
     http://localhost:8080/users
```

## 🔮 Future Improvements

- Product image upload and management
- Email verification
- Request validation and centralized exception handling
- API documentation with Swagger / OpenAPI
- Unit and integration testing
- Docker support and deployment configuration

## 👩‍💻 Author

**Reem Ebrahim**

Full-Stack Developer | React.js, Next.js, Node.js & Java Spring Boot

GitHub: [Reem99-sina](https://github.com/Reem99-sina)
