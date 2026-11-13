# DemoQA Automation Framework

A Java-based test automation project for [DemoQA](https://demoqa.com/), combining **web UI testing** with **REST API testing** in a structured framework.

The project uses **Selenium WebDriver**, **TestNG**, **REST Assured**, and **Apache Maven**. UI tests use the **Page Object Model (POM)** design pattern to separate test scenarios from browser interactions and locators.

> **Execution evidence:** The local smoke suite was reported successful on **November 5, 2026** (9 tests run, 0 failures, 0 errors, 0 skipped). The Maven packaging build was subsequently confirmed locally on **November 13, 2026**. A successful GitHub Actions run has not yet been verified. Test results may change as DemoQA changes.

## Technology Stack

| Technology | Purpose |
| --- | --- |
| Java (release target: 17) | Framework and test implementation |
| Selenium WebDriver 4.52.0 | Browser automation |
| TestNG 7.11.0 | Test execution, assertions, and grouping |
| REST Assured 6.3.0 | REST API testing |
| Jackson Databind 2.18.3 | JSON processing |
| Maven | Dependency resolution, compilation, test execution, and packaging |
| Maven Surefire | TestNG execution and reports |
| Maven JAR Plugin | Packaging compiled test classes into a classified test JAR |
| GitHub Actions | Manually triggered CI workflow |

## Project Structure

```text
demoqa-automation-framework/
├── .github/
│   └── workflows/
│       └── manual-tests.yml
├── src/
│   └── test/
│       ├── java/
│       │   ├── base/          # DriverFactory and shared test setup
│       │   ├── pages/         # Page Object Model classes
│       │   ├── tests/
│       │   │   ├── ui/        # Selenium UI tests
│       │   │   └── api/       # REST Assured API tests
│       │   ├── utils/         # Configuration and test-data utilities
│       │   └── listeners/     # TestNG listeners and screenshots
│       └── resources/        # Test configuration and resources
├── docs/                      # QA documentation
├── test-data/                 # Test data
├── pom.xml                    # Maven build configuration and metadata
├── testng.xml                 # Smoke suite
├── testng-regression.xml      # Regression suite
└── README.md
```

Build output is generated under `target/` and is ignored by Git.

## Framework Design

- **`DriverFactory`** creates/manages browser sessions and uses `ThreadLocal` for per-thread driver association.
- **`BaseUiTest`** handles common UI setup and teardown.
- **`BaseApiTest`** provides shared REST Assured request setup.
- **Page Objects** encapsulate locators and interactions.
- **TestNG groups** select smoke, API, and regression scenarios.
- **`ScreenshotListener`** attempts to capture screenshots when UI tests fail.

## Test Coverage

**UI:** Text Box, Check Box, Radio Button, Buttons, and Alerts in the smoke suite. The regression suite also references Web Tables and Practice Form scenarios.

**API:** The Book Store test class exercises the book catalog (`GET /BookStore/v1/Books`), book lookup by ISBN (`GET /BookStore/v1/Book`), and the response for a nonexistent user (`POST /Account/v1/Authorized`). Assertions are based on previously observed responses and may require review if the public API changes.

## Prerequisites

- **JDK 17 or newer** (Maven compiles with `--release 17`; local build was performed with Temurin JDK 25)
- **Apache Maven 3.x** (local build used Maven 3.10.0)
- **Internet access** for Maven Central on initial dependency resolution
- **Google Chrome** for Chrome-based UI tests
- **Internet access to DemoQA** when running its UI/API integration tests

Check tools:

```bash
java -version
mvn -version
```

## Getting Started

```bash
git clone https://github.com/lucka605/demoqa-automation-framework.git
cd demoqa-automation-framework
```

### Build and package without executing external tests

```bash
mvn clean package -DskipTests
```

This command deletes old outputs, resolves dependencies, compiles the project, **compiles the test classes**, skips executing tests, and packages artifacts. It is the recommended repeatable build command when DemoQA or a browser may be unavailable.

Expected artifacts for version `1.0.0`:

```text
target/demoqa-automation-framework-1.0.0.jar
target/demoqa-automation-framework-1.0.0-tests.jar
```

**Why two JARs?** This project is a **test automation framework**, with its Java implementation under `src/test/java`, not `src/main/java`. The regular JAR therefore contains build metadata rather than application classes. The Maven JAR Plugin's `test-jar` goal packages the compiled test/framework classes into the `-tests.jar` artifact. The test JAR is **not a standalone executable** and does not bundle external dependencies; Maven manages those libraries and runs the tests.

Inspect the packaged classes:

```bash
jar tf target/demoqa-automation-framework-1.0.0-tests.jar
```

The package should contain classes such as `tests/api/BookStoreApiTests.class`.

### Install the packaged artifacts locally

```bash
mvn clean install -DskipTests
```

This runs the Maven lifecycle through `install`, placing the versioned JAR artifacts into the local Maven repository (`~/.m2/repository`). External tests remain skipped, but test compilation still takes place.

### Run tests

The test framework is run **with Maven**, not by executing the JAR with `java -jar`.

Run the configured smoke suite (UI and API):

```bash
mvn clean test
```

Run the smoke suite with a headless browser:

```bash
mvn test -Dheadless=true
```

Run the regression suite:

```bash
mvn test -Dsurefire.suiteXmlFiles=testng-regression.xml
```

Run a specific test class or method:

```bash
mvn test -Dtest=CheckBoxTests
mvn test -Dtest=CheckBoxTests#homeSelectionDisplaysResult
```

The last command requires that the named method exist in the current test source. Command-line test selection can differ from executing the complete TestNG XML suite.

> **Important:** UI/API integration tests contact the public DemoQA service. They can fail due to site changes, browser incompatibility, network availability, or rate limits even when Maven compilation and packaging work correctly. Run tests responsibly and avoid stress/load testing a public demonstration service.

## Maven Build Automation (TFSD Course 4)

Maven's lifecycle and the project's `pom.xml` automate four core build responsibilities:

| Requirement | Maven configuration or command | What it does |
| --- | --- | --- |
| Dependency management | `<dependencies>` and `mvn dependency:tree` | Resolves Selenium, TestNG, REST Assured, Jackson, and transitive dependencies from Maven repositories |
| Compilation | `maven-compiler-plugin`, Java release `17` | Compiles Java test sources during `test-compile` |
| Version management | `<version>1.0.0</version>` and dependency version properties | Centralizes the project and dependency versions and includes the project version in output artifact names |
| Packaging | `maven-jar-plugin` with `test-jar` bound to `package` | Produces the classified test JAR containing compiled automation classes |
| Reproducible commands | `mvn clean package -DskipTests`, `mvn clean install -DskipTests` | Runs documented build lifecycles using the checked-in `pom.xml` |

### Dependency management

The project specifies direct dependencies in `pom.xml`, with centrally managed versions:

| Dependency | Version | Scope | Purpose |
| --- | --- | --- | --- |
| `org.seleniumhq.selenium:selenium-java` | `4.52.0` | `test` | Selenium browser interactions |
| `org.testng:testng` | `7.11.0` | `test` | Test runner and assertions |
| `io.rest-assured:rest-assured` | `6.3.0` | `test` | HTTP/API tests |
| `com.fasterxml.jackson.core:jackson-databind` | `2.18.3` | `test` | JSON data support |

All four dependencies use `test` scope because they are used by source files under `src/test/java`. Maven also resolves transitive dependencies. Review the resolved dependency tree with:

```bash
mvn dependency:tree
```

### Build configuration and project metadata

The `pom.xml` identifies the artifact via:

```text
groupId:    dev.luqman.qa
artifactId: demoqa-automation-framework
version:    1.0.0
packaging:  jar
```

It also includes a project name, description, author/developer, and GitHub source-control URL. Dependency versions are centralized using properties such as `selenium.version` and `testng.version`. Versions should be changed deliberately and committed to version control. No license is asserted here unless a matching license file is added to the repository.

The build uses `maven-compiler-plugin`, `maven-surefire-plugin`, and `maven-jar-plugin`. The default Maven profile defines the DemoQA URL, Chrome as the browser, and non-headless execution; these defaults can be overridden with `-D` command-line options.

### Reproducing the build on another machine

```bash
git clone https://github.com/lucka605/demoqa-automation-framework.git
cd demoqa-automation-framework
mvn clean package -DskipTests
```

This is a straightforward source build, provided the machine meets the JDK/Maven requirements and can download dependencies. Versions for direct dependencies and selected plugins are pinned in `pom.xml`; Maven may still resolve transitive dependencies and plugins from configured repositories.

## Reports and Troubleshooting

After tests run, examine TestNG/Maven reports under:

```text
target/surefire-reports/
```

The failure listener may save screenshots under:

```text
target/screenshots/
```

A practical debugging workflow is to inspect a failing assertion or stack trace, locate the related Page Object/API response, check current DOM locators if needed, rerun the affected test, and then rerun the full suite. Screenshot capture depends on the browser session being available when the failure occurs.

## CI/CD

The repository contains [a manually triggered GitHub Actions workflow](.github/workflows/manual-tests.yml) supporting smoke and regression selections. The workflow configuration exists, but **a successful hosted run has not yet been independently verified**. Automated pull-request validation is a future improvement.

## Roadmap

- [x] Implement Java UI and API test framework
- [x] Configure TestNG smoke tests
- [x] Run the local smoke suite successfully (9/9 on November 5, 2026)
- [x] Configure Maven packaging of compiled test classes
- [ ] Add more positive, negative, and boundary-value tests
- [ ] Improve assertions and test isolation
- [ ] Verify a successful GitHub Actions run
- [ ] Add automatic pull-request checks, linting, and generated Javadoc in later work

## Responsible Testing

[DemoQA](https://demoqa.com/) is a public demonstration website. Keep automated test volume low; do not run load or stress tests against public services without explicit authorization.

## Author

**Luqman Deeb**  
Software Quality Assurance / Test Automation

---

*This repository is a hands-on project for framework design, quality engineering, build automation, and learning.*
