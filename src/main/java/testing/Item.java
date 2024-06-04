package testing;

public class Item {
    private ItemType type;
    private String name;
    private int quantity;
    private double price;

    public Item(ItemType type, String name, int quantity, double price) {
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public ItemType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
