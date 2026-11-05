# DemoQA Automation Framework

A Java-based test automation project for [DemoQA](https://demoqa.com/), combining **web UI testing** with **REST API testing** in a structured, maintainable framework.

The framework uses **Selenium WebDriver**, **TestNG**, **REST Assured**, and **Maven**. UI tests follow the **Page Object Model (POM)** pattern to separate test scenarios from browser interactions and element locators.

> **Project status:** The local smoke suite was executed successfully on **November 5, 2026**, with **9 tests run, 0 failures, 0 errors, and 0 skipped**. A successful GitHub Actions run has not yet been verified.

## Technology Stack

| Technology | Purpose |
| --- | --- |
| Java | Test implementation and framework code |
| Selenium WebDriver | Browser automation for UI tests |
| TestNG | Test execution, assertions, groups, and data providers |
| REST Assured | HTTP requests and API response validation |
| Maven | Dependency management, build, and test execution |
| Maven Surefire | Runs tests and produces test reports |
| GitHub Actions | CI workflow configuration (requires validation on GitHub) |

## Project Structure

```text
demoqa-automation-framework/
├── .github/
│   └── workflows/               # GitHub Actions workflow(s)
├── src/
│   └── test/
│       ├── java/
│       │   ├── base/            # DriverFactory, BaseUiTest, BaseApiTest
│       │   ├── pages/           # Page Object Model classes
│       │   ├── tests/
│       │   │   ├── ui/          # Selenium UI tests
│       │   │   └── api/         # REST Assured API tests
│       │   ├── utils/           # Settings and test data helpers
│       │   └── listeners/       # TestNG listeners (e.g., screenshots)
│       └── resources/           # Test resources and configuration
├── pom.xml                      # Maven build configuration
├── testng.xml                   # Smoke suite
├── testng-regression.xml        # Regression suite configuration
└── README.md
```

> This tree summarizes the main components. The exact set of source files and CI workflow names may evolve as the framework is expanded.

## Framework Design

- **`DriverFactory`** creates and manages browser driver instances. The framework uses `ThreadLocal` to associate a driver with the executing thread.
- **`BaseUiTest`** handles shared UI test setup and teardown.
- **`BaseApiTest`** provides common API request setup for REST Assured tests.
- **Page Objects** encapsulate locators and interactions, keeping test cases focused on scenarios and assertions.
- **TestNG** organizes test methods using groups, including `smoke` and `api`.
- **`ScreenshotListener`** attempts to capture a screenshot when a UI test fails.

## Test Coverage

### UI Automation

The smoke suite references UI test classes for the following DemoQA components:

- Text Box
- Check Box
- Radio Button
- Buttons
- Alerts

Additional page objects and regression scenarios can be developed without duplicating browser interaction code.

### API Automation

The `BookStoreApiTests` class covers examples such as:

- Retrieving the book catalog (`GET /BookStore/v1/Books`)
- Retrieving a book by ISBN (`GET /BookStore/v1/Book`)
- Validating the response for an unknown user (`POST /Account/v1/Authorized`)

API assertions reflect the response observed during local testing; application behavior and endpoint contracts should be rechecked when updating the suite.

## Prerequisites

- A compatible Java JDK
- Apache Maven
- Google Chrome (for Chrome-based UI runs)
- Git (optional, for cloning and contributing)

Check your environment:

```bash
java -version
mvn -version
```

## Getting Started

Clone the repository and enter the project directory:

```bash
git clone https://github.com/YOUR_USERNAME/demoqa-automation-framework.git
cd demoqa-automation-framework
```

Replace `YOUR_USERNAME` with your GitHub username after publishing the repository.

Run the configured smoke suite:

```bash
mvn clean test
```

Run a specific test class:

```bash
mvn test -Dtest=CheckBoxTests
```

Run a specific test method:

```bash
mvn test -Dtest=CheckBoxTests#homeSelectionDisplaysResult
```

Run the configured regression suite:

```bash
mvn test -Dsurefire.suiteXmlFiles=testng-regression.xml
```

> **Note:** The exact supported browser and runtime settings are defined by the project's current Java configuration. Check the `Settings` class and `pom.xml` before adding command-line overrides. Running a specific method with `-Dtest` is not equivalent to running the complete XML suite.

## Test Execution with TestNG

The `testng.xml` suite separates browser-based and API tests:

- **UI Smoke:** runs test methods tagged with the `smoke` group in the listed UI test classes.
- **API Smoke:** runs test methods tagged with the `api` group in `BookStoreApiTests`.
- **Listener:** registers `listeners.ScreenshotListener`.
- **Execution mode:** `parallel="false"` for controlled sequential execution.

## Reports and Troubleshooting

After a Maven test run, inspect the generated test output under:

```text
target/surefire-reports/
```

When a UI test fails, the registered listener attempts to save a screenshot under:

```text
target/screenshots/
```

Screenshot capture depends on the browser session still being available at the time of failure.

A practical debugging workflow is to:

1. Read the failing TestNG assertion or stack trace.
2. Check the related Page Object locator or API response.
3. Inspect the page's current DOM using browser developer tools when UI locators fail.
4. Fix the underlying problem and rerun the affected test before executing the full suite.

## CI/CD

The repository contains GitHub Actions workflow configuration intended to execute automated checks in CI. **CI success should be confirmed after pushing the project to GitHub**, since the verified green run so far was performed locally.

## Roadmap

- [x] Implement a Java-based UI and API automation framework
- [x] Configure TestNG smoke tests
- [x] Execute the local smoke suite successfully (9/9 passing)
- [ ] Add more positive, negative, and boundary-value scenarios
- [ ] Review and strengthen assertions and test isolation
- [ ] Verify GitHub Actions execution on a hosted runner
- [ ] Improve reporting and execution documentation

## Responsible Testing

[DemoQA](https://demoqa.com/) is a public demonstration website. Keep automated test volume low and avoid running load or stress tests against public services without explicit authorization.

## Author

**Luqman Deeb**  
Software Quality Assurance / Test Automation

---

*This project is developed for hands-on learning, framework design practice, and QA automation portfolio demonstration.*
