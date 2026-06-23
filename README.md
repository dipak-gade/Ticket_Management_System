# Ticket Management System

## Project Overview

Ticket Management System is a Spring Boot REST API application used to manage users and support tickets. The application allows creating users, creating tickets, assigning tickets to users, updating ticket status and priority, and retrieving ticket information.

## Tech Stack

* Java 21
* Spring Boot 3.5.7
* Spring Data JPA
* PostgreSQL
* Maven
* Swagger / OpenAPI
* Lombok

## Features

* User Management
* Ticket Management
* Assign Ticket to User
* Change Ticket Status
* Change Ticket Priority
* Global Exception Handling
* Swagger Documentation
* PostgreSQL Integration

## API Endpoints

### User APIs

| Method | Endpoint  | Description   |
| ------ | --------- | ------------- |
| POST   | /user/add | Create User   |
| GET    | /user/all | Get All Users |

### Ticket APIs

| Method | Endpoint                           | Description             |
| ------ | ---------------------------------- | ----------------------- |
| POST   | /ticket/add                        | Create Ticket           |
| GET    | /ticket/all                        | Get All Tickets         |
| PUT    | /ticket/assign/{ticketId}/{userId} | Assign Ticket           |
| PUT    | /ticket/status/{ticketId}          | Change Status           |
| PUT    | /ticket/priority/{ticketId}        | Change Priority         |
| GET    | /ticket/user/{userId}              | Get Tickets By User     |
| GET    | /ticket/priority/{priority}        | Get Tickets By Priority |

## Swagger UI

http://localhost:8080/swagger-ui/index.html

## Database

PostgreSQL

## How to Run

1. Clone the repository.
2. Configure PostgreSQL database.
3. Set environment variables:

   * DB_USERNAME
   * DB_PASSWORD
4. Run the application.
5. Open Swagger UI.

## Future Enhancements

* Spring Security
* JWT Authentication
* Role Based Authorization
* Soft Delete
* Pagination & Sorting
