# University Management Console Application

This is a Spring Boot Java application with a console interface designed to manage a university system. The application consists of departments and lectors, where each lector can work in more than one department and holds a specific degree (assistant, associate professor, professor). All the data is stored in a relational database, and various commands can be executed via the console to interact with the system.

## Features

- **Head of Department Query:** Retrieve the head of a specific department.
- **Department Statistics:** View statistics for a department, including the count of assistants, associate professors, and professors.
- **Average Salary:** Calculate the average salary of employees within a department.
- **Employee Count:** Get the total number of employees in a department.
- **Global Search:** Perform a global search for lectors by name.

## Database Configuration

### Tables Creation

The database schema is managed using Flyway, which automatically handles database migrations. The following tables are created in the database:

- **departments:** Stores department information, including a unique name and a reference to the head of the department.
- **lectors:** Stores lector information, including name, salary, and degree.
- **department_lector:** A join table that maintains the many-to-many relationship between departments and lectors.

In addition to creating these tables, Flyway is also used to populate the database with mock data. This mock data includes sample departments and lectors, allowing for immediate testing and interaction with the application without requiring manual data entry. The mock data is inserted during the migration process, ensuring that the application is ready to use right after the initial setup.

### Docker Configuration

This project includes a `docker-compose.yml` file that sets up a PostgreSQL database along with pgAdmin for database management. All necessary credentials are already configured within the Spring Boot application.

- **PostgreSQL:** The relational database where all data related to departments and lectors is stored.
- **pgAdmin:** A web-based management tool for PostgreSQL databases, providing an easy way to manage the database schema and data.

Before building and running the project, you need to start the Docker containers with the PostgreSQL database and pgAdmin.

## How to Run

### Prerequisites

- **Java 17:** Ensure you have Java 17 installed on your machine. You can download it from [Oracle's official site](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
- Ensure you have [Docker](https://www.docker.com/) installed on your machine.

### Build and Run the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/n0rb33rt/university-management-console-app
   ```
2. Navigate to the project directory:
   ```bash
   cd university-management-console-app
   
3. Start the PostgreSQL and pgAdmin containers by running:

   ```bash
   docker-compose up -d
   ```
4. Build the project:
    ```bash
   ./mvnw clean install
   ```
5. Run the application:
    ```bash
   ./mvnw spring-boot:run
   ```
