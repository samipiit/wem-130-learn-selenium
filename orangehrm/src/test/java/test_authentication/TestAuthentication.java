package test_authentication;

import org.openqa.selenium.TimeoutException;
import page_library.DashboardPage;
import page_library.LoginPage;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAuthentication extends BasePage {

    LoginPage loginPage;

    @Test (dataProviderClass = data_providers.OrangeHRMDataProviders.class, dataProvider = "testLogin")
    public void testLogin(String username, String password, String isLoggedIn) {
        loginPage = new LoginPage();

        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        loginPage.clickLoginButton();

        try {
            driverWait.until(ExpectedConditions.visibilityOf(loginPage.errorMsg));
            loginPage.typeUsername(username);
            loginPage.typePassword(password);
            loginPage.clickLoginButton();
        } catch (TimeoutException e) {
            System.out.println("Successfully logged in!");
        }

        DashboardPage dashboardPage = new DashboardPage();

        if (Boolean.parseBoolean(isLoggedIn)) {
            driverWait.until(ExpectedConditions.visibilityOf(dashboardPage.systemBar.userDropdown));
            Assert.assertTrue(isElementDisplayed(dashboardPage.systemBar.userDropdown));
        } else {
            Assert.assertFalse(isElementDisplayed(dashboardPage.systemBar.userDropdown));
        }

//        try {
//            driverWait.until(ExpectedConditions.visibilityOf(dashboardPage.systemBar.userDropdown));
//        } catch (TimeoutException e) {
//            Assert.assertTrue(isElementDisplayed(loginPage.errorMsg));
//        }
//
//        Assert.assertTrue(isElementDisplayed(dashboardPage.systemBar.userDropdown));
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
