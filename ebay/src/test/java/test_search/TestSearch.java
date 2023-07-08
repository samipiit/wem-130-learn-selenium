package test_search;

import base.BasePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import page_library.Homepage;
import page_library.SearchResultsPage;

public class TestSearch extends BasePage {

    Homepage homepage;

    @Test(groups = {"smoke", "broken"}, dataProviderClass = data_providers.DataProviders.class, dataProvider = "testDoSearch")
    public void testDoSearch(String searchTerm, String validationTerm) {
        homepage = new Homepage();

        SearchResultsPage searchResultsPage = homepage.doSearch(searchTerm);

        Assert.assertEquals(searchResultsPage.textSearchTerm.getText(), validationTerm);
    }

    @Test
    public void testDoSearchWithCategory() {
        homepage = new Homepage();

        String searchTerm = "iPad";
        SearchResultsPage searchResultsPage = homepage.doCategorySearch(Homepage.CATEGORY_COMPUTERS_TABLETS_NETWORKING, searchTerm);

        Assert.assertEquals(searchResultsPage.textSearchTerm.getText(), searchTerm);
        Assert.assertEquals(searchResultsPage.getSelectedCategoryText(), Homepage.CATEGORY_COMPUTERS_TABLETS_NETWORKING.trim());
    }

}
