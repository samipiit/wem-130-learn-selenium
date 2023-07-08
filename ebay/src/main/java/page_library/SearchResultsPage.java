package page_library;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy (xpath = "//div[@class='srp-controls__row-cells']//h1/span[2]")
    public WebElement textSearchTerm;

    @FindBy (xpath = "//span[span[contains(text(), 'Selected category')]]")
    public WebElement textSelectedCategory;

    public SearchResultsPage() {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getSearchResults() {
        return driver.findElements(By.xpath("//ul[@class='srp-results srp-list clearfix']/li[@data-viewport]"));
    }

    public String getSelectedCategoryText() {
        String text = textSelectedCategory.getText();

        String[] elementText = text.split("\n");
        return elementText[1].trim();
    }

}
