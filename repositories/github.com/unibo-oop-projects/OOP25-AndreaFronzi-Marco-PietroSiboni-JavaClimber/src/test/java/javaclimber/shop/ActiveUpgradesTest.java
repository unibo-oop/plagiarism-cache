package javaclimber.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.model.shop.api.ActiveUpgrades;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import javaclimber.TestCostants;

/**
 * Tests for {@link ActiveUpgradesImpl}.
 */
class ActiveUpgradesTest {

    private final ShopItemFactory factory = new ShopItemFactoryImpl();
    private final Inventory inventory = new InventoryImpl(factory);
    private final ActiveUpgrades activeUpgrades = new ActiveUpgradesImpl(inventory);

    /**
     * Verifies when the inventory is empty the multipliers start at 1.0 as default.
     */
    @Test
    void testDefaults() {
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getSpeedMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getJumpMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getCoinMultiplier());
    }

    /**
     * Verifies that equipping a Skin updates the multipliers correctly.
     */
    @Test
    void testSkinApplication() {
        final String skinId = TestCostants.ID_SUB_SKIN;
        inventory.addItem(skinId);
        inventory.equipSkin(skinId);
        activeUpgrades.updateValues();
        assertEquals(TestCostants.MULTIPLIER_1_2, activeUpgrades.getSpeedMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getJumpMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getCoinMultiplier());
    }

    /**
     * Verifies that equipping more temporary ugrades updates the multipliers
     * correctly, also working together.
     */
    @Test
    void testConsumableApplication() {
        final String itemId = TestCostants.ID_JUMP_PT_1;
        inventory.addItem(itemId);
        inventory.addConsumable(itemId, TestCostants.DURATION_3);
        activeUpgrades.updateValues();
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getSpeedMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_3, activeUpgrades.getJumpMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getCoinMultiplier());

        final String coinId = TestCostants.ID_COIN_1;
        inventory.addItem(coinId);
        inventory.addConsumable(coinId, TestCostants.DURATION_3);
        activeUpgrades.updateValues();
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getSpeedMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_3, activeUpgrades.getJumpMultiplier());
        assertEquals(TestCostants.MULTIPLIER_2, activeUpgrades.getCoinMultiplier());
    }

    /**
     * Verifies if permanent upgrades updates the multipliers correctly, also
     * working together.
     */
    @Test
    void testPermanentUpgradeApplication() {
        inventory.addItem(TestCostants.ID_SPEED_PP_1);
        activeUpgrades.updateValues();
        assertEquals(TestCostants.MULTIPLIER_1_3, activeUpgrades.getSpeedMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getJumpMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getCoinMultiplier());

        inventory.addItem(TestCostants.ID_JUMP_PP_1);
        inventory.addItem(TestCostants.ID_JUMP_PP_2);
        activeUpgrades.updateValues();
        assertEquals(TestCostants.MULTIPLIER_1_3, activeUpgrades.getSpeedMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_4, activeUpgrades.getJumpMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getCoinMultiplier());
    }

    /**
     * Verifies the max value for type logic.
     */
    @Test
    void testConflictMaxLogic() {
        inventory.addItem(TestCostants.ID_PRIMITIVE_SKIN);
        inventory.equipSkin(TestCostants.ID_PRIMITIVE_SKIN);
        inventory.addItem(TestCostants.ID_SPEED_PT_3);
        inventory.addConsumable(TestCostants.ID_SPEED_PT_3, TestCostants.DURATION_5);

        activeUpgrades.updateValues();
        assertEquals(TestCostants.MULTIPLIER_1_5, activeUpgrades.getSpeedMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getJumpMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getCoinMultiplier());
    }

    /**
     * Verifies that starts return to default when all item for that type expires or
     * are removed from the inventory.
     */
    @Test
    void testRemoveAndReset() {
        final String itemId = TestCostants.ID_SPEED_PT_1;
        inventory.addItem(itemId);
        inventory.addConsumable(itemId, TestCostants.DURATION_5);
        activeUpgrades.updateValues();
        assertEquals(TestCostants.MULTIPLIER_1_3, activeUpgrades.getSpeedMultiplier());

        inventory.toggleConsumable(itemId, factory);
        activeUpgrades.updateValues();
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getSpeedMultiplier());

        inventory.addItem(TestCostants.ID_ASTRO_SKIN);
        inventory.equipSkin(TestCostants.ID_ASTRO_SKIN);
        inventory.addItem(TestCostants.ID_SPEED_PP_1);
        activeUpgrades.updateValues();
        assertEquals(TestCostants.MULTIPLIER_1_3, activeUpgrades.getSpeedMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_5, activeUpgrades.getJumpMultiplier());
        inventory.deselectSkin();
        activeUpgrades.updateValues();
        assertEquals(TestCostants.MULTIPLIER_1_3, activeUpgrades.getSpeedMultiplier());
        assertEquals(TestCostants.MULTIPLIER_1_0, activeUpgrades.getJumpMultiplier());
    }

}
