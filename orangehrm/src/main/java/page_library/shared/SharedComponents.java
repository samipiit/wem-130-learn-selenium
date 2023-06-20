package page_library.shared;

import base.BasePage;

public class SharedComponents extends BasePage {

    public NavigationBar navigationBar;
    public SystemBar systemBar;

    public SharedComponents() {
        navigationBar = new NavigationBar();
        systemBar = new SystemBar();
    }

}
