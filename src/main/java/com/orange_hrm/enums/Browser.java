package com.orange_hrm.enums;

public enum Browser {

    CHROME("chrome"),
    FIREFOX("firefox"),
    EDGE("edge"),
    SAFARI("safari");

    public final String BROWSER;

    Browser(String browser) {
        this.BROWSER = browser;
    }
}
