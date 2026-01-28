## Spring Boot Microservices with Postgres (Dockerised for AWS)

This project contains two Java Spring Boot microservices (`user-service` and `order-service`) using Postgres, with Docker and `docker-compose` to simplify local development and AWS deployment.

### Prerequisites

- **Java** 21 (only needed if you want to run via Maven directly)
- **Maven** 3.9+ (for local builds)
- **Docker** and **Docker Compose**

### Run everything with Docker Compose

From the project root:

```bash
docker compose build
docker compose up
```

- `user-service` will be available on `http://localhost:8081`
  - `GET /users`
  - `GET /users/health`
- `order-service` will be available on `http://localhost:8082`
  - `GET /orders`
  - `GET /orders/health`
- Postgres will be exposed on `localhost:5432` (user: `appuser`, password: `apppassword`).

Stop the stack with:

```bash
docker compose down
```

### Run a single service locally with Maven

If you prefer to run a service without Docker:

```bash
cd user-service
mvn spring-boot:run

# in another terminal
cd order-service
mvn spring-boot:run
```

For local non-Docker runs, make sure you have a Postgres instance running and update the `spring.datasource` properties in each `application.yml` accordingly (or export the `SPRING_DATASOURCE_URL`, `DB_USER`, and `DB_PASSWORD` environment variables).

### High-level AWS deployment notes

- Build container images with:

```bash
docker build -t user-service:latest ./user-service
docker build -t order-service:latest ./order-service
```

- Push them to a registry such as **Amazon ECR**.
- Create an RDS Postgres instance and configure:
  - `SPRING_DATASOURCE_URL`
  - `DB_USER`
  - `DB_PASSWORD`
- Deploy the services on **ECS/Fargate** or **EKS**, wiring environment variables to the RDS connection details and exposing ports 8081 and 8082 via a load balancer or API Gateway.

