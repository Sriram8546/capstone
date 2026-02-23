package com.example.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.config.Config;

/**
 * Base Page Object: common actions and waits.
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final String baseUrl;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.baseUrl = Config.BASE_URL;
    }

    // open any page
    protected void open(String path) {
        driver.get(baseUrl + path);
    }

    // 🔥 default wait 10 sec
    protected WebElement waitFor(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // 🔥 custom wait if needed
    protected WebElement waitFor(By locator, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected List<WebElement> waitForAll(By locator, long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean hasClass(WebElement el, String className) {
        return el.getAttribute("class") != null &&
               el.getAttribute("class").contains(className);
    }
}
// package com.example.pages;

// import com.example.config.Config;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;

// import java.time.Duration;
// import java.util.List;

// /**
//  * Base Page Object: common actions and waits.
//  */
// public abstract class BasePage {
//     protected final WebDriver driver;
//     protected final String baseUrl;

//     public BasePage(WebDriver driver) {
//         this.driver = driver;
//         this.baseUrl = Config.BASE_URL;
//     }

//     protected void open(String path) {
//         driver.get(baseUrl + path);
//     }

//     protected WebElement waitFor(By locator, long seconds = 10) {
//         return new WebDriverWait(driver, Duration.ofSeconds(seconds))
//                 .until(ExpectedConditions.visibilityOfElementLocated(locator));
//     }

//     protected List<WebElement> waitForAll(By locator, long seconds) {
//         return new WebDriverWait(driver, Duration.ofSeconds(seconds))
//                 .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
//     }

//     protected boolean isDisplayed(By locator) {
//         try {
//             return driver.findElement(locator).isDisplayed();
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     protected boolean hasClass(WebElement el, String className) {
//         return el.getAttribute("class") != null && el.getAttribute("class").contains(className);
//     }
// }
