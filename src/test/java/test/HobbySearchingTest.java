package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import page.HobbyHomePage;
import service.TestDataReader;
import utils.StringUtils;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HobbySearchingTest extends CommonConditions{

    private final Logger logger = LogManager.getRootLogger();

    private final String validSearch = TestDataReader.getTestData("testdata.product.name");

    private final String invalidSearch = TestDataReader.getTestData("testdata.invalid.search.text");

    private final String invalidSearchMessage = TestDataReader.getTestData("testdata.invalid.search.message");

    @Test
    public void searchForProduct() {
        logger.info("Search test start");
        List<String> searchProductsNames = new HobbyHomePage(driver)
                .openPage()
                .inputTextForSearching(validSearch)
                .getSearchingResult();
        String[] refactorSearchName = new StringUtils().splitBySpace(validSearch);
        assertThat(searchProductsNames)
                .allMatch(it -> Arrays.stream(refactorSearchName).allMatch(i -> it.contains(i.toLowerCase())));
    }

    @Test
    public void invalidSearchTest() {
        logger.info("Invalid Search test start");
        String searchMessage = new HobbyHomePage(driver)
                .openPage()
                .inputTextForSearching(invalidSearch)
                .getEmptySearchMessage();
        logger.info("Search message: " + searchMessage);
        assertThat(searchMessage)
                .contains(invalidSearchMessage);
    }
    
}
