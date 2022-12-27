package service;

import model.Product;

public class ProductCreator {

    public static final String TESTDATA_PRODUCT_NAME = "testdata.product.name";

    public static final String TESTDATA_PRODUCT_URL = "testdata.product.url";

    public static final String TESTDATA_PRODUCT_PRICE = "testdata.product.price";

    public static Product withCredentialsFromProperty(){
        return new Product(TestDataReader.getTestData(TESTDATA_PRODUCT_NAME),
                TestDataReader.getTestData(TESTDATA_PRODUCT_URL), Double.parseDouble(TestDataReader.getTestData(TESTDATA_PRODUCT_PRICE)));
    }

    public static Product withEmptyUsername() {
        return new Product("", TestDataReader.getTestData(TESTDATA_PRODUCT_URL), Double.parseDouble(TestDataReader.getTestData(TESTDATA_PRODUCT_PRICE)));
    }

    public static Product withEmptyPassword() {
        return new Product(TestDataReader.getTestData(TESTDATA_PRODUCT_NAME), "",Double.parseDouble(TestDataReader.getTestData(TESTDATA_PRODUCT_PRICE)));
    }
}
