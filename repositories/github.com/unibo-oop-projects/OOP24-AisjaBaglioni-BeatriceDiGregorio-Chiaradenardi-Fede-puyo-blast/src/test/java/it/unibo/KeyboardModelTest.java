package it.unibo;

import it.unibo.model.KeyboardModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link KeyboardModel} class.
 */
class KeyboardModelTest {
    
    private KeyboardModel keyboardModel;

    /**
     * Set up the test environment by initializing the KeyboardModel before each test.
     */
    @BeforeEach
    void setUp() {
        keyboardModel = new KeyboardModel();
    }

    /**
     * Test: Verifies that a key can be correctly pressed.
     */
    @Test
    void testKeyDown() {
        keyboardModel.keyDown(87); /** 87 is the key code for "W" */
        assertTrue(keyboardModel.isKeyPressed(87), "The 'W' key should be pressed.");
    }

    /**
     * Test: Verifies that a key can be correctly released.
     */
    @Test
    void testKeyUp() {
        keyboardModel.keyDown(87);
        assertTrue(keyboardModel.isKeyPressed(87), "The 'W' key should be pressed.");

        keyboardModel.keyUp(87);
        assertFalse(keyboardModel.isKeyPressed(87), "The 'W' key should not be pressed after release.");
    }

    /**
     * Test: Verifies that multiple keys can be pressed and released correctly.
     */
    @Test
    void testMultipleKeys() {
        keyboardModel.keyDown(87);
        keyboardModel.keyDown(65);
        keyboardModel.keyDown(68);

        assertTrue(keyboardModel.isKeyPressed(87), "The 'W' key should be pressed.");
        assertTrue(keyboardModel.isKeyPressed(65), "The 'A' key should be pressed.");
        assertTrue(keyboardModel.isKeyPressed(68), "The 'D' key should be pressed.");

        keyboardModel.keyUp(65);
        assertFalse(keyboardModel.isKeyPressed(65), "The 'A' key should not be pressed after release.");
    }

    /**
     * Test: Verifies that the isKeyPressed method returns false for keys that are not pressed.
     */
    @Test
    void testKeyNotPressed() {
        assertFalse(keyboardModel.isKeyPressed(87), "The 'W' key should not be pressed initially.");
    }
}
