package it.unibo.pacman.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unibo.pacman.model.entities.EntityFactoryImpl;
import it.unibo.pacman.model.entities.Movable;
import it.unibo.pacman.model.utilities.Difficulty;
import it.unibo.pacman.model.utilities.Direction;
import it.unibo.pacman.model.utilities.Position;

public class TestGhost {
    /**
     * Testing the effective changes of Ghost's position.
     */
    @Test
    public void testGhostPosition() {
    final Movable blinky = new EntityFactoryImpl().createBlinky(new Position(10, 10), Difficulty.NORMAL);
    assertNotNull(blinky);
    blinky.setPosition(blinky.nextPosition());
    assertTrue(blinky.getPosition().equals(new Position(10, 8)));
    blinky.setPosition(blinky.nextPosition());
    assertFalse(blinky.getPosition().equals(new Position(10, 8)));
    blinky.setPosition(blinky.nextPosition());
    assertTrue(blinky.getPosition().equals(new Position(10, 4)));
    }
    /**
     * Testing the effective ghost creation.
     */
    @Test
    public void testGhostCreation() {
        final Movable blinky = new EntityFactoryImpl().createBlinky(new Position(10, 10), Difficulty.NORMAL);
        final Movable inky = new EntityFactoryImpl().createInky(new Position(10, 10), Difficulty.NORMAL);
        final Movable pinky = new EntityFactoryImpl().createPinky(new Position(10, 10), Difficulty.NORMAL);
        final Movable clyde = new EntityFactoryImpl().createClyde(new Position(10, 10), Difficulty.NORMAL);
        assertNotNull(blinky);
        assertNotNull(inky);
        assertNotNull(pinky);
        assertNotNull(clyde);
    }
    /**
     * Testing the effective changes of Ghost's direction.
     */
    @Test
    public void testGhostDirection() {
        final Movable pinky = new EntityFactoryImpl().createPinky(new Position(10, 10), Difficulty.NORMAL);
        assertNotNull(pinky);
        assertTrue(pinky.getDirection().equals(Direction.UP));
        pinky.setDirection(Direction.getRandomDirection());
        assertFalse(pinky.getDirection().equals(Direction.UP));
    }
}
