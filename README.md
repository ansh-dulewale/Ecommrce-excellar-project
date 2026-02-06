# 🛒 ShopEase Backend (Flipkart Clone)

## 👤 Contributor: **Ansh Dulewale**

This document outlines the backend modules developed by **Ansh** and provides a step-by-step guide for teammates to set up and run the backend server locally.

---

## 📌 Modules Implemented by Ansh

### ✅ Authentication & Security
- User Registration
- User Login
- Password hashing using **BCrypt**
- JWT generation and validation
- Custom JWT Authentication Filter
- Stateless Spring Security configuration
- Role-based secured APIs

### ✅ Cart Module
- Cart entity & CartItem entity
- One cart per user
- Add product to cart
- Increase quantity if product already exists
- Remove product from cart
- Clear cart after checkout
- Cart total calculation
- Secure cart access using JWT principal

### ✅ Order Module
- Order entity & OrderItem entity
- Place order from cart
- Checkout flow
- Order persistence
- Order status management
- Order history per user
- Cart cleanup after successful order
- DTO-based API responses

---

## 🧩 APIs Implemented

### 🔐 Auth APIs
```

POST /api/auth/register
POST /api/auth/login

```

### 🛒 Cart APIs
```

GET  /api/cart
POST /api/cart/add/{productId}
DELETE /api/cart/remove/{productId}

```

### 📦 Order APIs
```

POST /api/orders
POST /api/orders/checkout
GET  /api/orders

```

> ⚠ All Cart and Order APIs require a valid JWT token.

---

## 🔧 Tech Stack (Backend)

- Java 25
- Spring Boot 4.x
- Spring Security
- JWT (jjwt)
- Spring Data JPA
- MySQL
- Maven
- IntelliJ IDEA

---

## 🚀 How to Start the Backend Server

### 1️⃣ Prerequisites
Ensure the following are installed:
- Java 21+ (Java 25 recommended)
- Maven
- MySQL
- IntelliJ IDEA (recommended)

---

### 2️⃣ Clone the Repository
```bash
git clone <repository-url>
cd backend
```

---

### 3️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://<HOST>:<PORT>/<DB_NAME>
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> ⚠ Make sure the database is accessible before starting the server.

---

### 4️⃣ Run the Application

#### Using IntelliJ IDEA

* Open the backend project
* Locate `ShopeaseBackendApplication`
* Click **Run**

#### Using Terminal

```bash
mvn spring-boot:run
```

---

### 5️⃣ Verify Server

If the server starts successfully:

```
http://localhost:8080
```

Test login endpoint:

```
POST http://localhost:8080/api/auth/login
```

---

## 🔐 How to Access Secured APIs

1. Login using `/api/auth/login`
2. Copy the JWT token from the response
3. Add the token to request headers:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## ⚠ Important Notes for Teammates

* Always pull the latest code before testing
* JWT token is required for Cart & Order APIs
* Each user has exactly one cart
* Orders are user-specific and secured
* Cart is automatically cleared after checkout

---

## ✅ Module Status

| Module         | Status                              |
| -------------- | ----------------------------------- |
| Authentication | ✅ Done                              |
| Cart           | ✅ Done                              |
| Orders         | ✅ Done                              |
| Product        | 🔗 Dependency (Handled by teammate) |

---

## 📣 Final Notes

* Backend is ready for frontend integration
* APIs are secured and tested
* No pending tasks under Ansh’s responsibility

---

**End of README**
