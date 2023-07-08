package page_library;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage extends BasePage {

    @FindBy (id = "gh-ac")
    public WebElement inputSearchBar;

    @FindBy (id = "gh-btn")
    public WebElement buttonSearchSubmit;

    @FindBy (id = "gh-cat")
    public WebElement comboBoxCategory;

    public static final String CATEGORY_ANTIQUES = "Antiques";
    public static final String CATEGORY_ART = " Art";
    public static final String CATEGORY_BABY = " Baby";
    public static final String CATEGORY_BOOKS_MAGAZINES = " Books & Magazines";
    public static final String CATEGORY_BUSINESS_INDUSTRIAL = " Business & Industrial";
    public static final String CATEGORY_CAMERA_PHOTO = " Cameras & Photo";
    public static final String CATEGORY_CELL_PHONES_ACCESSORIES = " Cell Phones & Accessories";
    public static final String CATEGORY_CLOTHING_SHOES_ACCESSORIES = " Clothing, Shoes & Accessories";
    public static final String CATEGORY_COINS_PAPER_MONEY = " Coins & Paper Money";
    public static final String CATEGORY_COLLECTIBLES = " Collectibles";
    public static final String CATEGORY_COMPUTERS_TABLETS_NETWORKING = "Computers/Tablets & Networking";

    public Homepage() {
        PageFactory.initElements(driver, this);
    }

    public void inputSearchTerm(String searchTerm) {
        clearTextSendKeys(inputSearchBar, searchTerm);
    }

    public void clickSearchSubmitButton() {
        clickElement(buttonSearchSubmit);
    }

    public void selectCategory(String category) {
        selectFromDropdownByVisibleText(comboBoxCategory, category);
    }

    public SearchResultsPage doSearch(String searchTerm) {
        inputSearchTerm(searchTerm);
        clickSearchSubmitButton();

        return new SearchResultsPage();
    }

    public SearchResultsPage doCategorySearch(String category, String searchTerm) {
        selectCategory(category);
        inputSearchTerm(searchTerm);
        clickSearchSubmitButton();

        return new SearchResultsPage();
    }

}
