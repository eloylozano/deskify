# deskify

**Deskify** is a helpdesk platform designed to efficiently manage technical support tickets. It provides tools to organize, filter, and prioritize requests, enhancing communication between users and support agents.

## Main Features

- Ticket management: creation, updating, and closure.
- Advanced filters to search tickets by agent, status, priority, category, or date.
- User roles (Admin, Supervisor, Manager, Agent, User) with specific permissions.
- Dashboard for ticket statistics and metrics.
- Support for multiple subscription plans per user.

## Screenshots

_SOON_

## Technologies Used

- **Java 17**
- **Spring Boot (3.3.x)**: Main framework for the application.
- **Spring Data JPA**: Data Persistence.
- **MySQL**: Relational Database.
- **Swagger**: Interactive API documentation.
- **ModelMapper**: Entity-to-DTO conversion.
- **Thymeleaf**: SOON...

## Configuration

1. Clone the repository:
   git clone https://github.com/eloylozano/deskify.git
   cd deskify

2. Open /Backend/Deskify and run the enviroment
3. Create .env in /Application/Deskify and set:

```bash
VITE_API_URL=http://localhost:8080
```

4. Then install dependencies with 'npm install' & and run with 'npm run dev'

5. Repeat the process, create .env in /Frontend/Deskify

```bash
PUBLIC_APP_URL=http://localhost:5173/login
PUBLIC_APP_REGISTER_URL=http://localhost:5173/register
PUBLIC_APP_SUBS_URL=http://localhost:5173/subscription
```
6. Then install dependencies with 'npm install' & and run with 'npm run dev'

### Usage Instructions

Basic instructions on how to use the system once it is running.

1. Register as a user or log in with an admin account.
2. Create and assign tickets via the dashboard.
3. Filter tickets by agent, status, priority, or date in the list view.
4. Monitor the support team’s performance using the statistics module.

## Roles and Permissions

- **Admin**: Full access, including user management and system configuration.
- **Supervisor**: Monitors ticket quality and agent performance.
- **Manager**: Manages tickets and the support team’s performance.
- **Agent**: Responds to and manages assigned tickets.
- **User**: Creates tickets and tracks the status of their requests.

## Contribuciones

Contributions are welcome! Please follow these steps:

Fork the project.
Create a branch for your new feature or bug fix
git checkout -b feature/new-feature

## Autor

**Eloy Lozano Barrios**  
[LinkedIn](https://www.linkedin.com/in/eloylozano/) | [GitHub](https://github.com/eloylozano)

This project was developed as part of my final project for the Web Application Development course.

## Licencia

This project is licensed under the MIT License. See the LICENSE file for more details.
