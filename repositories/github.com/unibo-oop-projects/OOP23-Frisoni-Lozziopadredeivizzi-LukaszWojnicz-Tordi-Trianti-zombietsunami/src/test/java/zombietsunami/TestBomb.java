package zombietsunami;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import zombietsunami.model.obstaclemodel.api.Bomb;
import zombietsunami.model.obstaclemodel.impl.BombImpl;

/**
 * Test class for TestBomb.
 * 
 * This class contains unit tests for various functionalities of the BombImpl
 * class.
 */
class TestBomb {
    private final Bomb bomb = new BombImpl();

    /**
     * Checks if the X coordinate is set properly.
     */
    @Test
    void testX() {
        bomb.setX(10);
        assertEquals(10, bomb.getX());
    }

    /**
     * Checks if the Y coordinate is set properly.
     */
    @Test
    void testY() {
        bomb.setY(10);
        assertEquals(10, bomb.getY());
    }
}
