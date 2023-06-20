package page_library;

import page_library.shared.SharedComponents;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends SharedComponents {

    public AdminPage() {
        PageFactory.initElements(driver, this);
    }

}
