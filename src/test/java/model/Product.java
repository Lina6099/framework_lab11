package model;

public class Product {

    private String productName;

    private String productURL;

    private double productPrice;

    public Product(String productName, String productURL, double productPrice){
        this.productName = productName;
        this.productURL = productURL;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductURL() {
        return productURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

}
