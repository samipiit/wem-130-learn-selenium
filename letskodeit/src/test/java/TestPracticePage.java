import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class TestPracticePage extends BasePage {

    @Test
    public void testDropdownElement() {
        PracticePage practicePage = new PracticePage();

        Select dropdownSelect = new Select(practicePage.dropdownCarSelect);
        dropdownSelect.selectByIndex(2);

        driverWait.until(ExpectedConditions.elementToBeSelected(By.xpath("//option[contains(normalize-space(), 'BMW')]")));
    }

}
