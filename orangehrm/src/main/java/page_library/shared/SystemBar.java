package page_library.shared;

import page_library.LoginPage;
import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SystemBar extends BasePage {

    @FindBy (xpath = "//span[@class='oxd-topbar-header-breadcrumb']")
    public WebElement breadCrumbText;

    @FindBy (xpath = "//li[@class='oxd-userdropdown']")
    public WebElement userDropdown;

    @FindBy (xpath = "//ul[@class='oxd-dropdown-menu']//a[contains(text(), 'Logout')]")
    public WebElement buttonLogout;

    public SystemBar() {
        PageFactory.initElements(driver, this);
    }

    public void clickUserDropdown() {
        clickElement(userDropdown);
    }

    public void clickLogoutButton() {
        clickElement(buttonLogout);
    }

    public LoginPage logout() {
        clickUserDropdown();
        clickLogoutButton();

        return new LoginPage();
    }
}
