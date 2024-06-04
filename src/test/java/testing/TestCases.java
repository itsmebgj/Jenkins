package testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCases {

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "1,5",
            "3,5",
            "4,12.5",
            "10,12.5",
            "11,20"})
    void deliveryIsAccordingToTheNumberOfItems(int noOfItems, double expectedDeliveryPrice) {
        ShoppingCart cart = new ShoppingCart();
        for (int i = 0; i < noOfItems; i++) {
            cart.add(new Item(ItemType.OTHER, "ANY", 1, 1));
        }
        double price = new DeliveryPrice().priceToAggregate(cart);
        assertThat(price).isEqualTo(expectedDeliveryPrice);
    }

    @ParameterizedTest
    @CsvSource({"1","2"})
    void chargeTheExtraPriceIfThereIsAnyElectronicInTheCart(int numberOfElectronics){
        ShoppingCart cart = new ShoppingCart();

        for(int i = 0; i< numberOfElectronics; i++){
            cart.add(new Item(ItemType.ELECTRONIC,"ANY ELECTRONIC",1,1));
        }
        double price = new ExtraChargeForElectronics().priceToAggregate(cart);
        assertThat(price).isEqualTo(7.50);
    }

    @Test
    void noExtraChargesIfNoElectronics(){
        ShoppingCart cart = new ShoppingCart();
        cart.add(new Item(ItemType.OTHER, "BOOK", 1, 1));
        cart.add(new Item(ItemType.OTHER, "CD", 1, 1));
        cart.add(new Item(ItemType.OTHER, "BABY TOY", 1, 1));

        double price = new ExtraChargeForElectronics().priceToAggregate(cart);
        assertThat(price).isEqualTo(0);
    }

    @Test
    void callAllPriceRules(){
        PriceRule rule1 = mock(PriceRule.class);
        PriceRule rule2 = mock(PriceRule.class);
        PriceRule rule3 = mock(PriceRule.class);

        ShoppingCart cart = new ShoppingCart();
        cart.add(new Item(ItemType.OTHER,"ITEM",1,1));

        when(rule1.priceToAggregate(cart)).thenReturn(1.0);
        when(rule2.priceToAggregate(cart)).thenReturn(0.0);
        when(rule3.priceToAggregate(cart)).thenReturn(2.0);

        FinalPriceCalculator calculator = new FinalPriceCalculator(Arrays.asList(rule1, rule2, rule3));
        double price = calculator.calculate(cart);

        assertThat(price).isEqualTo(3);
    }
}
