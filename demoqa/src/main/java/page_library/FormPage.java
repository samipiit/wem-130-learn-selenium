package page_library;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FormPage extends BasePage {

    @FindBy (id = "firstName")
    public WebElement inputFirstName;

    @FindBy (id = "lastName")
    public WebElement inputLastName;

    @FindBy (id = "userEmail")
    public WebElement inputEmailAddress;

    @FindBy (id = "gender-radio-1")
    public WebElement radioGenderMale;

    @FindBy (id = "gender-radio-2")
    public WebElement radioGenderFemale;

    @FindBy (id = "gender-radio-3")
    public WebElement radioGenderOther;

    @FindBy (id = "userNumber")
    public WebElement inputMobilePhone;

    @FindBy (id = "dateOfBirthInput")
    public WebElement inputDOB;

    @FindBy (id = "currentAddress")
    public WebElement inputCurrentAddress;


    public FormPage() {
        PageFactory.initElements(driver, this);
    }

    public void inputFirstName(String firstName) {
        clearTextSendKeys(inputFirstName, firstName);
    }

    public void inputLastName(String lastName) {
        clearTextSendKeys(inputLastName, lastName);
    }

    public void inputEmailAddress(String email) {
        clearTextSendKeys(inputEmailAddress, email);
    }

    public void selectRandomGender() {
        List<WebElement> genderOptions = new ArrayList<>();
        genderOptions.add(radioGenderMale);
        genderOptions.add(radioGenderFemale);
        genderOptions.add(radioGenderOther);

        Random random = new Random();
        int random_int = random.nextInt(0, 2);

        WebElement option = genderOptions.get(random_int);
        jsClickElement(option);
    }

    public void inputMobilePhone(String mobilePhone) {
        clearTextSendKeys(inputMobilePhone, mobilePhone);
    }

    public void inputDateOfBirth(String dateOfBirth) {
        clearTextSendKeys(inputDOB, dateOfBirth);
    }

    public void inputCurrentAddress(String streetAddress) {
        clearTextSendKeys(inputCurrentAddress, streetAddress);
    }

    public void fillForm(String firstName, String lastName, String email, String phone, String dob, String address) {
        inputFirstName(firstName);
        inputLastName(lastName);
        inputEmailAddress(email);
        selectRandomGender();
        inputMobilePhone(phone);
        inputDateOfBirth(dob);
        inputCurrentAddress(address);
    }

}
