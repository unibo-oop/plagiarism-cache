package it.unibo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.controller.impl.BirdController;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.utilities.Constants;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link BirdController}.
 */
class BirdControllerTest {

    private BirdController birdController;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        initializeBirdController();
    }

    /**
     * Initializes the BirdController instance.
     */
    private void initializeBirdController() {
        final GameController gameController = new GameController();
        final GamePerformance gamePerformance = new GamePerformanceImpl(gameController);
        birdController = new BirdController(gamePerformance);
    }

    /**
     * Tests the getBirds method.
     */
    @Test
    void testGetBirds() {
        final Set<Entity> birds = birdController.getBirds();
        assertNotNull(birds, "Birds should not be null after initialization");
    }

    /**
     * Tests the moveBird method.
     */
    @Test
    void testMoveBird() {
        birdController.update();
        birdController.moveBird();
        for (final Entity bird : birdController.getBirds()) {
            final double initialX = bird.getPosition().getX();
            birdController.moveBird();
            final double newX = bird.getPosition().getX();
            assertTrue(newX > initialX, "Bird's X position should increase after moving");
        }
    }

    /**
     * Tests the update method.
     */
    @Test
    void testUpdate() {
        final long initialTime = System.currentTimeMillis();
        birdController.update();
        final long currentTime = System.currentTimeMillis();
        if (currentTime - initialTime >= Constants.Bird.CREATION_INTERVA_1_B) {
            assertTrue(birdController.getBirds().size() > 0, "Bird should be created after the time interval");
        }
    }
}
