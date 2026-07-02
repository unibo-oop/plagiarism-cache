package it.unibo.controller;

import it.unibo.controller.impl.FelixController;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link FelixController}.
 */
class FelixControllerTest {

    private FelixController felixController;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        initializeFelixController();
    }

    /**
     * Initializes the FelixController instance.
     */
    private void initializeFelixController() {
        final GameController gameController = new GameController();
        final GamePerformance gamePerformance = new GamePerformanceImpl(gameController);
        felixController = new FelixController(gamePerformance);
    }

    /**
     * Tests the getFelix method.
     */
    @Test
    void testGetFelix() {
        final Entity felix = felixController.getFelix();
        assertNotNull(felix, "Felix should not be null after initialization");
    }

    /**
     * Tests the moveLeft method.
     */
    @Test
    void testMoveFelixLeft() {
        final Entity felix = felixController.getFelix();
        final double initialX = felix.getPosition().getX();
        felixController.moveLeft();
        final double newX = felix.getPosition().getX();
        assertTrue(newX < initialX, "Felix should have moved left");
    }

    /**
     * Tests the moveRight method.
     */
    @Test
    void testMoveFelixRight() {
        final Entity felix = felixController.getFelix();
        final double initialX = felix.getPosition().getX();
        felixController.moveRight();
        final double newX = felix.getPosition().getX();
        assertTrue(newX > initialX, "Felix should have moved right");
    }
}
