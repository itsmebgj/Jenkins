package testing;
public class DeliveryPrice implements PriceRule{
    @Override
    public double priceToAggregate(ShoppingCart cart){
        int totalItems = cart.numberOfItems();

        if (totalItems == 0)
            return 0;
        if (totalItems >= 1 && totalItems <= 3)
            return 5;
        if (totalItems >= 4 && totalItems <= 10)
            return 12.5;

        return 20.0;
    }
}
