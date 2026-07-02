package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Toolkit;

import controller.EntityFactory;
import model.entities.Position;
import utils.Pair;

/**
 * Test the Stuntman.
 */
public class StuntmanTest {

    private static final double WINDOWS_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 12.5;
    private final EntityFactory factory = new EntityFactory();
    private final Position initPos = new Position(this.factory.getStuntman().getPosition().getX(),
            this.factory.getStuntman().getPosition().getY());
    private final Pair<Double, Double> shift = new Pair<>(WINDOWS_WIDTH, this.factory.getStuntman().getHeight() / 2);

    /**
     * Test move stuntman.
     */
    @org.junit.Test
    public void testMove() {
        this.factory.getStuntman().moveUp();
        assertEquals(this.factory.getStuntman().getPosition(), initPos);
        this.factory.getStuntman().moveLeft();
        assertEquals(this.factory.getStuntman().getPosition(),
                new Position(this.initPos.getX() - this.shift.getX(), this.initPos.getY()));
    }

    /**
     * Test move down stuntman.
     */
    @org.junit.Test(expected = UnsupportedOperationException.class)
    public void testMoveDown() {
        this.factory.getStuntman().moveDown();
    }

    /**
     * Test stuntman life.
     */
    @org.junit.Test
    public void testStuntmanLife() {
        assertEquals(this.factory.getStuntman().getLife().getValue(), 3);
        assertTrue(this.factory.getStuntman().getLife().isMaximum());
        assertFalse(this.factory.getStuntman().getLife().isConsummate());
        this.factory.getStuntman().getLife().decrement();
        this.factory.getStuntman().getLife().decrement();
        this.factory.getStuntman().getLife().decrement();
        assertEquals(this.factory.getStuntman().getLife().getValue(), 0);
        assertTrue(this.factory.getStuntman().getLife().isConsummate());
        for (int i = 0; i < 10; i++) {
            this.factory.getStuntman().getLife().increment();
        }
        assertTrue(this.factory.getStuntman().getLife().isMaximum());
        assertEquals(this.factory.getStuntman().getLife().getValue(), 3);
    }

    /**
     * Test the stuntman's borders.
     */
    @org.junit.Test
    public void testBorder() {
        this.factory.getStuntman().moveUp();
        this.factory.getStuntman().moveUp();
        for (int i = 0; i < 100; i++) {
            this.factory.getStuntman().moveLeft();
        }
        final Position pos = new Position(this.factory.getStuntman().getPosition().getX(),
                this.factory.getStuntman().getPosition().getY());
        this.factory.getStuntman().moveLeft();
        assertEquals(pos, this.factory.getStuntman().getPosition());
    }
}
