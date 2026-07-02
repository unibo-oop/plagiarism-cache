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
 * Test suite for BasicToweImpl.
 */
class BasicTowerTest {
    private final Cell mockCell = new CellImpl(new PositionImpl(0, 0), true, true);
    private final Player mockPlayer = new HumanPlayerImpl("MockPlayer", mockCell);
    private final Tower mockBasicTower = new TowerFactoryImpl().buildTower(mockPlayer, TowerType.BASIC, mockCell);

    /**
     * Tests the initial properties of a BasicTower.
     */
    @Test
    void testBasicTowerProperties() {
        final int currentHealth = 75;
        final int currentDamage = 25;

        assertEquals(TowerType.BASIC, mockBasicTower.getTowerType(), "Tower type should be BASIC");
        assertEquals(currentHealth, mockBasicTower.currentHealth(), "Basic tower health should match formula");
        assertEquals(currentDamage, mockBasicTower.getDamage(), "Basic tower damage should match formula");
    }

    /**
     * Tests the upgrade functionality of a BasicTower.
     */
    @Test
    void testUpgradeBasicTower() {
        final int newLevel = 2;
        final int newHealth = 150;
        final int newDamage = 50;

        mockBasicTower.upgrade();
        assertEquals(newLevel, mockBasicTower.getLevel(), "Tower level should increase after upgrade");
        assertEquals(newHealth, mockBasicTower.currentHealth(), "Health should be updated after upgrade");
        assertEquals(newDamage, mockBasicTower.getDamage(), "Damage should be updated after upgrade");
    }

    /**
     * Tests that a tower cannot exceed the maximum level.
     */
    @Test
    void testMaxLevelUpgrade() {
        final int maxLevel = 3;
        for (int i = 1; i <= maxLevel; i++) {
            mockBasicTower.upgrade();
        }
        assertEquals(maxLevel, mockBasicTower.getLevel(), "Tower should not exceed MAX_LEVEL");
    }
}
