package it.unibo.modularcheckers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import it.unibo.modularcheckers.model.AIPlayer;
import it.unibo.modularcheckers.model.Player;

/**
 * Basic Player Test.
 */
public class PlayerTest {

    /*
     * private final Random randGen = new Random(); private final CoordinateFactory
     * coordFactory = new InfiniteXCoordinateFactory(); private static final int
     * MAX_MOVABLE_PIECES = 10; private static final int MAX_STEPS = 3; private
     * static final int TEST_ITERATIONS = 50;
     */

    /**
     * test Player Equals.
     */
    @Test
    public void testPlayer() {
        final Player p0 = new AIPlayer("Mario");
        final Player p1 = new AIPlayer("Susanna");
        final Player p2 = new AIPlayer("Gianna");
        final Player p3 = new AIPlayer("Mario");
        assertFalse("Testing Equals for p0 and p1.", p0.equals(p1));
        assertFalse("Testing Equals for p0 and p2.", p0.equals(p2));
        assertEquals("Testing Equals for p0 and p3.", p0, p3);
        assertFalse("Testing Equals for p1 and p2.", p1.equals(p2));
        assertFalse("Testing Equals for p1 and p3.", p1.equals(p3));
        assertFalse("Testing Equals for p1 and p3.", p2.equals(p3));
        System.out.println("testPlayer Passed! OK!");
    }

}
