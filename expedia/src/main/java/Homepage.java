import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Homepage extends BasePage {

    // region From Date Picker
    @FindBy (xpath = "//div[contains(@class, 'date-picker-menu')]//button[@aria-hidden='true']/preceding-sibling::button")
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

    private Locale loc = new Locale.Builder().setLanguage("en").setRegion("US").build();
    private SimpleDateFormat sdf = new SimpleDateFormat("MMyyyy", loc);

    public Homepage() {
        PageFactory.initElements(driver, this);
    }

    public void navigateDatePickerTo(String monthYear) {
        String leftMonthYear = headerFromDatePickerMonthLeft.getText();
        String[] leftMonthYearSplit = leftMonthYear.split(" ");

        String rightMonthYear = headerFromDatePickerMonthRight.getText();
        String[] rightMonthYearSplit = rightMonthYear.split(" ");

        String leftMonthNum = getMonthNumberFromMonthName(leftMonthYearSplit[0]);
        String rightMonthNum = getMonthNumberFromMonthName(rightMonthYearSplit[0]);

        try {
            Date desiredDate = sdf.parse(monthYear);
            Date leftMonthDate = sdf.parse(leftMonthNum + leftMonthYearSplit[1]);
            Date rightMonthDate = sdf.parse(rightMonthNum + rightMonthYearSplit[1]);

            int leftDateComparison = desiredDate.compareTo(leftMonthDate);
            int rightDateComparison = desiredDate.compareTo(rightMonthDate);

            if (leftDateComparison <= 0) {

            } else if (rightDateComparison > 0) {
                while (compareDesiredDateToRightMonth(desiredDate) > 0 && buttonFromDatePickerNextMonth.isEnabled()) {
                    clickElement(buttonFromDatePickerNextMonth);
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public List<WebElement> getDatePickerMonthTableRows(String whichTable) {
        if (whichTable.equalsIgnoreCase("right")) {
            return tableFromDatePickerMonthRight.findElements(By.tagName("tr"));
        }

        return tableFromDatePickerMonthLeft.findElements(By.tagName("tr"));
    }

    public String determineWhichMonthTable(String monthYear) {
        String rightMonthYear = headerFromDatePickerMonthRight.getText();
        String[] rightMonthYearSplit = rightMonthYear.split(" ");
        String rightMonthNum = getMonthNumberFromMonthName(rightMonthYearSplit[0]);

        try {
            Date desiredDate = sdf.parse(monthYear);
            Date rightMonthDate = sdf.parse(rightMonthNum + rightMonthYearSplit[1]);

            if (desiredDate.compareTo(rightMonthDate) == 0) {
                return "right";
            } else {
                return "left";
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement getMonthDayButtonFromTable(String whichTable, String day) {
        WebElement table;

        if (whichTable.equalsIgnoreCase("right")) {
            table = tableFromDatePickerMonthRight;
        } else {
            table = tableFromDatePickerMonthLeft;
        }

        return table.findElement(By.xpath("//button[not(contains(@aria-label, 'disabled'))][contains(@aria-label, '" + day + "')]"));
    }

    private int compareDesiredDateToRightMonth(Date desiredDate) {
        String rightMonthYear = headerFromDatePickerMonthRight.getText();
        String[] rightMonthYearSplit = rightMonthYear.split(" ");
        String rightMonthNum = getMonthNumberFromMonthName(rightMonthYearSplit[0]);

        try {
            Date rightMonthDate = sdf.parse(rightMonthNum + rightMonthYearSplit[1]);
            return desiredDate.compareTo(rightMonthDate);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private String getMonthNumberFromMonthName(String monthName) {
        switch (monthName.toLowerCase()) {
            case "january":
                return "01";
            case "february":
                return "02";
            case "march":
                return "03";
            case "april":
                return "04";
            case "may":
                return "05";
            case "june":
                return "06";
            case "july":
                return "07";
            case "august":
                return "08";
            case "september":
                return "09";
            case "october":
                return "10";
            case "november":
                return "11";
            case "december":
                return "12";
            default:
                LocalDate currentDate = LocalDate.now();
                Month currentMonth = currentDate.getMonth();
                return getMonthNumberFromMonthName(currentMonth.toString().toLowerCase());
        }
    }




}
