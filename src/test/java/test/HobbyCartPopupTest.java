package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import page.HobbyItemPage;
import service.ProductCreator;
import service.TestDataReader;

import static org.assertj.core.api.Assertions.assertThat;

public class HobbyCartPopupTest extends CommonConditions{

    private final Logger logger = LogManager.getRootLogger();

    private final int expectedCountOfProductsInCart = Integer
            .parseInt(TestDataReader.getTestData("testdata.count.product"));

    @Test
    public void checkProductInModuleCartWindow() {
        logger.info("checkProductInModuleCartWindow test");
        String productName = new HobbyItemPage(driver)
                .openPage()
                .addProductToCart()
                .openCartPopupWindow()
                .getProductNameFromPopupCartWindow();
        double productPrice = new HobbyItemPage(driver)
                .getProductPriceFromPopupCartWindow();
        int productCount = new HobbyItemPage(driver)
                .getProductCountInPopupWindow();
        assertThat(productName)
                .as("Similar Product Name")
                .isEqualTo(ProductCreator.withCredentialsFromProperty().getProductName());
        assertThat(productPrice)
                .as("Similar Product Price")
                .isEqualTo(ProductCreator.withCredentialsFromProperty().getProductPrice());
        assertThat(productCount)
                .as("only our product in cart")
                .isEqualTo(expectedCountOfProductsInCart);
    }
}
