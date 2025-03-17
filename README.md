# Selenium Java JUnit & Cloudbeat Example

A Selenium Java JUnit 5 project for testing the Sauce Demo website with Cloudbeat reporting

![Cloudbeat Results](https://github.com/cloudbeat-io/examples-java-junit/blob/main/preview/cloudbeat-results.jpg?raw=true)

## Prerequisites
- Java 11+
- Maven 3.6+
- Chrome browser installed

## Setup
1. Clone or download this project.
2. Navigate to the project directory: `cd selenium-saucedemo-tests`
3. Run `mvn clean install` to install dependencies.
4. Install CloudBeat dependencies:

```
mvn install:install-file -Dfile=C:\cb-java-junit\cb-kit\cb-kit-common-1.0.11-SNAPSHOT.jar
mvn install:install-file -Dfile=C:\cb-java-junit\cb-kit\cb-kit-selenium4-1.0.11-SNAPSHOT.jar
mvn install:install-file -Dfile=C:\cb-java-junit\cb-kit\cb-kit-junit5-1.0.11-SNAPSHOT.jar
```

## Implementing CloudBeat Reporting
1. Import CloudBeat JUnit extension:

```
import io.cloudbeat.junit.CbJunitExtension;
```

2. Wrap your test steps using startStep & endLastStep:

```
CbJunitExtension.startStep("Open Main Page");
loginPage.open();
loginPage.assertPageOpen();
CbJunitExtension.endLastStep();
```

## Running Tests
- Run all tests: `mvn test`
- Run a specific test class: `mvn test -Dtest=LoginTest`

## Project Structure
- `src/main/java/com/saucedemo/pages/` - Page Object classes
- `src/main/java/com/saucedemo/utils/` - Utility classes
- `src/test/java/com/saucedemo/tests/` - Test classes