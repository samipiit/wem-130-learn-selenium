package test_authentication;

import page_library.DashboardPage;
import page_library.LoginPage;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAuthentication extends BasePage {

    LoginPage loginPage;

    @Test
    public void testLogin() {
        loginPage = new LoginPage();

        DashboardPage dashboardPage = loginPage.login(properties.getProperty("username"), properties.getProperty("password"));

        driverWait.until(ExpectedConditions.visibilityOf(dashboardPage.systemBar.userDropdown));
        Assert.assertTrue(isElementDisplayed(dashboardPage.systemBar.userDropdown));
    }

    @Test
    public void testLogout() {
        loginPage = new LoginPage();

        DashboardPage dashboardPage = loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
        driverWait.until(ExpectedConditions.visibilityOf(dashboardPage.systemBar.userDropdown));

        loginPage = dashboardPage.systemBar.logout();

        Assert.assertTrue(isElementDisplayed(loginPage.loginButton));
    }

}
