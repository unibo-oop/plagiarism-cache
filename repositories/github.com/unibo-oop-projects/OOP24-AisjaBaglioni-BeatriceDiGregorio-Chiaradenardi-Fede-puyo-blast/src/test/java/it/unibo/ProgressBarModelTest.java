package it.unibo;

import it.unibo.model.ProgressBarModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for ProgressBarModel.
 * Verifies that the behavior of the progress bar model is correct.
 */
class ProgressBarModelTest {
    private ProgressBarModel progressBar;

    /**
     * Method executed before each test.
     * Initializes a new ProgressBarModel object.
     */
    @BeforeEach
    void setUp() {
        progressBar = new ProgressBarModel();
    }

    /**
     * Test: the initial charge level must be 0.
     */
    @Test
    void testInitialChargeLevelIsZero() {
        assertEquals(0.0, progressBar.getChargeLevel(),
                "The initial charge level should be 0.");
    }

    /**
     * Test: the incrementProgress() method must increase the charge level.
     */
    @Test
    void testIncrementProgress() {
        progressBar.incrementProgress();
        assertTrue(progressBar.getChargeLevel() > 0,
                "The charge level should increase after an increment.");
    }

    /**
     * Test: the charge level must not exceed 1, even after many increments.
     */
    @Test
    void testChargeDoesNotExceedOne() {
        for (int i = 0; i < 1000; i++) {
            progressBar.incrementProgress();
        }
        assertEquals(1.0, progressBar.getChargeLevel(), 0.0001,
                "The charge level should not exceed 1.");
    }

    /**
     * Test: the resetCharge() method must reset the charge to 0.
     */
    @Test
    void testResetCharge() {
        progressBar.incrementProgress();
        assertTrue(progressBar.getChargeLevel() > 0,
                "The charge level should be greater than 0 before reset.");
        progressBar.resetCharge();
        assertEquals(0.0, progressBar.getChargeLevel(),
                "The charge level should be reset to 0.");
    }

}
