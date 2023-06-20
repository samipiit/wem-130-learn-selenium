package testFormInput;

import base.BasePage;
import org.testng.annotations.Test;
import page_library.FormPage;
import utils.GenerateData;

public class TestForm extends BasePage {

    @Test
    public void testCompleteForm() {
        FormPage formPage = new FormPage();

        String firstName = GenerateData.firstName();
        String lastName = GenerateData.lastName();
        String email = GenerateData.email();
        String mobileNumber = GenerateData.mobilePhone();
        String[] dob = GenerateData.dateOfBirth("yyyyMMdd");
        String address = GenerateData.streetAddress();

        StringBuilder sb = new StringBuilder();
        for (String s : dob) {
            sb.append(s).append(" ");
        }

        formPage.fillForm(firstName, lastName, email, mobileNumber, sb.toString(), address);
    }

}
