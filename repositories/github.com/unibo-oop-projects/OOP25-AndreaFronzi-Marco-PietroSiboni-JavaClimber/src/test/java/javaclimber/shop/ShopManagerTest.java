package javaclimber.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.api.ShopManager;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import it.unibo.model.shop.impl.ShopManagerImpl;
import javaclimber.TestCostants;

/**
 * Tests for {@link ScoreManager}.
 */
class ShopManagerTest {
    private static final int PRICE_270 = 270;
    private static final int PRICE_220 = 220;
    private final ShopItemFactory itemFactory = new ShopItemFactoryImpl();
    private final Inventory inventory = new InventoryImpl(itemFactory);
    private final ShopManager shopManager = new ShopManagerImpl(itemFactory, inventory);

    /**
     * Tests a successfull skin purchase.
     * Verifies:
     * Skin is owned in the inventory
     * Skin is automatically selected
     * ScoreManager has correct coin balance
     */
    @Test
    void testSuccessfulSkinPurchase() {
        inventory.addCoins(TestCostants.PRICE_300);
        final ShopItem skin = itemFactory.getItemById(TestCostants.ID_ASTRO_SKIN).get();
        assertTrue(shopManager.buyItem(skin));
        assertEquals(TestCostants.PRICE_200, shopManager.getCoins());
        assertTrue(shopManager.isAlreadyOwned(skin));
        assertEquals(TestCostants.ID_ASTRO_SKIN, shopManager.getInventory().getSelectedSkin());
    }

    /**
     * Tests a successfull temporary power up purchase.
     * Verifies that the item is added to the map with correct initial duration
     */
    @Test
    void testSuccessfulTemporaryPWRPurchase() {
        inventory.addCoins(TestCostants.PRICE_300);
        final ShopItem tempPwr = itemFactory.getItemById(TestCostants.ID_JUMP_PT_3).get();
        assertTrue(shopManager.buyItem(tempPwr));
        assertEquals(TestCostants.PRICE_200, shopManager.getCoins());
        final Integer duration = shopManager.getInventory().getConsumablesStatus().get(TestCostants.ID_JUMP_PT_3);
        assertNotNull(duration);
        assertEquals(TestCostants.DURATION_7, duration);
    }

    /**
     * Tests a successfull permanent power up purchase.
     * Verifies:
     * Item is owned in the inventory
     * Item is not treated as temporary consumable
     * ScoreManager has correct coin balance
     * Purchase has not effect to selected skin
     */
    @Test
    void testSuccessfulPermanentPWRPurchase() {
        inventory.addCoins(TestCostants.PRICE_300);
        final ShopItem permPwr = itemFactory.getItemById(TestCostants.ID_SPEED_PP_1).get();
        assertTrue(shopManager.buyItem(permPwr));
        assertEquals(PRICE_270, shopManager.getCoins());
        assertTrue(shopManager.isAlreadyOwned(permPwr));
        assertTrue(shopManager.getInventory().getOwnedItems().contains(TestCostants.ID_SPEED_PP_1));
        assertFalse(shopManager.getInventory().getConsumablesStatus().containsKey(TestCostants.ID_SPEED_PP_1));
    }

    /**
     * Tests the correct sequential for permanent power up.
     * Verifies that an higher level upgrade can't be purchased if the previus level
     * isn't owned
     */
    @Test
    void testSequentialPermanentUpgrade() {
        inventory.addCoins(TestCostants.PRICE_300);
        final ShopItem speedLevel1 = itemFactory.getItemById(TestCostants.ID_SPEED_PP_1).get();
        final ShopItem speedLevel2 = itemFactory.getItemById(TestCostants.ID_SPEED_PP_2).get();

        assertFalse(shopManager.canBuyItem(speedLevel2));
        assertFalse(shopManager.buyItem(speedLevel2));

        assertTrue(shopManager.buyItem(speedLevel1));

        assertTrue(shopManager.canBuyItem(speedLevel2));
        assertTrue(shopManager.buyItem(speedLevel2));
        assertEquals(PRICE_220, shopManager.getCoins());
    }

    /**
     * Tests the failure purchase for insufficient coins.
     * Verifies that no transaction occurs
     */
    @Test
    void testFailedPurchaseInsufficientCoins() {
        inventory.addCoins(TestCostants.PRICE_30);
        final ShopItem item = itemFactory.getItemById(TestCostants.ID_ASTRO_SKIN).get();
        assertFalse(shopManager.buyItem(item));
        assertEquals(TestCostants.PRICE_30, shopManager.getCoins());
        assertFalse(shopManager.isAlreadyOwned(item));
    }

    /**
     * Tests that can't be bought the same skin twice.
     * Verifies that the purchase is blocked
     */
    @Test
    void testCannotBuySkinTwice() {
        inventory.addCoins(TestCostants.PRICE_100);
        final ShopItem item = itemFactory.getItemById(TestCostants.ID_SUB_SKIN).get();
        assertTrue(shopManager.buyItem(item));

        assertFalse(shopManager.canBuyItem(item));
        assertFalse(shopManager.buyItem(item));
    }
}
