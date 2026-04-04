# API Automation Testing Framework - RestAssured

![Java](https://img.shields.io/badge/Java-21-blue)
![RestAssured](https://img.shields.io/badge/RestAssured-5.4.0-orange)
![JUnit](https://img.shields.io/badge/JUnit-5.10-purple)
![Gradle](https://img.shields.io/badge/Gradle-8.x-green)
![Jackson](https://img.shields.io/badge/Jackson-2.15-red)
![Hamcrest](https://img.shields.io/badge/Hamcrest-2.2-brown)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow)
![Status: Completed](https://img.shields.io/badge/Status-Completed-brightgreen)

A professional API testing framework built with **RestAssured**, focused on scalability, clean code, and reusability.

## Tech Stack
* **Java 21**
* **RestAssured** (API Testing)
* **JUnit 5** (Test Runner)
* **Hamcrest** (Advanced Assertions)
* **Jackson Databind** (JSON Serialization/Deserialization)
* **Gradle** (Build Tool)

## Project Architecture
The project follows industry-standard testing patterns:

1. **BaseTest (Inheritance):** Shared configuration for all test classes. Uses `@BeforeAll` to initialize global request/response specifications.
2. **Request & Response Specifications:** Reusable configurations (BaseURI, Content-Type, Response Time check) to ensure **DRY (Don't Repeat Yourself)** principles.
3. **POJO Models (Plain Old Java Objects):** API resources (e.g., `Post`, `User`) mapped to Java classes. Leverages Jackson for seamless JSON <-> Object mapping.
4. **Configuration Management:** Environment-specific settings (URLs) moved to a `config.properties` file for easy maintenance.

## Advanced Validation Techniques
The framework utilizes advanced Java features and matchers to ensure robust and readable tests:

* **Hamcrest Matchers:** Instead of standard JUnit assertions, the project uses Hamcrest for "readable-as-English" assertions (`assertThat`, `is`, `hasItem`, `hasProperty`).
* **Java Stream API:** Used for sophisticated data filtering and searching within large API responses (Lists of POJOs).
* **Collection Validation:** Efficiently verifying the existence of specific data within collections without manual loops.

## Data-Driven Testing & Security
To maximize test coverage and simulate real-world scenarios, the framework implements:

* **JUnit 5 Parameterized Tests:** Using `@ValueSource` for quick multi-scenario execution and `@CsvFileSource` for large-scale data sets stored in external `.csv` files.
* **External Data Management:** Separation of test logic from data by reading resources from `src/test/resources`.
* **API Security & Authentication:** Implementation of various auth methods, including **OAuth2 (Bearer Tokens)** and **Query Parameters** (e.g., `authuser`) identified via **Chrome DevTools** network analysis.
* **Log-driven Debugging:** Using `.log().uri()` and `.log().all()` to verify dynamic URL construction and header integrity.
* **Nested Object Validation:** Verification of deep JSON structures (e.g., User -> Address -> Zipcode) using Java Stream API and Hamcrest matchers.

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