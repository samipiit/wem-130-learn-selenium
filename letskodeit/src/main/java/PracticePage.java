import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticePage extends BasePage {

    @FindBy (id = "carselect")
    public WebElement dropdownCarSelect;

    public PracticePage() {
        PageFactory.initElements(driver, this);
    }

}
