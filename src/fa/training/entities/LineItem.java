package fa.training.entities;

public class LineItem {
    private int orderId;
    private int productId;
    private int quantity;
    private double price;

    public LineItem() {
    }

    public LineItem(int orderID, int productId, int quantity, double price) {
        this.orderId = orderID;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderID() {
        return orderId;
    }

    public void setOrderID(int orderID) {
        this.orderId = orderID;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "=====LineItem=====" +
                "\norderID: " + orderId +
                ", productId: " + productId +
                ", quantity: " + quantity +
                ", price: " + price;
    }
}