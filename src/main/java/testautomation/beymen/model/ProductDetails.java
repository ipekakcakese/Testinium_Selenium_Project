package testautomation.beymen.model;

public record ProductDetails(String name, String price) {

    @Override
    public String toString() {
        return "Product: " + name + "\nPrice: " + price;
    }
}
