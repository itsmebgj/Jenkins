package testing;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public ShoppingCart(List<Item> items) {
        this.items = items;
    }

    public void add(Item item) {
        items.add(item);
    }

    public int numberOfItems() {
        return items.size();
    }

    public List<Item> getItems() {
        return items;
    }
}
