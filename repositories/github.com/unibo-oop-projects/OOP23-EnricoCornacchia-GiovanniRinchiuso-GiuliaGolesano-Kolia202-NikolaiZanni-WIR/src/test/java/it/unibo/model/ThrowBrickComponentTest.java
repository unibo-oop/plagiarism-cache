package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.common.Pair;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.ThrowBrickComponent;

/**
 * Test class for the ThrowBrickComponent.
 */
class ThrowBrickComponentTest {

    private ThrowBrickComponent throwBrickComponent;
    private GamePerformance gamePerformance;
    /**
     * Set up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        final GameController gameController = new GameController();
        gamePerformance = new GamePerformanceImpl(gameController);
        throwBrickComponent = new ThrowBrickComponent(gamePerformance);
    }
    /**
     * Test adding a brick when the component is not blocked.
     */
    @Test
    void testAddBrickWhenUnblocked() {
        final Pair<Double, Double> position = new Pair<>(300.0, 300.0);
        throwBrickComponent.addBrickToThrow(gamePerformance.getBricks(), position);
        assertFalse(throwBrickComponent.isBlocked(), "ThrowBrickComponent should not be blocked when adding a brick");
        assertTrue(gamePerformance.getBricks().size() == 1, "A brick should be added when component is not blocked");
    }

    /**
     * Test adding a brick when the component is blocked.
     */
    @Test
    void testAddBrickWhenBlocked() {
        assertTrue(gamePerformance.getBricks().isEmpty(), "The set of bricks should be empty");
        throwBrickComponent.setBlocked();
        final Pair<Double, Double> position = new Pair<>(300.0, 300.0);
        throwBrickComponent.addBrickToThrow(gamePerformance.getBricks(), position);
        assertTrue(throwBrickComponent.isBlocked(), "ThrowBrickComponent should be blocked when adding a brick");
        assertTrue(gamePerformance.getBricks().isEmpty(), "No brick should be added when component is blocked");
    }

    /**
     * Test setting the component to blocked.
     */
    @Test
    void testSetBlocked() {
        throwBrickComponent.setBlocked();
        assertTrue(throwBrickComponent.isBlocked(), "ThrowBrickComponent should be blocked after calling setBlocked");
    }

    /**
     * Test setting the component to unblocked.
     */
    @Test
    void testSetUnblocked() {
        throwBrickComponent.setBlocked();
        throwBrickComponent.setUnblocked();
        assertFalse(throwBrickComponent.isBlocked(),
                "ThrowBrickComponent should be unblocked after calling setUnblocked");
    }
}
