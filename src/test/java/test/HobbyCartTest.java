package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import page.HobbyCartPage;
import page.HobbyItemPage;
import service.ProductCreator;
import service.TestDataReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HobbyCartTest extends CommonConditions {

    private final Logger logger = LogManager.getRootLogger();

    private final String emptyCartMessage = TestDataReader.getTestData("testdata.emptycart.text");

    private final int expectedCountOfProductsInCart = Integer
            .parseInt(TestDataReader.getTestData("testdata.count.product"));

    @Test
    public void addProductInCart() {
        new HobbyItemPage(driver)
                .openPage()
                .addProductToCart()
                .openCartPopupWindow()
                .goToCartPage();
        List<String> actualProductsInCart = new HobbyCartPage(driver)
                .getProductsNameInCart();
        assertThat(actualProductsInCart)
                .allMatch(i -> i.equals(ProductCreator.withCredentialsFromProperty().getProductName()))
                .as("only one, current product in cart")
                .hasSize(expectedCountOfProductsInCart);
    }

    @Test
    public void addTwoProductToCart() {
        int expectedCount = 2;
        logger.info("expected count  = " + expectedCount);
        new HobbyItemPage(driver)
                .openPage()
                .addProductToCart()
                .openCartPopupWindow()
                .increaseCountOfProductInCart()
                .goToCartPage();
        int actualCountOfProduct = new HobbyCartPage(driver)
                .getCurrentProductSizeInCart();
        assertThat(actualCountOfProduct)
                .isEqualTo(expectedCount);
    }

    @Test
    public void deleteItemFromCart() {
        new HobbyItemPage(driver)
                .openPage()
                .addProductToCart()
                .openCartPopupWindow()
                .goToCartPage();
        String actualMessage = new HobbyCartPage(driver)
                .deleteAllProductsInCart()
                .getEmptyCartMessage();
        logger.info("actual message = " + actualMessage);
        logger.info("expected message = " + emptyCartMessage);
        assertThat(actualMessage)
                .contains(emptyCartMessage);
    }
}
