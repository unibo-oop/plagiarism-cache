package it.unibo.model;

import it.unibo.controller.impl.GameController;
import it.unibo.model.impl.GamePerformanceImpl;
import it.unibo.model.impl.StopRalphComponent;
import it.unibo.model.impl.ThrowBrickComponent;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for the class StopRalphComponent.
 */
final class StopRalphComponentTest {

    private static final int UNBLOCK_WAIT_TIME_MS = 10_100;

    private StopRalphComponent stopRalphComponent;
    private ThrowBrickComponent throwBrickComponent;

    /**
     * Set up the test components before each test.
     */
    @BeforeEach
    void setUp() {
        initializeComponents();
    }

    /**
     * Initializes the components.
     */
    private void initializeComponents() {
        stopRalphComponent = new StopRalphComponent();
        throwBrickComponent = new ThrowBrickComponent(new GamePerformanceImpl(new GameController()));
    }

    /**
     * Test setting the stop Ralph component.
     */
    @Test
    void testSetStopRalph() {
        stopRalphComponent.setStopRalph(throwBrickComponent);
        assertTrue(throwBrickComponent.isBlocked(), "ThrowBrickComponent should be blocked after calling setStopRalph");
    }

    /**
     * Test checking and unlocking the Ralph component.
     *
     * @throws InterruptedException if the thread sleep is interrupted
     */
    @Test
    void testCheckUnlockRalph() throws InterruptedException {
        stopRalphComponent.setStopRalph(throwBrickComponent);
        Thread.sleep(UNBLOCK_WAIT_TIME_MS);
        stopRalphComponent.checkUnlockRalph(throwBrickComponent);
        assertFalse(throwBrickComponent.isBlocked(),
                "ThrowBrickComponent should be unblocked after calling checkUnlockRalph");
    }
}
