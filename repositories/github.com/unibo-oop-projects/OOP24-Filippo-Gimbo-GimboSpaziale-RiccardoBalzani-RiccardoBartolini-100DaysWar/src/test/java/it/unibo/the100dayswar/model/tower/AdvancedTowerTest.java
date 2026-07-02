package it.unibo.the100dayswar.model.tower;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.tower.api.TowerType;
import it.unibo.the100dayswar.model.tower.impl.TowerFactoryImpl;

/**
 * Test suite for AdvancedTowerImpl.
 */
class AdvancedTowerTest {
    private final Cell mockCell = new CellImpl(new PositionImpl(0, 0), true, true);
    private final Player mockPlayer = new HumanPlayerImpl("MockPlayer", mockCell);
    private final Tower mockAdvancedTower = new TowerFactoryImpl().buildTower(mockPlayer, TowerType.ADVANCED, mockCell);

    /**
     * Tests the initial properties of an AdvancedTower.
     */
    @Test
    void testAdvancedTowerProperties() {
        final int currentHealth = 200;
        final int currentDamage = 37;

        assertEquals(TowerType.ADVANCED, mockAdvancedTower.getTowerType(), "Tower type should be ADVANCED");
        assertEquals(currentHealth, mockAdvancedTower.currentHealth(), "Advanced tower health should match formula");
        assertEquals(currentDamage, mockAdvancedTower.getDamage(), "Advanced tower damage should match formula");
    }

    /**
     * Tests the upgrade functionality of an AdvancedTower.
     */
    @Test
    void testUpgradeAdvancedTower() {
        final int newLevel = 2;
        final int newHealth = 400;
        final int newDamage = 74;

        mockAdvancedTower.upgrade();
        assertEquals(newLevel, mockAdvancedTower.getLevel(), "Tower level should increase after upgrade");
        assertEquals(newHealth, mockAdvancedTower.currentHealth(), "Health should be updated after upgrade");
        assertEquals(newDamage, mockAdvancedTower.getDamage(), "Damage should be updated after upgrade");
    }

    /**
     * Tests that a tower cannot exceed the maximum level.
     */
    @Test
    void testMaxLevelUpgrade() {
        final int maxLevel = 3;

        for (int i = 1; i <= maxLevel; i++) {
            mockAdvancedTower.upgrade();
        }
        assertEquals(maxLevel, mockAdvancedTower.getLevel(), "Tower should not exceed MAX_LEVEL");
    }
}
