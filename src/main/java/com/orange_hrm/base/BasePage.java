package com.orange_hrm.base;

import com.orange_hrm.enums.Browser;
import com.orange_hrm.utils.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Properties;

public class BasePage {

    public static WebDriver driver;
    public static WebDriverWait driverWait;
    public static Properties properties;

    // region Hooks
    @BeforeMethod
    public void setUp(){
        // Initialize driver object
        driver = getDriver(Browser.CHROME.BROWSER);

        // Initialize explicit/fluent wait object
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to URL
        properties = getAppConfig();
        String url = properties.getProperty("url");
        driver.get(url);

        // Delete Cookies
        driver.manage().deleteAllCookies();

        // Maximize Window
        driver.manage().window().maximize();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null) {
            driver.quit();
        }

    }

    // endregion

    // region Selenium Methods
    public void clickElement(WebElement element) {
        driverWait.until(ExpectedConditions.elementToBeClickable(element));

        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            jsClickElement(element);
        }
    }

    public void jsClickElement(WebElement element) {
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        jsDriver.executeScript("arguments[0].click();", element);
    }

    public void clearTextSendKeys(WebElement element, String text) {
        driverWait.until(ExpectedConditions.visibilityOf(element));
        element.clear();

        element.sendKeys(text);
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            driverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            return false;
        }

        return true;
    }

    // endregion

    // region Helper Methods
    private WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                driver = new FirefoxDriver(options);
            }
            case "edge" -> driver = new EdgeDriver();
            case "chrome" -> driver = new ChromeDriver();
            case "safari" -> driver = new SafariDriver();
        }
        return driver;
    }

    private Properties getAppConfig() {
        return ConfigReader.loadProperties("app_config");
    }

    // endregion

}
