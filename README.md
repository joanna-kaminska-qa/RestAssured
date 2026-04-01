# API Automation Testing Framework - RestAssured

![Java](https://img.shields.io/badge/Java-17+-blue)
![RestAssured](https://img.shields.io/badge/RestAssured-5.4.0-orange)
![JUnit](https://img.shields.io/badge/JUnit-5.10-purple)
![Gradle](https://img.shields.io/badge/Gradle-8.x-green)
![Jackson](https://img.shields.io/badge/Jackson-2.15-red)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow)
![Status: In%20Progress](https://img.shields.io/badge/Status-In%20Progress-orange)

A professional API testing framework built with **RestAssured**, focused on scalability, clean code, and reusability.

## Tech Stack
* **Java 17+**
* **RestAssured** (API Testing)
* **JUnit 5** (Test Runner)
* **Jackson Databind** (JSON Serialization/Deserialization)
* **Gradle** (Build Tool)

## Project Architecture
The project follows industry-standard testing patterns:

1. **BaseTest (Inheritance):** Shared configuration for all test classes. Uses `@BeforeAll` to initialize global request/response specifications.
2. **Request & Response Specifications:** Reusable configurations (BaseURI, Content-Type, Response Time check) to ensure **DRY (Don't Repeat Yourself)** principles.
3. **POJO Models (Plain Old Java Objects):** API resources (e.g., `Post`, `User`) mapped to Java classes. Leverages Jackson for seamless JSON <-> Object mapping.
4. **Configuration Management:** Environment-specific settings (URLs) moved to a `config.properties` file for easy maintenance.

## Testing Scope
* **Full CRUD Lifecycle:** Comprehensive tests for GET, POST, PUT, PATCH, and DELETE methods.
* **POJO-based Validation:** Verifying data types and response body integrity using Java objects.
* **Negative Testing:** Error handling verification and status code validation (e.g., 404 Not Found).
* **Partial Deserialization:** Implementing `@JsonIgnoreProperties` to map only relevant fields from large JSON payloads.

## How to run tests?
To run all tests from the terminal, use the following command:
```bash
./gradlew test
```
---

## License

This project is licensed under the **MIT License**.  
See the LICENSE file for details.

---

## Authors

Created by:

**Joanna Kamińska**  
GitHub: [https://github.com/joanna-kaminska-qa](https://github.com/joanna-kaminska-qa)

---