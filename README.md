> WORK IN PROGRESS

# Sensor Data Ingestion Microservices

A event-driven microservices architecture for sensor data ingestion and analytics

**Developed with focus on Functional Error Handling (Result Pattern/Either), Object Calisthenics and DDD
in addition, i created a slide comparing the trade-offs between the different approaches used in services (apresentacao.pdf) file.** 

## 💻 Tech Stack

<div style="display: flex; flex-wrap: wrap; gap: 15px; margin-top: 10px; margin-bottom: 20px;">
  <img align="center" alt="Java" height="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg"/>
  <img align="center" alt="Spring" height="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original.svg"/>
  <img align="center" alt="PostgreSQL" height="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postgresql/postgresql-original.svg"/>
  <img align="center" alt="MongoDB" height="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/mongodb/mongodb-original.svg"/>
  <img align="center" alt="RabbitMQ" height="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/rabbitmq/rabbitmq-original.svg"/>
  <img align="center" alt="Docker" height="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/docker/docker-original.svg"/>
  <img align="center" alt="JUnit" height="50" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/junit/junit-original.svg" />
</div>

## 📖 About the Repository

This repository contains two microservices (`operations-service` and `analytics-service`) that demonstrate the evolution from a classic anemic CRUD to a Rich Domain Model. 
I developed the system to understand the trade-offs of different ways to design a system

## Key Features

###  Domain Protection & Predictability
- **Result Pattern (Either Monad):** Replaces traditional Exception throwing for business rules, making the execution flow predictable and explicit.
- **Object Calisthenics (Value Objects):** Wraps primitive types to guarantee state validity upon instantiation and avoid invalid data representation.

###  Event-Driven Architecture
- **Asynchronous Communication:** Seamless integration between microservices using RabbitMQ (Exchanges, Queues, and Bindings).
- **Event DTOs:** Clean separation between the complex Domain Model and the payload that travels across the network.

###  CQRS-inspired Separation
- **Operations Service:** Handles complex business rules, data validation, and relational storage using PostgreSQL.
- **Analytics Service:** Acts as a consumer that saves processed events into MongoDB for fast reads and dashboarding, utilizing a more pragmatic architectural approach.

###  Code Quality & Testing
- **Unit Tests:** Blazing-fast tests for *Value Objects* in pure Java, without loading the Spring Context.
- **Service Orchestration Tests:** Validation of the integration flow using *JUnit 5* and *Mockito*.

## TO-DO
- [ ] Implement Dead Letter Queues (DLQ) and retry policies in RabbitMQ.
- [ ] Develop a Front-end dashboard to display real-time sensor metrics from the Analytics API.

---

## 🛠️ How to run the project locally

### Prerequisites
- Java 21+
- Docker & Docker Compose
- Maven (Embedded via wrapper)

### Infrastructure Setup (Databases & Broker)
1. Navigate to the root folder of the repository.
2. Start the required infrastructure (PostgreSQL, MongoDB, and RabbitMQ) using Docker Compose:

   ```bash
   docker-compose up -d
