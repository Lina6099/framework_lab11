package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import service.ProductCreator;
import utils.Waits;

import java.util.ArrayList;
import java.util.List;

public class HobbyCartPage extends AbstractPage {

    private final String CART_URL = "https://hobbygames.by/zakaz";

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//td[contains(@class,'name item-name')]/p")
    private List<WebElement> productBlockNames;

    @FindBy(xpath = "//td/a[@class='icon icon-remove']")
    private List<WebElement> deleteProductButton;

    @FindBy(xpath = "//tr[@class='row order-totals']/td[@class='col-xs-6']")
    private WebElement informationMessage;

    private final By informationPath = By.xpath("//tr[@class='row order-totals']/td[@class='col-xs-6']");

    private final By deleteButtonPath = By.xpath("//td/a[@class='icon icon-remove']");

    private final By productBlockNamePath = By.xpath("//td[contains(@class,'name item-name')]");

    private String customXpathForCurrentProductSizePart1 = "//td[contains(@class,'name item-name')]/p[contains(.,'";

    private String customXpathForCurrentProductSizePart2 = "')]/../../td[contains(@class,'qty')]/span/span";

    public HobbyCartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductsNameInCart(){
        Waits.getWebElementUntilWait(driver, productBlockNamePath);
        List<String> productNameInCart = new ArrayList<>();
        for ( WebElement i : productBlockNames){
            productNameInCart.add(i.getText());
        }
        logger.info("Product in cart: " + productNameInCart);
        return productNameInCart;
    }

    public int getCurrentProductSizeInCart(){
        String xpathForCurrentProduct = customXpathForCurrentProductSizePart1
                + ProductCreator.withCredentialsFromProperty().getProductName()
                + customXpathForCurrentProductSizePart2;
        Waits.getWebElementUntilClickableWait(driver, By.xpath(xpathForCurrentProduct));
        logger.info(" getCurrentProductSizeInCart xpath custom " + customXpathForCurrentProductSizePart1);
        return Integer.parseInt(driver.findElement(By.xpath(xpathForCurrentProduct)).getText());
    }

    public HobbyCartPage deleteAllProductsInCart() {
        Waits.getWebElementUntilClickableWait(driver, deleteButtonPath);
        for (WebElement i : deleteProductButton){
            if (!driver.findElements(deleteButtonPath).isEmpty()) {
                Waits.getWebElementUntilClickableWait(driver, deleteButtonPath);
                i.click();
            }
        }
        return this;
    }

    public String getEmptyCartMessage() {
        Waits.getWebElementUntilClickableWait(driver, informationPath);
        logger.info("expecetd message = " + informationMessage.getText());
        return informationMessage.getText();
    }

    @Override
    public HobbyCartPage openPage(){
        driver.get(CART_URL);
        logger.info("Open cart page result: " + CART_URL);
        return this;
    }
}
