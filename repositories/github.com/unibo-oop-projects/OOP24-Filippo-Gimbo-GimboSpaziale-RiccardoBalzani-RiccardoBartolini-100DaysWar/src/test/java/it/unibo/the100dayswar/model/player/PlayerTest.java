package it.unibo.the100dayswar.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.player.impl.PlayerImpl;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.soldier.impl.SoldierImpl;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.tower.impl.BasicTowerImpl;
import it.unibo.the100dayswar.model.turn.api.GameDay;
import it.unibo.the100dayswar.model.turn.impl.GameDayImpl;
import it.unibo.the100dayswar.model.unit.api.Buyable;
import it.unibo.the100dayswar.model.unit.api.Unit;

class PlayerTest {
    private static final String PLAYER_NAME = "TestPlayer";
    private Cell spawnPoint;
    private PlayerImpl player;

    @BeforeEach
    void setUp() {
        spawnPoint = new CellImpl(new PositionImpl(0, 0), true, true);
        player = new HumanPlayerImpl(PLAYER_NAME, spawnPoint);
    }

    @Test
    void testInitialization() {
        final int expectedInitialBalance = 250;
        assertEquals(PLAYER_NAME, player.getUsername(),
                "The player's name should be correctly initialized");
        assertEquals(spawnPoint, player.getSpawnPoint(),
                "The player's spawn point should match the one provided");
        assertEquals(expectedInitialBalance, player.getBankAccount().getBalance(),
                "The player's bank account balance should start at 1000");
        assertTrue(player.getUnits().isEmpty(),
                "The player's unit set should be initially empty");
    }

    @Test
    void testEarnResources() {
        final int earnedAmount = 100;
        final int expectedBalance = 350;
        player.earnResources(earnedAmount);
        assertEquals(expectedBalance, player.getBankAccount().getBalance(),
                "The bank account balance should increase by the earned amount");
    }

    @Test
    void testSpendResources() {
        final int earnedAmount = 100;
        final int spentAmount = 50;
        final int expectedBalance = 300;
        player.earnResources(earnedAmount);
        player.spendResources(spentAmount);
        assertEquals(expectedBalance, player.getBankAccount().getBalance(),
                "The bank account balance should decrease by the spent amount");
    }

    @Test
    void testSpendResourcesInsufficient() {
        final int earnedAmount = 30;
        final int spentAmount = 1050;
        player.earnResources(earnedAmount);
        assertThrows(IllegalStateException.class,
                () -> player.spendResources(spentAmount),
                "Spending more resources than available should throw an exception");
    }

    @Test
    void testAddUnit() {
        final Unit soldier = new SoldierImpl(player);
        player.addUnit(soldier);
        assertTrue(player.getUnits().contains(soldier),
                "The unit should be added to the player's unit set");
    }

    @Test
    void testRemoveUnit() {
        final Unit soldier = new SoldierImpl(player);
        player.addUnit(soldier);
        player.removeUnit(soldier);
        assertFalse(player.getUnits().contains(soldier),
                "The unit should be removed from the player's unit set");
    }

    @Test
    void testGetUnits() {
        final Unit soldier1 = new SoldierImpl(player);
        final Unit soldier2 = new SoldierImpl(player);
        player.addUnit(soldier1);
        player.addUnit(soldier2);

        final Set<Unit> units = player.getUnits();
        final int expectedUnitCount = 2;
        assertEquals(expectedUnitCount, units.size(),
                "The unit set should contain the correct number of units");
        assertTrue(units.contains(soldier1),
                "The unit set should contain the first unit");
        assertTrue(units.contains(soldier2),
                "The unit set should contain the second unit");
    }

    @Test
    void testGetSoldiers() {
        final Soldier soldier1 = new SoldierImpl(player);
        final Soldier soldier2 = new SoldierImpl(player);
        player.addUnit(soldier1);
        player.addUnit(soldier2);

        final Set<Soldier> soldiers = player.getSoldiers();
        final int expectedSoldierCount = 2;
        assertEquals(expectedSoldierCount, soldiers.size(),
                "The soldier set should contain the correct number of soldiers");
        assertTrue(soldiers.contains(soldier1),
                "The soldier set should contain the first soldier");
        assertTrue(soldiers.contains(soldier2),
                "The soldier set should contain the second soldier");
    }

    @Test
    void testGetTowers() {
        final Tower tower1 = new BasicTowerImpl(player, new CellImpl(new PositionImpl(1, 1), true, true));
        final Tower tower2 = new BasicTowerImpl(player, new CellImpl(new PositionImpl(2, 2), true, true));
        player.addUnit(tower1);
        player.addUnit(tower2);

        final Set<Tower> towers = player.getTowers();
        final int expectedTowerCount = 2;
        assertEquals(expectedTowerCount, towers.size(),
                "The tower set should contain the correct number of towers");
        assertTrue(towers.contains(tower1),
                "The tower set should contain the first tower");
        assertTrue(towers.contains(tower2),
                "The tower set should contain the second tower");
    }

    @Test
    void testBuyUnit() {
        final Unit soldier = new SoldierImpl(player);
        final int earnedAmount = 250;
        player.earnResources(earnedAmount);
        player.buyUnit(soldier);
        assertTrue(player.getUnits().contains(soldier),
                "The unit should be added to the units set after purchase");
        final int unitCost = soldier.getBuyCost();
        final int expectedBalance = 250 + earnedAmount - unitCost;
        assertEquals(expectedBalance, player.getBankAccount().getBalance(),
                "The balance should decrease by the cost of the purchased unit");
    }

    @Test
    void testUpgradeUnit() {
        final Buyable soldier = new SoldierImpl(player);
        player.addUnit((Unit) soldier);
        final int costToUpgradeToLevel2 = soldier.getUpgradeCost();
        player.upgradeUnit(soldier);
        final int expectedLevel = 2;
        final int expectedBalance = 250 - costToUpgradeToLevel2;
        assertEquals(expectedLevel, soldier.getLevel(),
                "The unit's level should increase after the upgrade");
        assertEquals(expectedBalance, player.getBankAccount().getBalance(),
                "The balance should decrease by the cost of the upgrade");
    }

    @Test
    void testUpdate() {
        final GameDay day = new GameDayImpl();
        final int initialResources = player.getBankAccount().getBalance();
        final int resourcesGenerated = day.getAmount();
        final int expectedBalance = initialResources + resourcesGenerated;
        player.update(day);
        assertEquals(expectedBalance, player.getBankAccount().getBalance(),
                "The balance should increase by the generated amount");
    }

    @Test
    void testEqualsAndHashCode() {
        final PlayerImpl anotherPlayer = new HumanPlayerImpl(PLAYER_NAME, spawnPoint);
        assertEquals(player, anotherPlayer,
                "Players with the same name and spawn point should be equal");
        assertEquals(player.hashCode(), anotherPlayer.hashCode(),
                "Equal players should have the same hash code");
    }

    @Test
    void testCopyConstructor() {
        final HumanPlayerImpl copiedPlayer = new HumanPlayerImpl((HumanPlayer) player);
        assertEquals(player, copiedPlayer,
                "The copied player should be equal to the original");

        final int earnedAmount = 100;
        copiedPlayer.earnResources(earnedAmount);
        assertNotEquals(player.getBankAccount().getBalance(), copiedPlayer.getBankAccount().getBalance(),
                "Changes to the copied player's balance should not affect the original");
    }
}
