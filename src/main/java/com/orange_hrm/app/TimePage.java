package com.orange_hrm.app;

import com.orange_hrm.app.shared.SharedComponents;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TimePage extends SharedComponents {

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement buttonPunchInOut;

    public TimePage() {
        PageFactory.initElements(driver, this);
    }

    public void clickPunchInOutButton() {
        clickElement(buttonPunchInOut);
    }
}
