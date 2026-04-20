# Agoda Automation Framework

A professional test automation framework designed for Agoda.com using **Selenium Java**, **TestNG**, and **Maven**. This project follows the **Page Object Model (POM)** pattern to ensure scalability and maintainability.

---

## 🚀 Project Overview

This framework automates the end-to-end flow of searching for a hotel on Agoda, selecting dates, setting occupancy, and verifying the hotel details on the search results and details page.

### Key Features:
- **Page Object Model (POM)**: Decouples test logic from UI elements.
- **Data-Driven Testing**: Uses External JSON files (`hotels.json`) for test data.
- **Automatic Reporting**: Generates rich HTML reports using **Extent Reports** with embedded screenshots for every step.
- **Cross-Browser Support**: Configurable via Maven or TestNG XML (defaults to Chrome).
- **Parallel Execution**: Supports running tests in parallel at the method level.

---

## 🛠 Tech Stack

- **Lanuage**: Java 11
- **Automation Tool**: Selenium WebDriver 4.x
- **Test Runner**: TestNG
- **Build Tool**: Maven
- **Reporting**: Extent Reports 5
- **Utilities**: Log4j2 (Console Logging), Jackson (JSON Handling), WebDriverManager

---

## 📂 Project Structure

```text
src/
├── main/java/
│   ├── pages/         # Page Object Classes
│   └── utils/         # Reusable Utilities
├── test/java/
│   └── tests/
│       └── search/    # Search & Price Verification tests
└── test/resources/
    └── testdata/      # External Test Data

suites/                # Testng XML suites
├── SearchWorkflow.xml
└── RegressionSuite.xml
```

---

## 🏃 How to Run

### 1. Run all tests (Default)
```bash
mvn clean test
```

### 2. Run Search Workflow Suite
```bash
mvn test -DsuiteXmlFile=suites/SearchWorkflow.xml
```

---

## 📊 Test Reporting

After execution, the HTML report will be generated at the following path:
`target/ExtentReports/AgodaTestReport.html`

The report includes:
- Test execution status (Pass/Fail).
- Step-by-step logs.
- **Screenshots** captured for every action.

---

## 📝 Automation Steps
1. **Precondition**: Navigate to Agoda.com.
2. **Search**: Input hotel name from test data.
3. **Dates**: Select Check-in and Check-out dates based on offsets from today.
4. **Occupancy**: Set number of rooms, adults, and children.
5. **Action**: Click Search and navigate to results.
6. **Selection**: Click on the first hotel result.
7. **Verification**: Switch to the new tab and verify that the hotel title and price are displayed correctly.