import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

public class TestDatePicker extends BasePage {

    @Test
    public void testFromDatePicker() {
        Homepage homepage = new Homepage();

        clickElement(homepage.buttonFromDate);
        driverWait.until(ExpectedConditions.visibilityOf(homepage.tableFromDatePickerMonthLeft));

        List<WebElement> tableRows = homepage.tableFromDatePickerMonthLeft.findElements(By.tagName("tr"));

        List<WebElement> tableRow1FromDatePickerMonthLeft;
        List<WebElement> tableRow2FromDatePickerMonthLeft;
        List<WebElement> tableRow3FromDatePickerMonthLeft;
        List<WebElement> tableRow4FromDatePickerMonthLeft;
        List<WebElement> tableRow5FromDatePickerMonthLeft;
        List<WebElement> tableRow6FromDatePickerMonthLeft;


        tableRow1FromDatePickerMonthLeft = tableRows.get(0).findElements(By.tagName("button"));
        tableRow2FromDatePickerMonthLeft = tableRows.get(1).findElements(By.tagName("button"));
        tableRow3FromDatePickerMonthLeft = tableRows.get(2).findElements(By.tagName("button"));
        tableRow4FromDatePickerMonthLeft = tableRows.get(3).findElements(By.tagName("button"));

        try {
            tableRow5FromDatePickerMonthLeft = tableRows.get(4).findElements(By.tagName("button"));
            tableRow6FromDatePickerMonthLeft = tableRows.get(5).findElements(By.tagName("button"));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Captured all table date buttons");
        }






    }

}
