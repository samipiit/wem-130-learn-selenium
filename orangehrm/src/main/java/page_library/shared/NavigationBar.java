package page_library.shared;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationBar extends BasePage {

    @FindBy (xpath = "//button[@class='oxd-icon-button oxd-main-menu-button']")
    public WebElement buttonCollapse;

    @FindBy (xpath = "//input[@placeholder='Search']")
    public WebElement inputSearch;

    @FindBy (xpath = "//ul[@class='oxd-main-menu']//a[@href='/web/index.php/admin/viewAdminModule']")
    public WebElement buttonAdministration;

    @FindBy (xpath = "//ul[@class='oxd-main-menu']//a[@href='/web/index.php/pim/viewPimModule']")
    public WebElement buttonPIM;

    @FindBy (xpath = "//ul[@class='oxd-main-menu']//a[@href='/web/index.php/leave/viewLeaveModule']")
    public WebElement buttonLeave;

    @FindBy (xpath = "//ul[@class='oxd-main-menu']//a[@href='/web/index.php/time/viewTimeModule']")
    public WebElement buttonTime;

    @FindBy (xpath = "//ul[@class='oxd-main-menu']//a[@href='/web/index.php/recruitment/viewRecruitmentModule']")
    public WebElement buttonRecruitment;

    @FindBy (xpath = "//ul[@class='oxd-main-menu']//a[@href='/web/index.php/pim/viewMyDetails']")
    public WebElement buttonMyInfo;

    @FindBy (xpath = "//ul[@class='oxd-main-menu']//a[@href='/web/index.php/performance/viewPerformanceModule']")
    public WebElement buttonPerformance;

    @FindBy (xpath = "//ul[@class='oxd-main-menu']//a[@href='/web/index.php/dashboard/index']")
    public WebElement buttonDashboard;

    @FindBy (xpath = "//ul[@class='oxd-main-menu']//a[@href='/web/index.php/directory/viewDirectory']")
    public WebElement buttonDirectory;

    @FindBy (xpath = "//ul[@class='oxd-main-menu']//a[@href='/web/index.php/maintenance/viewMaintenanceModule']")
    public WebElement buttonMaintenance;

    @FindBy (xpath = "//ul[@class='oxd-main-menu']//a[@href='/web/index.php/buzz/viewBuzz']")
    public WebElement buttonBuzz;

    public NavigationBar() {
        PageFactory.initElements(driver, this);
    }
}
