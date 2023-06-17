package com.orange_hrm.app;

import com.orange_hrm.app.shared.SharedComponents;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends SharedComponents {

    @FindBy (xpath = "//button[@class='oxd-icon-button oxd-icon-button--solid-main orangehrm-attendance-card-action']")
    public WebElement buttonAttendance;

    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }

    public TimePage clickAttendanceButton() {
        clickElement(buttonAttendance);

        return new TimePage();
    }





}
