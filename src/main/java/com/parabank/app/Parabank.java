package com.parabank.app;

import com.parabank.enums.Browser;
import com.parabank.utils.ConfigReader;
import org.openqa.selenium.By;
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

public class Parabank {

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
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null) {
            driver.quit();
        }

    }

    // endregion

    // region Tests
    @Test(priority = 1, enabled = false)
    public void testNavigateToApplication(){
        // 1. Navigate to app url
        // 2. Validate the login button is displayed

        WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit']"));
        boolean isDisplayed = loginButton.isDisplayed();

        Assert.assertTrue(isDisplayed);
    }

    @Test(priority = 0)
    public void testRegisterNewAccount(){
        /*
        1. Click 'Register' button
        2. Enter First Name in 'First Name' input field
        3. Enter Last Name in 'Last Name' input field
        4. Enter Address in 'Address' input field
        5. Enter City in 'City' input field
        6. Enter State in 'State' input field
        7. Enter Zipcode in 'Zipcode' input field
        8. Enter Phone Number in 'Phone Number' input field
        9. Enter SSN in 'SSN' input field
        10. Enter desired Username in 'Username' input field
        11. Enter desired Password in 'Password' input field
        12. Enter desired Password in 'Confirm' input field
        13. Click Register button
        14. Validate Header text contains username (//h1[@class='title'])
        15. Validate Logout button exists and is displayed (//a[@href='/parabank/logout.htm'])
         */

        WebElement registerButton = driver.findElement(By.xpath("//a[contains(text(), 'Register')]"));
        registerButton.click();

        WebElement firstNameInputField = driver.findElement(By.id("customer.firstName"));
        firstNameInputField.sendKeys("Dalal");

        WebElement lastNameInputField = driver.findElement(By.id("customer.lastName"));
        lastNameInputField.sendKeys("Sadouni");

        WebElement streetAddressInputField = driver.findElement(By.id("customer.address.street"));
        streetAddressInputField.sendKeys("123 Main St");

        WebElement cityInputField = driver.findElement(By.id("customer.address.city"));
        cityInputField.sendKeys("New York");

        WebElement stateInputField = driver.findElement(By.id("customer.address.state"));
        stateInputField.sendKeys("New York");

        WebElement zipCodeInputField = driver.findElement(By.id("customer.address.zipCode"));
        zipCodeInputField.sendKeys("10001");

        WebElement phoneNumberInputField = driver.findElement(By.id("customer.phoneNumber"));
        phoneNumberInputField.sendKeys("2125555555");

        WebElement ssnInputField = driver.findElement(By.id("customer.ssn"));
        ssnInputField.sendKeys("123456789");

        String username = "DalalS12345";
        WebElement usernameInputField = driver.findElement(By.id("customer.username"));
        usernameInputField.sendKeys(username);

        String password = "password123";
        WebElement passwordInputField = driver.findElement(By.id("customer.password"));
        passwordInputField.sendKeys(password);

        WebElement passwordConfirmInputField = driver.findElement(By.id("repeatedPassword"));
        passwordConfirmInputField.sendKeys(password);

        WebElement registerSubmitButton = driver.findElement(By.xpath("//input[@value='Register']"));
        registerSubmitButton.click();

        driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@class='title']"))));

        WebElement header = driver.findElement(By.xpath("//h1[@class='title']"));
        String headerText = header.getText();

        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Log Out')]")));

        Assert.assertTrue(headerText.contains(username));
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
