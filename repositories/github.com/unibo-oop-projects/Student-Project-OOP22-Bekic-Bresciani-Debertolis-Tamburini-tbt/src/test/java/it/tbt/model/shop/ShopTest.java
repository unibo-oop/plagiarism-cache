package it.tbt.model.shop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import it.tbt.model.entities.items.Item;
import it.tbt.model.entities.items.Potion;
import it.tbt.model.entities.items.factories.PotionFactory;
import it.tbt.model.party.Party;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShopTest {
    private static final int DEF_CASH = 10_000;
    private static final String RND_NAME = "asdfg";
    private final Shop shop;
    private final PotionFactory pot = PotionFactory.getInstance();
    private final String rndPotName;

    ShopTest() {
        // party
        final Party party = new Party("party", 10, 10, 10, 10);
        party.addCash(DEF_CASH);
        // shop
        final Map<Item, Integer> items = new HashMap<>();
        for (final Potion potion : pot.getItems()) {
            items.put(potion, 3);
            party.addItemToInventory(potion); // add items to the party inventory
        }
        shop = new Shop(
            party,
            items,
            DEF_CASH
        );
        rndPotName = pot.getItems().toArray(new Potion[pot.getItems().size()])[0].getName();
    }

    @Test
    @Order(1)
    void testBuy() {
        assertFalse(shop.buy(rndPotName)); // false: successfull transaction
        assertTrue(shop.buy(RND_NAME)); // true: failed transaction
    }

    @Test
    @Order(2)
    void testSell() {
        assertFalse(shop.sell(rndPotName)); // false: successfull transaction
        assertTrue(shop.buy(RND_NAME)); // true: failed transaction
    }
}
