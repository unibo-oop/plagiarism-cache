package it.unibo;

import it.unibo.controller.ProgressBarController;
import it.unibo.model.ProgressBarModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link ProgressBarController} class.
 * These tests ensure the controller behaves correctly in response to game ticks and reset actions.
 */
class ProgressBarControllerTest {

    private ProgressBarModel progressBarModel;
    private ProgressBarController progressBarController;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        progressBarModel = mock(ProgressBarModel.class);
        progressBarController = new ProgressBarController(progressBarModel);
    }

    /**
     * Test for the onTick method, which should increment the progress bar's charge level.
     */
    @Test
    void testOnTick() {
        progressBarController.onTick();
        verify(progressBarModel).incrementProgress();
    }

    /**
     * Test for the resetBar method when the progress bar is fully charged.
     * The method should reset the progress bar and return true.
     */
    @Test
    void testResetBarWhenFullyCharged() {
        when(progressBarModel.getChargeLevel()).thenReturn(1.0);
        
        boolean result = progressBarController.resetBar();
        
        verify(progressBarModel).resetCharge();
        assertTrue(result);
    }

    /**
     * Test for the resetBar method when the progress bar is not fully charged.
     * The method should return false and not reset the bar.
     */
    @Test
    void testResetBarWhenNotFullyCharged() {
        when(progressBarModel.getChargeLevel()).thenReturn(0.0);
        
        boolean result = progressBarController.resetBar();
    
        verify(progressBarModel, never()).resetCharge();
        assertFalse(result);
    }

    /**
     * Test for the reset method, which should reset the progress bar's charge.
     */
    @Test
    void testReset() {
        progressBarController.reset();
        verify(progressBarModel).resetCharge();
    }
}
