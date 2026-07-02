package it.unibo.the100dayswar.model.fight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.fight.impl.GenericBattleCommand;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.soldier.impl.SoldierImpl;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.tower.impl.AdvancedTowerImpl;

class BattleCommandTest {
    private static final int SOLDIER_DAMAGE = 30;

    private Player playerHuman1;
    private Player playerHuman2;

    @BeforeEach
    void setUp() {
        playerHuman1 = new HumanPlayerImpl("playerName1", new CellImpl(new PositionImpl(0, 0), true, true));
        playerHuman2 = new HumanPlayerImpl("playerName1", new CellImpl(new PositionImpl(0, 0), true, true));
    }
    @Test
    void testExecuteSoldierSoldier() {
        final Soldier soldier1 = new SoldierImpl(playerHuman1);
        final Soldier soldier2 = new SoldierImpl(playerHuman2);
        final GenericBattleCommand<Soldier, Soldier> command = new GenericBattleCommand<>();
        command.execute(soldier1, soldier2);
        assertTrue(soldier1.currentHealth() == 0 || soldier2.currentHealth() == 0);
    }

    @Test
    void testExecuteSoldierTower() {
        final Soldier soldier = new SoldierImpl(playerHuman1);
        final int initialSoldierHealth = soldier.currentHealth();
        final Tower tower = new AdvancedTowerImpl(playerHuman2, new CellImpl(new PositionImpl(1, 1), true, true));
        final int towerHealth = tower.currentHealth();
        assertEquals(initialSoldierHealth, soldier.currentHealth());
        final GenericBattleCommand<Soldier, Tower> command = new GenericBattleCommand<>();
        command.execute(soldier, tower);
        assertTrue(
            soldier.currentHealth() == initialSoldierHealth 
            && tower.currentHealth() == towerHealth - SOLDIER_DAMAGE * soldier.getLevel());
    }

    @Test
    void testExecuteTowerSoldier() {
        final Soldier soldier = new SoldierImpl(playerHuman1);
        final int initialSoldierHealth = soldier.currentHealth();
        final Tower tower = new AdvancedTowerImpl(playerHuman2, new CellImpl(new PositionImpl(1, 1), true, true));
        assertEquals(initialSoldierHealth, soldier.currentHealth());
        final GenericBattleCommand<Tower, Soldier> command = new GenericBattleCommand<>();
        command.execute(tower, soldier);
        assertNotEquals(soldier.currentHealth(), initialSoldierHealth);
    }
}
