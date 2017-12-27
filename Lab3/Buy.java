public class Buy {

    private String productId;
    private int productPrice;
    private int productQuantity;

    public Buy(String product, int price, int quantity) {

        productId = product;
        productPrice = price;
        productQuantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("Buy: %s\n\t" +
                "Price: %d\n\t" +
                "Quantity: %d\n\t", productId, productPrice, productQuantity);
    }

    public String getProduct() {
        return productId;
    }

    public int getPrice() {
        return productPrice;
    }
}
