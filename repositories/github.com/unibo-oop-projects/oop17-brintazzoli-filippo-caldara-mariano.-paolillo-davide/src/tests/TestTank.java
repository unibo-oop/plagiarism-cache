package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

import model.factory.FactoryTank;
import model.input.Input;
import model.input.InputImpl;
import model.object.Tank;
import model.utility.Direction;
import model.utility.Pair;

/**
 * JUnit Test for the Tanks.
 */
public class TestTank {

    private static final double INITIAL_PLAYER_POSITION = 20.0;
    private static final double INITIAL_ENEMY_POSITION = 300.0;
    private static final int ENEMY_UPDATE_POSITION = 103;

    /**
     * Tests the fields of a new tank where it's possible.
     */
    @Test
    public void testInitialStateTank() {
        final Tank playerTank = FactoryTank.createPlayer(new Pair<>(0.0, 0.0), 3);
        final Tank enemyTank = FactoryTank.createEnemy(new Pair<>(100.0, 100.0), 3, 3, 4);
        assertNotNull(playerTank);
        assertNotNull(enemyTank);
        assertEquals(playerTank.getLifes(), 3);
        assertEquals(enemyTank.getLifes(), 3);
        assertEquals(playerTank.getPosition().getFirst().intValue(), 0);
        assertEquals(playerTank.getPosition().getSecond().intValue(), 0);
        assertEquals(enemyTank.getPosition().getFirst().intValue(), 100);
        assertEquals(enemyTank.getPosition().getSecond().intValue(), 100);
    }

    /**
     * Tests the position of the two tanks.
     */
    @Test
    public void testTankPosition() {
        final Tank playerTank = FactoryTank.createPlayer(new Pair<>(0.0, 0.0), 3);
        final Tank enemyTank = FactoryTank.createEnemy(new Pair<>(100.0, 100.0), 3, 3, 4);
        playerTank.setPosition(new Pair<>(INITIAL_PLAYER_POSITION, INITIAL_PLAYER_POSITION));
        enemyTank.setPosition(new Pair<>(INITIAL_ENEMY_POSITION, INITIAL_ENEMY_POSITION));
        assertEquals(playerTank.getPosition().getFirst().intValue(), (int) INITIAL_PLAYER_POSITION);
        assertEquals(playerTank.getPosition().getSecond().intValue(), (int) INITIAL_PLAYER_POSITION);
        assertEquals(enemyTank.getPosition().getFirst().intValue(), (int) INITIAL_ENEMY_POSITION);
        assertEquals(enemyTank.getPosition().getSecond().intValue(), (int) INITIAL_ENEMY_POSITION);
    }

    /**
     * Tests if the two tanks is alive.
     */
    @Test
    public void testTankDead() {
        final Tank playerTank = FactoryTank.createPlayer(new Pair<>(0.0, 0.0), 3);
        final Tank enemyTank = FactoryTank.createEnemy(new Pair<>(100.0, 100.0), 3, 3, 4);
        assertTrue(playerTank.isAlive());
        assertTrue(enemyTank.isAlive());
        playerTank.damage(3);
        enemyTank.damage(3);
        assertFalse(playerTank.isAlive());
        assertFalse(enemyTank.isAlive());
    }

    /**
     * Tests if the tank is correctly moving.
     */
    @Test
    public void testTankMovement() {
        final Tank playerTank = FactoryTank.createPlayer(new Pair<>(0.0, 0.0), 3);
        final Tank enemyTank = FactoryTank.createEnemy(new Pair<>(100.0, 100.0), 3, 3, 4);
        final Input playerInput = new InputImpl();
        final Input enemyInput = new InputImpl();
        playerInput.getMovement().put(Direction.DOWN, true);
        enemyInput.getMovement().put(Direction.RIGHT, true);
        playerTank.update(playerInput);
        enemyTank.update(enemyInput);
        assertEquals(playerTank.getPosition().getSecond().intValue(), 3);
        assertEquals(enemyTank.getPosition().getFirst().intValue(), ENEMY_UPDATE_POSITION);
    }
}
