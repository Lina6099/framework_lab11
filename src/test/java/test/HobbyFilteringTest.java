package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import page.HobbyHomePage;
import service.TestDataReader;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class HobbyFilteringTest extends CommonConditions{

    private final Logger logger = LogManager.getRootLogger();

    public final String ascendingFilterPageLink = TestDataReader.getTestData("testdata.ascending.price.link");

    public final String descendingFilterPageLink = TestDataReader.getTestData("testdata.descending.price.link");

    @Test
    public void filterByAscendingPrice(){
        logger.info("filterByAscendingPrice test");
        driver.get(ascendingFilterPageLink);
        List<Double> pricesFromPage = new HobbyHomePage(driver)
                .getPriceList();
        assertThat(pricesFromPage)
                .isSorted();
    }

    @Test
    public void filterByDescendingPrice(){
        logger.info("filterByDescendingPrice test");
        driver.get(descendingFilterPageLink);
        List<Double> pricesFromPage = new HobbyHomePage(driver)
                .getPriceList();
        assertThat(pricesFromPage)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }
}
