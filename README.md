# Wish List API

## Project Status: In Development

**Note:** This project is currently under active development. Features, API endpoints, and response structures might change.

## Table of Contents

1.  [Introduction](#introduction)
2.  [Features](#features)
3.  [Prerequisites](#prerequisites)
4.  [Getting Started](#getting-started)
    *   [Database Setup](#database-setup)
    *   [Running the Application](#running-the-application)
5.  [API Endpoints](#api-endpoints)
    *   [Authentication (`/auth`)](#authentication--auth)
    *   User Management (`/user`)
    *   Wishlist Management (`/wishlist`)
6.  Data Models
    *   User
    *   Wish
7.  Data Transfer Objects (DTOs)
8.  Error Handling
9.  Security
10. Technologies Used
11. Contributing
12. License

## Introduction

The Wish List API is a Spring Boot application designed to allow users to manage their personal wish lists. Users can register, log in, and then create, view, update, and delete wishes. Each wish is associated with a user and can have details like title, description, a link to the product, price, and priority.

## Features

*   **User Authentication:**
    *   User registration with password encryption.
    *   User login.
*   **User Management:**
    *   Update user details (full and partial).
    *   Retrieve user information.
    *   Delete users.
*   **Wishlist Management:**
    *   Create new wishes associated with a user.
    *   Update existing wishes.
    *   Retrieve specific wishes.
    *   Delete wishes.
*   **Data Validation:** Input validation for DTOs.
*   **Centralized Exception Handling:** Consistent error responses for common issues.

## Prerequisites

*   Java JDK 17 or higher
*   Maven 3.6+ (or Gradle, if you switch)
*   PostgreSQL database server

## Getting Started

### Database Setup

1.  Ensure you have a PostgreSQL instance running.
2.  Create a database (e.g., `wishlist_db`).
3.  Configure the database connection in `src/main/resources/application.properties`:

    