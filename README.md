# GTShop — Reward Points Shop (Spring Boot Backend)

A Spring Boot backend that powers a **reward points shop**: users can browse rewards, manage a cart, and redeem items using earned points.

> Built during **Generația Tech Microproject (Spring 2025)** as part of a 7-person team.  
> **My role:** Java Backend Developer

---

## Context

This project simulates a “Reward Shop” connected to a larger platform where users earn **Activity Points**.  
**Authentication and user identity were handled externally** by the Generația Tech platform; this backend focuses on the shop functionality.

---

## Key Features

- Browse rewards and view reward details (frontend-driven)
- Cart management:
  - add items to cart
  - remove items from cart
  - view cart contents
  - adjust quantities (plus/minus interactions handled via API calls)
- Checkout / redeem flow:
  - redeem cart items using available points
  - points decrease when completing checkout
- Persistent storage using **MySQL + Spring Data JPA**

---

## Tech Stack

- **Java 24**
- **Spring Boot 3.5.4**
- Spring Web (REST API)
- Spring Data JPA (Hibernate)
- MySQL (mysql-connector-j)
- Spring Boot Test (testing dependency included)

---

## API Endpoints (Backend)

- `POST /api/cart/add` — add an item to cart
- `POST /api/cart/remove` — remove an item from cart
- `GET  /api/cart` — get current cart
- `GET  /api/user/profile` — get current user profile (simulated/external auth context)
- `POST /api/checkout` — redeem items & spend points

---

## What I Learned

- Building REST APIs with Spring Boot

- Persisting data using Spring Data JPA + MySQL

- Designing backend endpoints for real UI interactions (cart quantity updates, checkout flow)

- Working in a cross-functional team (PM, design, marketing, frontend, backend)
