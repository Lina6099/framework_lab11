package page;

import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import service.ProductCreator;
import utils.StringUtils;
import utils.Waits;

import java.util.List;

public class HobbyItemPage extends AbstractPage{

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//div[contains(@class,'add-to-cart')]")
    private WebElement buttonAddToCart;

    @FindBy(xpath = "//div[@id='cart-popup']")
    private WebElement buttonCartPopup;

    @FindBy(xpath = "//div[contains(@class,'cart-popup__items__item item')]")
    private List<WebElement> productsInCartPopupWindow;

    @FindBy(xpath = "//div[contains(@class,'cart-popup__items__item__name')]")
    private WebElement productNameInPopupWindow;

    @FindBy(xpath = "//div[contains(@class,'cart-popup__items__item__price')]")
    private WebElement productPriceInPopupWindow;

    @FindBy(xpath = "//div[contains(@class,'text-right')]/a")
    private WebElement makeOrderButton;

    @FindBy(xpath = "//a[@class='icon icon-plus']")
    private WebElement increaseCountButton;

    private final By increaseCountButtonPath = By.xpath("//a[@class='icon icon-plus']");

    private final By makeOrderButtonPath = By.xpath("//div[contains(@class,'text-right')]/a");

    private final By productNameInPopupWindowPath = By.xpath("//div[contains(@class,'cart-popup__items__item__name')]");

    private final By buttonCartPopupPath = By.xpath("//div[@id='cart-popup']");

    private final By buttonAddToCartPath = By.xpath("//div[contains(@class,'add-to-cart')]");

    public HobbyItemPage(WebDriver driver) {
        super(driver);
    }

    public HobbyItemPage openCartPopupWindow() {
        Waits.getWebElementUntilClickableWait(driver, buttonCartPopupPath);
        buttonCartPopup.click();
        return this;
    }

    public HobbyItemPage goToCartPage() {
        Waits.getWebElementUntilClickableWait(driver, makeOrderButtonPath);
        makeOrderButton.click();
        return this;
    }

    public HobbyItemPage addProductToCart() {
        Waits.getWebElementUntilClickableWait(driver, buttonAddToCartPath);
        buttonAddToCart.click();
        return this;
    }

    public String getProductNameFromPopupCartWindow() {
        logger.info("get product name from popup cart window method");
        Waits.getWebElementUntilClickableWait(driver, productNameInPopupWindowPath);
        logger.info("item name: " + productNameInPopupWindow.getText());
        return productNameInPopupWindow.getText();
    }

    public double getProductPriceFromPopupCartWindow() {
        String productPrice = new StringUtils().getPriceNumbers(productPriceInPopupWindow.getText());
        logger.info("item Price: " + productPrice);
        return Double.parseDouble(productPrice);
    }

    public int getProductCountInPopupWindow() {
        return productsInCartPopupWindow.size();
    }

    public HobbyItemPage increaseCountOfProductInCart() {
        Waits.getWebElementUntilWait(driver, increaseCountButtonPath);
        increaseCountButton.click();
        return this;
    }

    @Override
    public HobbyItemPage openPage(){
        Product currentProduct = ProductCreator.withCredentialsFromProperty();
        driver.get(currentProduct.getProductURL());
        logger.info("Open item page result: " + currentProduct.getProductURL());
        return this;
    }

}
