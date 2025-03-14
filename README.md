# Selenium Java JUnit & Cloudbeat Example

A Selenium Java JUnit 5 project for testing the Sauce Demo website with Cloudbeat reporting

## Prerequisites
- Java 11+
- Maven 3.6+
- Chrome browser installed

## Setup
1. Clone or download this project.
2. Navigate to the project directory: `cd selenium-saucedemo-tests`
3. Run `mvn clean install` to install dependencies.

## Running Tests
- Run all tests: `mvn test`
- Run a specific test class: `mvn test -Dtest=LoginTest`

## Project Structure
- `src/main/java/com/saucedemo/pages/` - Page Object classes
- `src/main/java/com/saucedemo/utils/` - Utility classes
- `src/test/java/com/saucedemo/tests/` - Test classes