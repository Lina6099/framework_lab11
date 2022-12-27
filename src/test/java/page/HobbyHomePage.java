package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.StringUtils;
import utils.Waits;

import java.util.ArrayList;
import java.util.List;

public class HobbyHomePage extends AbstractPage {

    private final String HOME_URL = "https://hobbygames.by/";

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//input[@class='input']")
    private WebElement searchBox;

    @FindBy(xpath = "//div[@class='name-desc']")
    private List<WebElement> searchingResultsNames;

    @FindBy(xpath = "//div[@class='result']")
    private WebElement emptySearchMessage;

    @FindBy(xpath = "//span[@class='price']")
    private List<WebElement> pricesForProductsList;

    private final By pricesForProductsListPath = By.xpath("//span[@class='price']");

    private final By emptySearchMessagePath = By.xpath("//div[@class='result']");

    private final By searchResultPath = By.xpath("//div[@class='name-desc']");

    private final By searchBoxPath = By.xpath("//input[@class='input']");

    public HobbyHomePage(WebDriver driver) {
        super(driver);
    }

    public HobbyHomePage inputTextForSearching(String text) {
        Waits.getWebElementUntilClickableWait(driver,searchBoxPath);
        searchBox.sendKeys(text);
        searchBox.sendKeys(Keys.ENTER);
        return this;
    }

    public List<String> getSearchingResult(){
        Waits.getWebElementUntilClickableWait(driver, searchResultPath);
        List<String> productsNames = new ArrayList<>();
        for ( WebElement i: searchingResultsNames){
            productsNames.add(i.getText().toLowerCase());
        }
        logger.info("Search result: " + productsNames);
        return productsNames;
    }

    public String getEmptySearchMessage() {
        Waits.getWebElementUntilClickableWait(driver, emptySearchMessagePath);
        return emptySearchMessage.getText();
    }

    public List<Double> getPriceList(){
        List<Double> prices = new ArrayList<>();
        for(WebElement itemPrice: pricesForProductsList){
            prices.add(Double.parseDouble(new StringUtils().getPriceNumbers(itemPrice.getText())));
        }
        logger.info("get price of items: " + prices.toString());
        return prices;
    }

    @Override
    public HobbyHomePage openPage() {
        driver.get(HOME_URL);
        logger.info("Open home page result: " + HOME_URL);
        return this;
    }

}
