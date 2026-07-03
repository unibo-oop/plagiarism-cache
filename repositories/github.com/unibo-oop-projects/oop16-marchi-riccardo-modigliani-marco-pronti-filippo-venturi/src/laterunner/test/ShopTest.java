package laterunner.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import laterunner.model.shop.Shop;
import laterunner.model.user.User;

/**
 * Shop tester.
 *
 */
public class ShopTest {
    private static Shop shop = Shop.getShop();
    private static User user = User.getUser();

    /**
     * Test the correct function of the class Shop.
     * 
     */
    @Test
    public void testInitialFields() {
        user.reset();
        shop.buyLife();
        assertEquals(shop.getLifeCost(), 1500);
        user.setMoney(user.getMoney() + 5000);
        shop.buyLife();
        assertEquals(shop.getLifeCost(), 2250);
        shop.buySpeed();
        assertEquals(shop.getSpeedCost(), 6000);
        user.reset();
        shop.reset();
        assertEquals(shop.getLifeCost(), 1000);
        assertEquals(shop.getSpeedCost(), 3000);
        user.setMoney(user.getMoney() + 21000);
        shop.buySpeed();
        shop.buySpeed();
        assertEquals(shop.getSpeedCost(), 12000);
        user.reset();
        shop.reset();
        shop.buyLife();
        shop.buyLife();
        assertEquals(User.getUser().getUserLives(), 4);
        user.reset();
        shop.reset();
        shop.buySpeed();
        assertTrue(User.getUser().getSpeedMul() == 1.0);
        user.reset();
        user.setMoney(user.getMoney() + 21000);
        shop.reset();
        shop.buySpeed();
        shop.buySpeed();
        shop.buySpeed();
        assertTrue(User.getUser().getSpeedMul() == 2.0);
    }
 }
