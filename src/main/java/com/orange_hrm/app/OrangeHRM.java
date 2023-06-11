package com.orange_hrm.app;

import com.orange_hrm.enums.Browser;
import com.orange_hrm.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class OrangeHRM {

    static WebDriver driver;
    static WebDriverWait driverWait;
    static Properties properties;

    // region Hooks
    @BeforeMethod
    public void setUp(){
        // Initialize driver object
        driver = getDriver(Browser.CHROME);

        // Initialize explicit/fluent wait object
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to URL
        properties = getAppConfig();
        String url = properties.getProperty("URL");
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

    // region Tests
    @Test
    public void testNavigateToApplication(){
        // 1. Navigate to app url
        // 2. Validate the login button is displayed
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        boolean isDisplayed = loginButton.isDisplayed();

        Assert.assertTrue(isDisplayed);
    }

    @Test
    public void testLogin() {
        String username = properties.getProperty("USERNAME");
        String password = properties.getProperty("PASSWORD");

        WebElement usernameInputField = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement passwordInputField = driver.findElement(By.xpath("//input[@name='password']"));

        usernameInputField.sendKeys(username);
        passwordInputField.sendKeys(password);

        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-alert oxd-alert--error']")));
            WebElement usernameInput = driver.findElement(By.xpath("//input[@name='username']"));
            WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));

            usernameInput.sendKeys(username);
            passwordInput.sendKeys(password);

            WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
            submit.click();
        } catch (TimeoutException e) {

        }

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='oxd-userdropdown-name']")));
        WebElement usernameDropdown = driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']"));
        String fullName = "Paul Collings";

        Assert.assertTrue(usernameDropdown.getText().contains(fullName));
    }

    // endregion

    // region Helper Methods
    private WebDriver getDriver(Browser browser) {
        switch (browser.BROWSER) {
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
