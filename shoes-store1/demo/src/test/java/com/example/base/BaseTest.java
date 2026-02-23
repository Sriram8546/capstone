package com.example.base;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        ChromeOptions options = new ChromeOptions();

        // 🔥 HEADLESS MODE FOR GITHUB
        String headless = System.getProperty("headless");

        if ("true".equalsIgnoreCase(headless)) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
        }

        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // open app
        driver.get("http://localhost:3000/");
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
// package com.example.base;

// import java.time.Duration;

// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;
// import org.testng.annotations.AfterMethod;
// import org.testng.annotations.BeforeMethod;


// public abstract class BaseTest {

//     protected WebDriver driver;
//     @BeforeMethod(alwaysRun = true)
// public void setUp() {

//     ChromeOptions options = new ChromeOptions();
//     options.addArguments("--disable-notifications");

//     driver = new ChromeDriver(options);

//     driver.manage().window().maximize();
//     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    
//     driver.get("http://127.0.0.1:5500/shoes-store1/shoes-store/index.html");
// }


//     @AfterMethod(alwaysRun = true)
//     public void tearDown() {
//         if (driver != null) {
//             driver.quit();
//         }
//     }

//     protected WebDriver getDriver() {
//         return driver;
//     }
// }
