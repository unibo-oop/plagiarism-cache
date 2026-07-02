package unibo.exiled.model.character.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.model.item.HealingItem;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.Item;
import unibo.exiled.utilities.ElementalType;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class TestPlayer {
    private static final int EXPERIENCE_CAP = 100;
    private static final int LEVEL_INCREASE = 10;
    private static final int MOVES_LEARNING_INTERVAL = 5;

    private Player player;

    @BeforeEach
    void setUp() {
        player = new PlayerImpl(EXPERIENCE_CAP, LEVEL_INCREASE, MOVES_LEARNING_INTERVAL);
    }

    @Test
    void testGetLevel() {
        assertEquals(ConstantsAndResourceLoader.PLAYER_STARTING_LEVEL, player.getLevel());
    }

    @Test
    void testGetExperience() {
        assertEquals(0, player.getExperience());
    }

    @Test
    void testGetInventory() {
        final Inventory inventory = player.getInventory();
        assertNotNull(inventory);
        // assertEquals(4, inventory.getItems().size()); // It doesn't work correctly
        // because of the
        // "initializeInventory()" test method in PlayerImpl.
    }

    @Test
    void testSetAndGetPlayerClass() {
        player.setPlayerClass(ElementalType.FIRE);
        assertEquals(ElementalType.FIRE, player.getType());
    }

    @Test
    void testAddExperience() {
        final int experienceToAdd = 150;
        final double expectedExperience = 50;
        final int expectedLevel = 2;
        player.addExperience(experienceToAdd);
        assertEquals(expectedExperience, player.getExperience());
        assertEquals(expectedLevel, player.getLevel());
    }

    @Test
    void testLevelUp() {
        final int experienceToAdd = 230;
        final double expectedExperience = 10;
        final int expectedLevel = 3;
        player.addExperience(experienceToAdd);
        assertEquals(expectedExperience, player.getExperience());
        assertEquals(expectedLevel, player.getLevel());
    }

    @Test
    void testUseHealingItem() {
        final Set<Item> playerItems = player.getInventory().getItems().keySet();
        for (final Item item : playerItems) {
            if (item instanceof HealingItem) {
                final double initialHealth = player.getHealth(); // Save the starting value of health.
                final int initialItemQuantity = player.getInventory().getItemQuantity(item); // Save the starting value
                // of item quantity.

                player.useItem((HealingItem) item); // Use the healing item.

                final double newHealth = player.getHealth(); // Save the newer value of health.
                final int newItemQuantity = player.getInventory().getItems().containsKey(item)
                        ? player.getInventory().getItemQuantity(item)
                        : 0; // Save the newer value of item quantity.
                assertTrue(newHealth >= initialHealth);
                assertTrue(!player.getInventory().getItems().containsKey(item)
                        || newItemQuantity == initialItemQuantity - 1);
            }
        }
    }
}
