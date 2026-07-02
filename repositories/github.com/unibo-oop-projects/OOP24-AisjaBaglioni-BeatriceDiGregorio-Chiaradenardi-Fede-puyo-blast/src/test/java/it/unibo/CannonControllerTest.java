package it.unibo;

import static org.mockito.Mockito.*;
import it.unibo.controller.CannonController;
import it.unibo.model.CannonModel;
import it.unibo.model.KeyboardModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

/**
 * Unit tests for the {@link CannonController} class.
 */

class CannonControllerTest {
    private CannonModel cannonModel;
    private KeyboardModel keyboardModel;
    private CannonController cannonController;

    /**
     * Initializes the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        cannonModel = mock(CannonModel.class);
        keyboardModel = mock(KeyboardModel.class);
        cannonController = new CannonController(cannonModel, keyboardModel, null);
    }

    /**
     * Tests if the cannon moves right when the right arrow key is pressed.
     */
    @Test
    void testMoveRight() {
        when(keyboardModel.isKeyPressed(KeyEvent.VK_RIGHT)).thenReturn(true);
        cannonController.onTick();
        verify(cannonModel).moveRight();
    }

    /**
     * Tests if the cannon moves left when the left arrow key is pressed.
     */
    @Test
    void testMoveLeft() {
        when(keyboardModel.isKeyPressed(KeyEvent.VK_LEFT)).thenReturn(true);
        cannonController.onTick();
        verify(cannonModel).moveLeft();
    }

    /**
     * Tests if the cannon aims up when the up arrow key is pressed.
     */
    @Test
    void testAimUp() {
        when(keyboardModel.isKeyPressed(KeyEvent.VK_UP)).thenReturn(true);
        cannonController.onTick();
        verify(cannonModel).aimUp();
    }

    /**
     * Tests if the cannon aims down when the down arrow key is pressed.
     */
    @Test
    void testAimDown() {
        when(keyboardModel.isKeyPressed(KeyEvent.VK_DOWN)).thenReturn(true);
        cannonController.onTick();
        verify(cannonModel).aimDown();
    }

    /**
     * Tests that no movement occurs when no keys are pressed.
     */
    @Test
    void testNoMovementWhenNoKeysPressed() {
        when(keyboardModel.isKeyPressed(KeyEvent.VK_RIGHT)).thenReturn(false);
        when(keyboardModel.isKeyPressed(KeyEvent.VK_LEFT)).thenReturn(false);
        when(keyboardModel.isKeyPressed(KeyEvent.VK_UP)).thenReturn(false);
        when(keyboardModel.isKeyPressed(KeyEvent.VK_DOWN)).thenReturn(false);

        cannonController.onTick();

        verify(cannonModel, never()).moveLeft();
        verify(cannonModel, never()).moveRight();
        verify(cannonModel, never()).aimUp();
        verify(cannonModel, never()).aimDown();
    }
}
