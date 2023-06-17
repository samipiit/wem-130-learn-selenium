package com.orange_hrm.app;

import com.orange_hrm.app.shared.SharedComponents;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends SharedComponents {

    public AdminPage() {
        PageFactory.initElements(driver, this);
    }

}
