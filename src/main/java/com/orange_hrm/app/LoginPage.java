package com.orange_hrm.app;

import com.orange_hrm.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private static int loginAttempts = 0;
    @FindBy (xpath = "//input[@name='username']")
    public WebElement usernameInputField;

    @FindBy (xpath = "//input[@name='password']")
    public WebElement passwordInputField;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement loginButton;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void typeUsername(String username) {
        clearTextSendKeys(usernameInputField, username);
    }

    public void typePassword(String password) {
        clearTextSendKeys(passwordInputField, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    public DashboardPage login(String username, String password) {
        typeUsername(username);
        typePassword(password);
        clickLoginButton();

        loginAttempts++;

        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-alert oxd-alert--error']")));

            if (loginAttempts < 3) {
                login(username, password);
            } else {
                throw new RuntimeException();
            }

        } catch (TimeoutException e) {

        } catch (RuntimeException e) {
            System.out.println("Unable to login - error message still available");
        }

        return new DashboardPage();
    }

}
