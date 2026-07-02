package it.unibo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.controller.impl.CakeController;
import it.unibo.controller.impl.GameController;
import it.unibo.controller.impl.WindowsController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.utilities.Constants;

/**
 * Test for the CakeController.
 */
class CakeControllerTest {
    private static final long TIME_SLEEP = 6000;

    private CakeController cakeController;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        initializeCakeController();
    }

    /**
     * Initializes the CakeController instance.
     */
    private void initializeCakeController() {
        final GameController gameController = new GameController();
        final GamePerformance gamePerformance = new GamePerformanceImpl(gameController);
        final WindowsController windowsController = new WindowsController(gamePerformance);
        cakeController = new CakeController(gamePerformance);
        windowsController.windowsGrid(Constants.Windows.NUM_WINDOWS);
    }

    /**
     * Tests the getCakes method.
     */
    @Test
    void testGetCakes() {
        final Set<Entity> cakes = cakeController.getCakes();
        assertNotNull(cakes, "Cakes should not be null after initialization");
    }

    /**
     * Tests the update method.
     * 
     * @throws InterruptedException if the sleep is interrupted
     */
    @Test
    void testUpdate() throws InterruptedException {
        final long initialTime = System.currentTimeMillis();
        cakeController.update();
        final long currentTime = System.currentTimeMillis();
        if (currentTime - initialTime >= Constants.Cake.CREATION_INTERVA_1_C) {
            assertTrue(cakeController.getCakes().size() > 0, "Cake should be created after the time interval");
        }
        Thread.sleep(TIME_SLEEP);
        cakeController.update();
        final Set<Entity> cakes = cakeController.getCakes();
        assertTrue(cakes.isEmpty(), "All cakes should be removed after their active duration");
    }
}
