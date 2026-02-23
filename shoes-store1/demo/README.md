# KICKS Shoes Store – Selenium + TestNG

This project adds **Selenium** and **TestNG** automation for the shoes store, with **6 modules** and **10 test cases** each (60 tests total).

## Prerequisites

- **Java 11+**
- **Maven 3.6+**
- **Chrome** (ChromeDriver is managed by Selenium 4)

## Project structure

```
demo/
├── pom.xml
├── testng.xml
├── src/test/java/com/example/
│   ├── base/BaseTest.java
│   ├── config/Config.java
│   ├── pages/
│   │   ├── BasePage.java
│   │   ├── LoginPage.java
│   │   ├── HomePage.java
│   │   ├── ProductPage.java
│   │   ├── CartPage.java
│   │   ├── CheckoutPage.java
│   │   └── OrdersPage.java
│   ├── LoginRegistrationTests.java       (10 tests)
│   ├── ProductSearchListingTests.java    (10 tests)
│   ├── CartManagementTests.java          (10 tests)
│   ├── OrderPlacementTests.java          (10 tests)
│   ├── PaymentProcessingTests.java      (10 tests)
│   └── OrderHistoryTrackingTests.java    (10 tests)
```

## Running tests

From the **`demo`** directory (parent of `demo` must contain the **`shoes-store`** app folder):

```bash
cd demo
mvn test
```

- **Headless Chrome:**  
  `mvn test -Dheadless=true`

- **Single class:**  
  `mvn test -Dtest=LoginRegistrationTests`
- **Single method:**  
  `mvn test -Dtest=LoginRegistrationTests#loginPageLoadsWithFormVisible`

## Test modules and coverage

| Module | Class | Coverage |
|--------|--------|----------|
| 1 | `LoginRegistrationTests` | Login page load, toggle Login/Sign Up, validation, sign up, login, errors |
| 2 | `ProductSearchListingTests` | Home grid, search by name/category, no results, View Details, nav, titles |
| 3 | `CartManagementTests` | Empty cart, add to cart, summary, totals, checkout, badge, multiple items |
| 4 | `OrderPlacementTests` | Checkout load, order total, billing fields, Card/UPI tabs, Pay Now, navigation |
| 5 | `PaymentProcessingTests` | Card/UPI fields, validation, payment total, tab visibility, full fill |
| 6 | `OrderHistoryTrackingTests` | Orders page load, empty state, Start Shopping, nav, title, accessibility |

Base URL is resolved from `Config`: it points at the **`shoes-store`** folder (e.g. `file:///.../shoes-store/`) so tests run against the local static app.
