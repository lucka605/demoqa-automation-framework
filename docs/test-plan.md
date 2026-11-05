# Test Plan — DemoQA Automation Framework

## Objective
Verify a representative, non-destructive subset of DemoQA UI components and the public Book Store API.

## Scope
- UI: Text Box, Check Box, Radio Button, Buttons, Alerts; regression adds Web Tables and Practice Form.
- API: GET books, GET book by ISBN, invalid authentication.
- Out of scope: load/stress testing, security scanning, CAPTCHA bypass, account creation/deletion, destructive operations.

## Strategy
- Smoke suite: representative UI workflows + read-only/negative API requests.
- Regression suite: added UI scenarios.
- Single-threaded tests; do not run numerous concurrent browser sessions against the public demo.
- On failures, collect screenshot locally and Surefire XML report.

## Entry criteria
Java 17+, Maven 3.9+, Chrome installed, reachable DemoQA server, informed consent to the public site's terms.

## Exit criteria
All planned tests executed; failures triaged. Do not equate a green suite with complete product coverage.

## Risks
Public demo changes and third-party advertisements can produce flaky locators or overlays; network rate limits may affect availability.
