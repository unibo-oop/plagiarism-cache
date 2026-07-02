package javaclimber.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopItemFactory;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;
import javaclimber.TestCostants;

/**
 * Tests for {@link Inventory} interface.
 */
class InventoryTest {
    private static final int UNO = 1;
    private static final int DUE = 2;
    private static final int TRE = 3;
    private static final int QUATTRO = 4;
    private Inventory inventory;

    /**
     * Initializes inventory.
     */
    @BeforeEach
    void setUp() {
        final ShopItemFactory factory = new ShopItemFactoryImpl();
        inventory = new InventoryImpl(factory);
    }

    /**
     * Verifies at the start the base skin is already present and selected.
     */
    @Test
    void testInitialization() {
        assertTrue(inventory.hasItem(TestCostants.ID_DEFAULT_SKIN));
        assertEquals(TestCostants.ID_DEFAULT_SKIN, inventory.getSelectedSkin());
    }

    /**
     * Tests item addition and get owned.
     */
    @Test
    void testAddAndHasItems() {
        inventory.addItem(TestCostants.ID_ASTRO_SKIN);
        assertTrue(inventory.hasItem(TestCostants.ID_ASTRO_SKIN));
        assertFalse(inventory.hasItem(TestCostants.ID_PRIMITIVE_SKIN));
        assertEquals(DUE, inventory.getOwnedItems().size());
    }

    /**
     * Tests equipment and deselection logic for skin item (manually and
     * automatically).
     */
    @Test
    void testSkinEquipment() {
        inventory.addItem(TestCostants.ID_ASTRO_SKIN);
        inventory.equipSkin(TestCostants.ID_ASTRO_SKIN);

        assertEquals(TestCostants.ID_ASTRO_SKIN, inventory.getSelectedSkin());

        inventory.deselectSkin();
        assertEquals(TestCostants.ID_DEFAULT_SKIN, inventory.getSelectedSkin());

        inventory.addItem(TestCostants.ID_SUB_SKIN);
        assertEquals(TestCostants.ID_SUB_SKIN, inventory.getSelectedSkin());
    }

    /**
     * Tests the temporary power up, equipment and expiration.
     */
    @Test
    void testConsumableAndDuration() {
        inventory.addConsumable(TestCostants.ID_JUMP_PT_1, TestCostants.DURATION_3);
        assertTrue(inventory.hasItem(TestCostants.ID_JUMP_PT_1));
        assertTrue(inventory.getActiveConsumables().contains(TestCostants.ID_JUMP_PT_1));

        inventory.updateConsumables();
        assertTrue(inventory.hasItem(TestCostants.ID_JUMP_PT_1));

        inventory.updateConsumables();
        inventory.updateConsumables();
        assertFalse(inventory.hasItem(TestCostants.ID_JUMP_PT_1));
        assertFalse(inventory.getActiveConsumables().contains(TestCostants.ID_JUMP_PT_1));
    }

    /**
     * Tests integrity and immutability of the map of consumables.
     */
    @Test
    void testConsumableStatus() {
        inventory.addConsumable(TestCostants.ID_JUMP_PT_1, TestCostants.DURATION_3);
        inventory.addConsumable(TestCostants.ID_SPEED_PT_1, TestCostants.DURATION_5);

        final Map<String, Integer> status = inventory.getConsumablesStatus();
        assertEquals(DUE, status.size());
        assertEquals(TestCostants.DURATION_3, status.get(TestCostants.ID_JUMP_PT_1));
        assertEquals(TestCostants.DURATION_5, status.get(TestCostants.ID_SPEED_PT_1));

        inventory.updateConsumables();
        final Map<String, Integer> updatedStatus = inventory.getConsumablesStatus();
        assertEquals(TestCostants.DURATION_3 - UNO, updatedStatus.get(TestCostants.ID_JUMP_PT_1));
        assertEquals(TestCostants.DURATION_5 - UNO, updatedStatus.get(TestCostants.ID_SPEED_PT_1));

        assertThrows(UnsupportedOperationException.class, () -> {
            status.put("hacker_item", TestCostants.PRICE_300);
        }, "The map cannot be modified from outside");
    }

    /**
     * Tests the behaviour of permanent power up that is different from temporary.
     */
    @Test
    void testPermanentPowerUp() {
        final String permUpgradeId = TestCostants.ID_SPEED_PP_1;
        inventory.addItem(permUpgradeId);
        assertEquals(UNO, inventory.getSelectedSpeedLevel());

        for (int i = 0; i < 10; i++) {
            inventory.updateConsumables();
        }
        assertTrue(inventory.hasItem(permUpgradeId), "Permanent upgrade should not be removed with updates");
        assertTrue(inventory.getOwnedItems().contains(permUpgradeId));
    }

    /**
     * Tests that only one temporary power up for type can be active at same time.
     */
    @Test
    void testExclusiveTempPowerUp() {
        inventory.addConsumable(TestCostants.ID_JUMP_PT_1, TestCostants.DURATION_3);
        inventory.addConsumable(TestCostants.ID_SPEED_PT_1, TestCostants.DURATION_5);
        inventory.addConsumable(TestCostants.ID_COIN_1, TestCostants.DURATION_5);

        final Set<String> active = inventory.getActiveConsumables();
        assertEquals(TRE, active.size());
        assertTrue(active.contains(TestCostants.ID_JUMP_PT_1));
        assertTrue(active.contains(TestCostants.ID_SPEED_PT_1));
        assertTrue(active.contains(TestCostants.ID_COIN_1));

        inventory.addConsumable(TestCostants.ID_JUMP_PT_2, TestCostants.DURATION_5);
        final Set<String> newActive = inventory.getActiveConsumables();
        assertTrue(newActive.contains(TestCostants.ID_JUMP_PT_2));
        assertFalse(newActive.contains(TestCostants.ID_JUMP_PT_1));
        assertTrue(active.contains(TestCostants.ID_SPEED_PT_1));
        assertTrue(active.contains(TestCostants.ID_COIN_1));
    }

    /**
     * Tests the wallet operations of inventory: add, spend and set total coins.
     */
    @Test
    void testWalletOperations() {
        assertEquals(TestCostants.ZERO, inventory.getTotalCoins());
        inventory.addCoins(TestCostants.PRICE_300);
        assertEquals(TestCostants.PRICE_300, inventory.getTotalCoins());
        assertTrue(inventory.spendCoins(TestCostants.PRICE_100));
        assertEquals(TestCostants.PRICE_200, inventory.getTotalCoins());
        assertFalse(inventory.spendCoins(TestCostants.PRICE_300));
        assertEquals(TestCostants.PRICE_200, inventory.getTotalCoins());
        assertFalse(inventory.spendCoins(TestCostants.MINUS_PRICE_50));
        assertEquals(TestCostants.PRICE_200, inventory.getTotalCoins());
        inventory.setTotalCoins(TestCostants.PRICE_300);
        assertEquals(TestCostants.PRICE_300, inventory.getTotalCoins());
    }

    @Test
    void testLoadSave() {
        final SaveState state = new SaveState(TestCostants.PRICE_300, TestCostants.ZERO, Set.of(TestCostants.ID_ASTRO_SKIN),
                Map.of(TestCostants.ID_SPEED_PP_1, TestCostants.ZERO), TestCostants.ID_ASTRO_SKIN, 4, 2);
        inventory.loadState(state);

        assertEquals(TestCostants.PRICE_300, inventory.getTotalCoins());
        assertTrue(inventory.hasItem(TestCostants.ID_ASTRO_SKIN));
        assertTrue(inventory.hasItem(TestCostants.ID_DEFAULT_SKIN));
        assertEquals(TestCostants.ID_ASTRO_SKIN, inventory.getSelectedSkin());
        assertTrue(inventory.getConsumablesStatus().containsKey(TestCostants.ID_SPEED_PP_1));
        assertEquals(QUATTRO, inventory.getSelectedJumpLevel());
        assertEquals(DUE, inventory.getSelectedSpeedLevel());
        assertTrue(inventory.getActiveConsumables().isEmpty());
    }

}
