package base;

import org.openqa.selenium.support.ui.FluentWait;
import utils.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.ExcelData;

import java.io.File;
import java.time.Duration;
import java.util.Properties;

public class BasePage {

    public static WebDriver driver;
    public static FluentWait<WebDriver> driverWait;
    public static Properties properties;
    public ExcelData excelData;

    // region Hooks
    @BeforeMethod
    public void initExcelData() {
        String modulePath = System.getProperty("user.dir");
        String dataPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "test_data.xlsx";
        this.excelData = new ExcelData(modulePath + File.separator + dataPath);
    }

    @Parameters({"browser", "url", "driverTotalTimeout", "driverPollingInterval"})
    @BeforeMethod (alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, @Optional("https://opensource-demo.orangehrmlive.com/") String url,
                      @Optional("10") String driverTotalTimeout, @Optional("500") String driverPollingInterval){
        // Initialize driver object
        initDriver(browser, url, Long.parseLong(driverTotalTimeout), Long.parseLong(driverPollingInterval));
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        if(driver != null) {
            driver.quit();
        }

    }

    // endregion

    // region Selenium Methods
    public void clickElement(WebElement element) {

        try {
            driverWait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (TimeoutException | ElementClickInterceptedException e) {
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
    private void initDriver(String browser, String url, long driverTimeout, long driverPollingInterval) {
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

        // Initialize explicit/fluent wait object
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(driverTimeout))
                .pollingEvery(Duration.ofMillis(driverPollingInterval))
                .ignoring(NoSuchElementException.class);

        // Navigate to URL
        driver.get(url);

        // Delete Cookies
        driver.manage().deleteAllCookies();

        // Maximize Window
        driver.manage().window().maximize();
//        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
    }

    private Properties getAppConfig() {
        return ConfigReader.loadProperties("app_config");
    }

    // endregion

}
