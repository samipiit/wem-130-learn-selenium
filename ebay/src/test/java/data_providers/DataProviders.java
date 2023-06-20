package data_providers;

import base.BasePage;
import org.testng.annotations.DataProvider;
import utils.ExcelData;

import java.io.File;

public class DataProviders {

    String modulePath = System.getProperty("user.dir");
    String dataPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "test_data.xlsx";
    ExcelData excelData = new ExcelData(modulePath + File.separator + dataPath);

    @DataProvider(name = "testDoSearch")
    public Object[][] getDoSearchDataProvider() {
        return excelData.readStringArrays("testDoSearch");
    }

}
