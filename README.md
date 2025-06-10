# Selenium Java JUnit & Cloudbeat Example

A Selenium Java JUnit 5 project for testing the Sauce Demo website with Cloudbeat reporting

![Cloudbeat Results](https://github.com/cloudbeat-io/examples-java-junit/blob/main/preview/cloudbeat-results.jpg?raw=true)

## Prerequisites
- Java 11+
- Maven 3.6+
- Chrome browser installed

## Setup & Usage
1. Clone or download this project.
2. Navigate to the project directory: `cd examples-java-junit`
3. Run all tests with `mvn test` or run a specific test class `mvn test -Dtest=ProductsTest`

## Implementing CloudBeat Reporting
1.1. Import CloudBeat step extension in your pages

```java
import io.cloudbeat.common.annotation.CbStep;

```

1.2. Add CbStep above your method
```java
@CbStep("Open Base URL")
public void open() {
    driver.get(baseUrl);
}
```

2.1. Import CloudBeat Junit extension in your tests

```java
import io.cloudbeat.junit.CbJunitExtension;
```

2.2. Extend CloudBeat Junit extension class

```java
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({ CbJunitExtension.class })
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
    }
}
```

2.3. Wrap your test steps using startStep & endLastStep:

```java
    @Test
    @DisplayName("Standard User Login Behaviour")
    public void standardUserLoginBehaviour() {
        CbJunitExtension.startStep("Open Main Page");
        loginPage.open();
        loginPage.assertPageOpen();
        CbJunitExtension.endLastStep();
    }
```

2.4. Or use an anonymous method:

```java
    import io.cloudbeat.junit.CbJunitExtension;

    CbJunitExtension.step("Method Name", () => {
        // code
    });
```

2.5 Attach Screenshot:

```java
    CbJunitExtension.attachScreenshot(screenshotData, true);
```

```java
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.io.IOException;
    import org.openqa.selenium.OutputType;
    import org.openqa.selenium.TakesScreenshot;
    import io.cloudbeat.junit.CbJunitExtension;

    public void takeScreenshot(String stepName) {
        CbJunitExtension.step("Take Screenshot", () -> {
            byte[] screenshotData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            CbJunitExtension.attachScreenshot(screenshotData, true);

            try {
                Path screenshotDir = Path.of("screenshots");
                if (!Files.exists(screenshotDir)) {
                    Files.createDirectories(screenshotDir);
                }

                String timestamp = new SimpleDateFormat("d.M.yy~HH_mm_ss").format(new Date());
                Path screenshotPath = screenshotDir.resolve(stepName + "_" + timestamp + ".png");
                Files.write(screenshotPath, screenshotData);

                System.out.println("Screenshot saved to file: " + screenshotPath.toAbsolutePath());
            } catch (IOException e) {
                System.err.println("Failed to save screenshot: " + e.getMessage());
            }
        });
    }

    // usage
    takeScreenshot("Add to cart");
```

## Project Structure
- `src/main/java/com/saucedemo/pages/` - Page Object classes
- `src/main/java/com/saucedemo/utils/` - Utility classes
- `src/test/java/com/saucedemo/tests/` - Test classes