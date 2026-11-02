# Amazon UI + Petstore API BDD Automation

## Stack
Java 17, Maven, Selenium WebDriver, Cucumber BDD, TestNG, POM and Rest Assured.

## Prerequisites
- JDK 17 (or change both Java release values in pom.xml to match your installed JDK)
- Maven installed and available on PATH
- Google Chrome installed
- Internet access

## Run
Open VS Code terminal in the project root (the folder containing pom.xml).

Run all tests:
```bash
mvn clean test
```

Only UI:
```bash
mvn clean test -Dcucumber.filter.tags="@ui"
```

Only API:
```bash
mvn clean test -Dcucumber.filter.tags="@api"
```

## Output
- Screenshots: `verification/`
- Cucumber report: `target/cucumber-report.html`

## Notes
Amazon is a live website. Its navigation links, layout, and anti-bot behavior can change. If the Mobiles XPath does not match your session, inspect the page and update the locator in AmazonHomePage.java. Full-page screenshots use Chrome DevTools Protocol. Petstore is a shared public demo API and may occasionally be unavailable or inconsistent.
