package it.unibo;

import it.unibo.controller.KeyboardController;
import it.unibo.model.KeyboardModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link KeyboardController} class.
 */
class KeyboardControllerTest {
    private KeyboardModel keyboardModel;
    private KeyboardController keyboardController;

    /**
     * Sets up the testing environment before each test.
     * Initializes the mock objects and the KeyboardController instance.
     */
    @BeforeEach
    void setUp() {
        keyboardModel = mock(KeyboardModel.class);
        keyboardController = new KeyboardController(keyboardModel);
    }

    /**
     * Test: Verifies that when a key is pressed, the correct method is called on the keyboardModel.
     */
    @Test
    void testKeyPressed() {
        KeyEvent keyEvent = mock(KeyEvent.class);
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_W);

        keyboardController.keyPressed(keyEvent);
        verify(keyboardModel).keyDown(KeyEvent.VK_W);
    }

    /**
     * Test: Verifies that when a key is released, the correct method is called on the keyboardModel.
     */
    @Test
    void testKeyReleased() {
        KeyEvent keyEvent = mock(KeyEvent.class);
        when(keyEvent.getKeyCode()).thenReturn(KeyEvent.VK_W);

        keyboardController.keyReleased(keyEvent);
        verify(keyboardModel).keyUp(KeyEvent.VK_W);
    }

    /**
     * Test: Verifies that the keyTyped method is not used and has no effect.
     */
    @Test
    void testKeyTyped() {
        KeyEvent keyEvent = mock(KeyEvent.class);

        keyboardController.keyTyped(keyEvent);
        verifyNoInteractions(keyboardModel);
    }
}
