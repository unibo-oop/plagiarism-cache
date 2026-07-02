package it.unibo;

import it.unibo.model.TryAgainModel;
import it.unibo.model.interfaces.TryAgainModelInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the TryAgainModel class.
 * It verifies the behavior of the "Try Again" feature, specifically 
 * its enabling and disabling functionality.
 */
class TryAgainModelTest {

    private TryAgainModelInterface tryAgainModel;

    /**
     * Sets up the testing environment by initializing a new TryAgainModel instance.
     */
    @BeforeEach
    void setUp() {
        tryAgainModel = new TryAgainModel();
    }

    /**
     * Tests that the "Try Again" feature is enabled by default when a TryAgainModel
     * instance is created.
     */
    @Test
    void testIsEnabled_DefaultState() {
        assertTrue(tryAgainModel.isEnabled(), "The 'Try Again' feature should be enabled by default.");
    }

    /**
     * Tests the ability to disable the "Try Again" feature.
     * It verifies that the "Try Again" feature can be disabled by setting its state to false.
     */
    @Test
    void testSetEnabled_Disable() {
        tryAgainModel.setEnabled(false);

        assertFalse(tryAgainModel.isEnabled(), "The 'Try Again' feature should be disabled after setting it to false.");
    }

    /**
     * Tests the ability to enable the "Try Again" feature after it has been disabled.
     * It verifies that the feature can be re-enabled.
     */
    @Test
    void testSetEnabled_Enable() {
        tryAgainModel.setEnabled(false);
        
        tryAgainModel.setEnabled(true);

        assertTrue(tryAgainModel.isEnabled(), "The 'Try Again' feature should be enabled after setting it to true.");
    }

    /**
     * Tests the ability to toggle the "Try Again" feature by enabling and disabling it 
     * multiple times.
     */
    @Test
    void testToggleEnabled() {
        assertTrue(tryAgainModel.isEnabled(), "The 'Try Again' feature should be enabled by default.");

        tryAgainModel.setEnabled(false);
        assertFalse(tryAgainModel.isEnabled(), "The 'Try Again' feature should be disabled after calling setEnabled(false).");

        tryAgainModel.setEnabled(true);
        assertTrue(tryAgainModel.isEnabled(), "The 'Try Again' feature should be enabled after calling setEnabled(true).");
    }
}
