package fargoal.entity.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fargoal.model.entity.player.impl.InventoryImpl;
import fargoal.model.manager.api.FloorManager;
import fargoal.model.manager.api.MatchType;
import fargoal.model.manager.impl.FloorManagerImpl;
import fargoal.model.core.GameEngine;
import fargoal.model.entity.player.api.Inventory;
import fargoal.model.entity.player.api.Player;

class TestInventory {
    private Inventory inventory;
    private final FloorManager manager = new FloorManagerImpl(new GameEngine(), MatchType.NORMAL);
    private final Player player = manager.getPlayer();
    private final Inventory playerInventory = player.getInventory();

    /**
     * Configurates the inventory before each test.
     * If this class gets extended, make sure to call {@code super.setup()}
     * to maintain the configuration.
     */
    @BeforeEach
    void init() {
        inventory = new InventoryImpl(manager);
    }

    @Test
    void testInitialInventory() {
        assertEquals(1, inventory.numberHealingPotions());
        assertEquals(0, inventory.numberBeacons());
        assertEquals(1, inventory.numberMagicSacks());
        assertEquals(0, inventory.numberEnchantedWeapons());
        assertEquals(0, inventory.numberInvisibilitySpells());
        assertEquals(1, inventory.numberTeleportSpells());
        assertEquals(0, inventory.numberShieldSpells());
        assertEquals(0, inventory.numberRegenerationSpell());
        assertEquals(0, inventory.numberDriftSpells());
        assertEquals(0, inventory.numberLightSpells());
        assertNotNull(inventory.getListOfMaps());
    }

    @Test
    void testSpellControl() {
        assertTrue(player.getInventory().areThereSpells());
        //CHECKSTYLE: MagicNumber OFF
        //10 is a random number to set the number of invisibility spells for test porpouses.
        for (int i = 0; i < 10; i++) {
            playerInventory.addInvisibilitySpell();
        }
        //CHECKSTYLE: MagicNumber ON
        playerInventory.removeInvisibilitySpell();

        //CHECKSTYLE: MagicNumber OFF
        //9 is the expected number of invisibility spells the player has after getting 10 and using one.
        assertEquals(9, playerInventory.numberInvisibilitySpells());
        //CHECKSTYLE: MagicNumber ON
    }
}
