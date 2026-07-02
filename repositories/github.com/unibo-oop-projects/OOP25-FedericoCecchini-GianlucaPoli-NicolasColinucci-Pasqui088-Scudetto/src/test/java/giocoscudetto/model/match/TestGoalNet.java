package giocoscudetto.model.match;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.impl.match.GoalNetImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.match.GoalNetImpl}.
 */
class TestGoalNet {

    private GoalNetImpl goalNet;

    /**
     * Sets up the test environment before each test method is executed.
     */
    @BeforeEach
    void setUp() {
        goalNet = new GoalNetImpl();
    }

    /**
     * Tests that the setGoalKeeperPosition method correctly sets, 
     * the position of the goalkeeper.
     */
    @Test
    void testSetGoalKeeperPosition() {
        goalNet.setGoalKeeperPosition(2);
        goalNet.setGoalKeeperPosition(5);
    }

    /**
     * Tests that the setGoalKeeperPosition method correctly sets the position of the goalkeeper.
     */
    @Test
    void testSetGoalKeeperPositionMaxPositions() {
        goalNet.setGoalKeeperPosition(1);
        goalNet.setGoalKeeperPosition(3);
        goalNet.setGoalKeeperPosition(5);
        goalNet.setGoalKeeperPosition(7);
        assertTrue(goalNet.isGoal(5));
    }

    /**
     * Tests that the isGoal method correctly identifies goals.
     */
    @Test
    void testIsGoal() {
        goalNet.setGoalKeeperPosition(3);
        goalNet.setGoalKeeperPosition(5);
        assertTrue(goalNet.isGoal(1));
    }

    /**
     * Tests that the isGoal method correctly identifies non-goals.
     */
    @Test
    void testIsNotGoal() {
        goalNet.setGoalKeeperPosition(2);
        goalNet.setGoalKeeperPosition(8);
        assertFalse(goalNet.isGoal(8));
        goalNet.setGoalKeeperPosition(2);
        goalNet.setGoalKeeperPosition(8);
        assertFalse(goalNet.isGoal(2));
        goalNet.setGoalKeeperPosition(3);
        goalNet.setGoalKeeperPosition(4);
        assertFalse(goalNet.isGoal(4));
    }

    /**
     * Tests that the goal net correctly identifies goals in multiple rounds.
     */
    @Test
    void testMultipleRounds() {
        goalNet.setGoalKeeperPosition(1);
        goalNet.setGoalKeeperPosition(5);
        assertTrue(goalNet.isGoal(3));
        goalNet.setGoalKeeperPosition(2);
        goalNet.setGoalKeeperPosition(6);
        assertFalse(goalNet.isGoal(2));
        goalNet.setGoalKeeperPosition(4);
        goalNet.setGoalKeeperPosition(8);
        assertTrue(goalNet.isGoal(7));
    }
}
