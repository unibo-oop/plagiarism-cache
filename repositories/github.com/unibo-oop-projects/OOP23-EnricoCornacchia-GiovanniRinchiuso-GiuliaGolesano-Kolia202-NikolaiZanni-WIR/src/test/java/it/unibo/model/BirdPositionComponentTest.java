package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.impl.BirdPositionComponent;
import it.unibo.utilities.Constants;

/**
 * Test class for {@link BirdPositionComponent}.
 */
final class BirdPositionComponentTest {

    /**
     * The BirdPositionComponent instance to be tested.
     */
    private BirdPositionComponent birdPositionComponent;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        initializeBirdPositionComponent();
    }

    /**
     * Initializes the BirdPositionComponent instance.
     */
    private void initializeBirdPositionComponent() {
        birdPositionComponent = new BirdPositionComponent();
    }

    /**
     * Tests the randomPosition method.
     */
    @Test
    void testRandomPosition() {
        final Pair<Double, Double> position = birdPositionComponent.randomPosition();
        assertEquals(Constants.GameEdges.LEFT_WALL, position.getX());
        final double birdY = position.getY();
        assertTrue(birdY == Constants.Bird.FLOOR_1_Y_B
                || birdY == Constants.Bird.FLOOR_2_Y_B
                || birdY == Constants.Bird.FLOOR_3_Y_B,
                "Y coordinate should be one of the predefined floor values");
    }

    /**
     * Tests the getComponent method.
     */
    @Test
    void testGetComponent() {
        assertEquals(ComponentType.BIRDPOSITION, birdPositionComponent.getComponent());
    }
}
