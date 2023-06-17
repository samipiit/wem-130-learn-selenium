package com.orange_hrm.app.shared;

import com.orange_hrm.base.BasePage;

public class SharedComponents extends BasePage {

    public NavigationBar navigationBar;
    public SystemBar systemBar;

    public SharedComponents() {
        navigationBar = new NavigationBar();
        systemBar = new SystemBar();
    }

}
