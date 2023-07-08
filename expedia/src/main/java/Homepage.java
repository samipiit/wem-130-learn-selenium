import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage extends BasePage {

    // region From Date Picker
    @FindBy (xpath = "//button[@data-name='d1']")
    public WebElement buttonFromDate;

    @FindBy (xpath = "//div[@class='uitk-calendar']/div[contains(@class, 'layout')]/button[1]")
    public WebElement buttonFromDatePickerPreviousMonth;

    @FindBy (xpath = "//div[@class='uitk-calendar']/div[contains(@class, 'layout')]/button[2]")
    public WebElement buttonFromDatePickerNextMonth;

    @FindBy (xpath = "//div[@class='uitk-date-picker-menu-months-container']/div[1]/h2")
    public WebElement headerFromDatePickerMonthLeft;

    @FindBy (xpath = "//div[@class='uitk-date-picker-menu-months-container']/div[1]/table/tbody")
    public WebElement tableFromDatePickerMonthLeft;

    @FindBy (xpath = "//div[@class='uitk-date-picker-menu-months-container']/div[2]/h2")
    public WebElement headerFromDatePickerMonthRight;

    @FindBy (xpath = "//div[@class='uitk-date-picker-menu-months-container']/div[2]/table/tbody")
    public WebElement tableFromDatePickerMonthRight;

    // endregion

    @FindBy (id = "d2-btn")
    public WebElement buttonToDate;

    public Homepage() {
        PageFactory.initElements(driver, this);
    }


}
