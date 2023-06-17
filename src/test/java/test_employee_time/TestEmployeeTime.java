package test_employee_time;

import com.orange_hrm.app.DashboardPage;
import com.orange_hrm.app.LoginPage;
import com.orange_hrm.app.TimePage;
import com.orange_hrm.base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestEmployeeTime extends BasePage {

    LoginPage loginPage;

    @Test
    public void testPunchOut() {
        loginPage = new LoginPage();

        DashboardPage dashboardPage = loginPage.login(properties.getProperty("username"), properties.getProperty("password"));
        TimePage timePage = dashboardPage.clickAttendanceButton();
        timePage.clickPunchInOutButton();

        driverWait.until(ExpectedConditions.urlContains("punchIn"));

        Assert.assertTrue(isElementDisplayed(timePage.buttonPunchInOut));
    }

}
