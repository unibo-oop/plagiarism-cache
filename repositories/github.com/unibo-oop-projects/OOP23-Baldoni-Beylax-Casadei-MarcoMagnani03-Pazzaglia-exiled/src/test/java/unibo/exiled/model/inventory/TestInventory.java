package unibo.exiled.model.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.factory.ItemFactory;
import unibo.exiled.model.item.factory.ItemFactoryImpl;

/**
 * Tests the inventory items.
 */
final class TestInventory {

    private static final double HEALING_DEFAULT = 5.0;
    private static final String HEALING_POTION_NAME = "Health Potion";
    private static final String HEALING_POTION_DESC = "A healing potion";

    @Test
    void testAddItem() {
        final Inventory inventory = new InventoryImpl();
        final ItemFactory factory = new ItemFactoryImpl();
        final Item crystal = factory.createUnUsableItem("Crystal", "A crystal");

        inventory.addItem(crystal);
        assertEquals(1, inventory.getItemQuantity(crystal));

        inventory.addItem(crystal);
        assertEquals(2, inventory.getItemQuantity(crystal));
    }

    @Test
    void testRemoveItem() {
        final Inventory inventory = new InventoryImpl();
        final ItemFactory factory = new ItemFactoryImpl();
        final Item potion = factory.createHealingItem(HEALING_POTION_NAME,
                HEALING_POTION_DESC, HEALING_DEFAULT);

        // Remove an item that is not in the inventory
        inventory.removeItem(potion);
        assertNull(inventory.getItemQuantity(potion));

        // Add an item and then remove it
        inventory.addItem(potion);
        inventory.removeItem(potion);
        assertNull(inventory.getItemQuantity(potion));

        // Add multiple items and remove one
        final Item maxPotion = factory.createHealingItem("Max Health Potion",
                "A max healing potion",
                HEALING_DEFAULT * 10);
        inventory.addItem(potion);
        inventory.addItem(maxPotion);
        inventory.removeItem(potion);
        assertEquals(1, inventory.getItemQuantity(maxPotion));
    }

    @Test
    void testGetItemQuantity() {
        final Inventory inventory = new InventoryImpl();
        final ItemFactory factory = new ItemFactoryImpl();
        final Item potion = factory.createHealingItem(HEALING_POTION_NAME,
                HEALING_POTION_DESC, HEALING_DEFAULT);

        assertNull(inventory.getItemQuantity(potion));

        inventory.addItem(potion);
        assertEquals(1, inventory.getItemQuantity(potion));

        inventory.addItem(potion);
        assertEquals(2, inventory.getItemQuantity(potion));
    }

    @Test
    void testContainsItem() {
        final Inventory inventory = new InventoryImpl();
        final ItemFactory factory = new ItemFactoryImpl();
        final Item potion = factory.createHealingItem(HEALING_POTION_NAME, HEALING_POTION_DESC, HEALING_DEFAULT);
        final Item crystal = factory.createUnUsableItem("Crystal", "A crystal");

        assertFalse(inventory.containsItem(potion));

        inventory.addItem(potion);
        assertTrue(inventory.containsItem(potion));

        inventory.addItem(crystal);
        assertTrue(inventory.containsItem(crystal));
    }

    @Test
    void testGetItems() {
        final Inventory inventory = new InventoryImpl();
        final ItemFactory factory = new ItemFactoryImpl();
        final Item potion = factory.createHealingItem(HEALING_POTION_NAME, HEALING_POTION_DESC, HEALING_DEFAULT);
        final Item crystal = factory.createUnUsableItem("Crystal", "A crystal");

        assertTrue(inventory.getItems().isEmpty(), "Newly created inventory should have no items");

        inventory.addItem(potion);
        inventory.addItem(crystal);

        assertEquals(2, inventory.getItems().size(), "Inventory should have 2 items");
    }
}
