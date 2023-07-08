import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

public class TestDatePicker extends BasePage {

    @Test
    public void testFromDatePicker() throws InterruptedException {
        Homepage homepage = new Homepage();

        clickElement(homepage.buttonFromDate);
        driverWait.until(ExpectedConditions.visibilityOf(homepage.tableFromDatePickerMonthLeft));

        String monthYear = "072023";
        homepage.navigateDatePickerTo(monthYear);

        String whichTable = homepage.determineWhichMonthTable(monthYear);

        String departureDate = "24";
        clickElement(homepage.getMonthDayButtonFromTable(whichTable, departureDate));

        monthYear = "092023";
        homepage.navigateDatePickerTo(monthYear);
        whichTable = homepage.determineWhichMonthTable(monthYear);

        String returnDate = "4";
        clickElement(homepage.getMonthDayButtonFromTable(whichTable, returnDate));

        Thread.sleep(5000);

    }

}
