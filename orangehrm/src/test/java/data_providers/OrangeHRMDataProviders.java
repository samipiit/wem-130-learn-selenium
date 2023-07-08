package data_providers;

import org.testng.annotations.DataProvider;
import utils.ExcelData;

import java.io.File;

public class OrangeHRMDataProviders {

    String modulePath = System.getProperty("user.dir");
    String dataPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "test_data.xlsx";
    ExcelData excelData = new ExcelData(modulePath + File.separator + dataPath);

    @DataProvider(name = "testLogin")
    public Object[][] getLoginDP() {
        return excelData.readStringArrays("testLogin");
    }

}
