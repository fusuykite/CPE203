import com.sun.org.apache.xpath.internal.functions.FuncFalse;

public class View {

    private String productId;
    private int productPrice;

    public View(String product, int price) {

        productId = product;
        productPrice = price;
    }

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!getClass().equals(other.getClass())) {
            return false;
        }

        return (productId == ((View) other).productId &&
                productPrice == ((View) other).productPrice);
    }

    public String getProduct() {
        return productId;
    }

    public int getPrice() {
        return productPrice;
    }
    public String toString() {
        return String.format("ViewProduct: %s, ViewPrice: %d\n",
                productId, productPrice);
    }
}
