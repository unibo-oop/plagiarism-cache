package javaclimber.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.model.shop.api.ShopItem;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.api.ShopItemStat;
import it.unibo.model.shop.api.ShopItemType;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import javaclimber.TestCostants;

/**
 * Tests for {@link ShopItem} and {@link ShopItemFactory}.
 */
class ShopItemTest {

    private static final int TOTAL_SKIN = 12;
    private static final int TOTAL_TEMP_PWR = 9;
    private static final int TOTAL_PERM_PWR = 10;
    private static final int TOTAL_ITEMS = TOTAL_SKIN + TOTAL_TEMP_PWR + TOTAL_PERM_PWR;

    private final ShopItemFactory itemFactory = new ShopItemFactoryImpl();

    /**
     * Verifies the separation of lists in the factory.
     */
    @Test
    void testFactoryLists() {
        assertEquals(TOTAL_SKIN, itemFactory.getSkins().size());
        assertEquals(TOTAL_TEMP_PWR, itemFactory.getPowerUpsTemporary().size());
        assertEquals(TOTAL_PERM_PWR, itemFactory.getPowerUpsPermanent().size());

        assertEquals(TOTAL_ITEMS, itemFactory.getAllItems().size());
    }

    /**
     * Verifies a skin item.
     */
    @Test
    void testSkinItem() {
        final Optional<ShopItem> skinAstronaut = itemFactory.getItemById(TestCostants.ID_ASTRO_SKIN);
        assertTrue(skinAstronaut.isPresent());
        final ShopItem item = skinAstronaut.get();

        assertEquals(TestCostants.ID_ASTRO_SKIN, item.getId());
        assertEquals("Astronaut alien", item.getName());
        assertEquals(ShopItemType.SKIN, item.getType());
        assertEquals("From the space with x1.5 Jump", item.getDescription());
        assertEquals(TestCostants.PRICE_100, item.getPrice());
        assertEquals(TestCostants.ZERO, item.getInitialDuration());
        assertTrue(item.getStats().containsKey(ShopItemStat.JUMP_HEIGHT));
        assertEquals(TestCostants.MULTIPLIER_1_5, item.getStats().get(ShopItemStat.JUMP_HEIGHT));
    }

    /**
     * Verifies a temporary power up item.
     */
    @Test
    void testTemporaryPowerUpItem() {
        final Optional<ShopItem> coinPwr = itemFactory.getItemById(TestCostants.ID_COIN_1);
        assertTrue(coinPwr.isPresent());
        final ShopItem item = coinPwr.get();

        assertEquals(TestCostants.ID_COIN_1, item.getId());
        assertEquals("Coin Multiplier 1", item.getName());
        assertEquals(ShopItemType.TEMPORARY_UPGRADE, item.getType());
        assertEquals("Coin multiplier x2.0 for 3 matches", item.getDescription());
        assertEquals(TestCostants.PRICE_75, item.getPrice());
        assertEquals(TestCostants.DURATION_3, item.getInitialDuration());
        assertTrue(item.getStats().containsKey(ShopItemStat.COIN_MULTIPLIER));
        assertEquals(TestCostants.MULTIPLIER_2, item.getStats().get(ShopItemStat.COIN_MULTIPLIER));
    }

    /**
     * Verifies a permanent power up item.
     */
    @Test
    void testPermanentPowerUpItem() {
        final Optional<ShopItem> permSpeed = itemFactory.getItemById(TestCostants.ID_SPEED_PP_2);
        assertTrue(permSpeed.isPresent());
        final ShopItem item = permSpeed.get();

        assertEquals(TestCostants.ID_SPEED_PP_2, item.getId());
        assertEquals("Speed Boost 2", item.getName());
        assertEquals(ShopItemType.PERMANENT_UPGRADE, item.getType());
        assertEquals("Permanent Speed boost 1.4", item.getDescription());
        assertEquals(TestCostants.PRICE_50, item.getPrice());
        assertEquals(TestCostants.ZERO, item.getInitialDuration());
        assertTrue(item.getStats().containsKey(ShopItemStat.SPEED));
        assertEquals(TestCostants.MULTIPLIER_1_4, item.getStats().get(ShopItemStat.SPEED));
    }
}
