# Spring Boot JWT Authentication and Authorization

This project demonstrates the implementation of a secure user management system using Spring Boot and JWT (JSON Web Token). It provides robust authentication and authorization mechanisms, supporting user registration, login, and role-based access control. Additionally, it includes a token refresh feature to ensure uninterrupted access to services.

## Features

### 1. User Registration (Signup API)
- Allows users to create accounts by providing:
  - **Email**
  - **Password** (encrypted for security)
  - **Name**
- User details are securely stored in the database.

### 2. User Login (Login API)
- Verifies user credentials (email and password).
- On successful login:
  - Returns a JWT token.
  - Provides a refresh token for token renewal.

### 3. Role-Based Access Control
- Users are assigned roles such as **Admin** or **User**.
- Two separate controllers ensure strict segregation of endpoints:
  - Admin endpoints: Accessible only by admin users.
  - User endpoints: Accessible only by normal users.
- Unauthorized access attempts are blocked with a **403 Forbidden** response.

### 4. Token Refresh API
- Provides a mechanism to obtain a new JWT token when the current token expires.
- Validates the refresh token before issuing a new JWT.

## Tech Stack
- **Java**: Core language
- **Spring Boot**: Framework for REST API development
- **Spring Security**: Authentication and authorization
- **JWT**: Token-based security
- **Hibernate**: ORM for database interaction
- **MySQL**: Database for user data storage

## Project Structure
