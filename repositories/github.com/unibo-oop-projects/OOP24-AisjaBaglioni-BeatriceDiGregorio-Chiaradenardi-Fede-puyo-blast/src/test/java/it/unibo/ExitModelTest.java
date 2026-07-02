package it.unibo;

import it.unibo.model.ExitModel;
import it.unibo.model.interfaces.ExitModelInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the ExitModel class.
 * It verifies the behavior of the "Exit" feature, specifically 
 * whether it correctly handles the state of the "Exit" button 
 * (whether it has been clicked or not).
 */
class ExitModelTest {

    private ExitModelInterface exitModel;

    /**
     * Sets up the testing environment by initializing a new ExitModel instance.
     * This method is called before each test is run to ensure that each test 
     * has a fresh instance of ExitModel.
     */
    @BeforeEach
    void setUp() {
        exitModel = new ExitModel();
    }

    /**
     * Tests that the "Exit" feature is not triggered by default when an ExitModel
     * instance is created (i.e., the exitClicked flag is false).
     */
    @Test
    void testIsExitClicked_DefaultState() {
        assertFalse(exitModel.isExitClicked(), "The 'Exit' button should not be clicked by default.");
    }

    /**
     * Tests the ability to set the "Exit" action to clicked (true).
     * It verifies that the state of the exitClicked flag can be set to true.
     */
    @Test
    void testSetExitClicked_Enable() {
        exitModel.setExitClicked(true);

        assertTrue(exitModel.isExitClicked(), "The 'Exit' button should be clicked after setting it to true.");
    }

    /**
     * Tests the ability to set the "Exit" action to not clicked (false).
     * It verifies that the state of the exitClicked flag can be set to false.
     */
    @Test
    void testSetExitClicked_Disable() {
        exitModel.setExitClicked(true); 
        exitModel.setExitClicked(false); 

        assertFalse(exitModel.isExitClicked(), "The 'Exit' button should not be clicked after setting it to false.");
    }

    /**
     * Tests the toggling of the "Exit" feature.
     * This checks that the exitClicked flag can be toggled multiple times 
     * between true and false.
     */
    @Test
    void testToggleExitClicked() {
        assertFalse(exitModel.isExitClicked(), "The 'Exit' button should not be clicked by default.");

        exitModel.setExitClicked(true);
        assertTrue(exitModel.isExitClicked(), "The 'Exit' button should be clicked after calling setExitClicked(true).");

        exitModel.setExitClicked(false);
        assertFalse(exitModel.isExitClicked(), "The 'Exit' button should not be clicked after calling setExitClicked(false).");
    }
}
