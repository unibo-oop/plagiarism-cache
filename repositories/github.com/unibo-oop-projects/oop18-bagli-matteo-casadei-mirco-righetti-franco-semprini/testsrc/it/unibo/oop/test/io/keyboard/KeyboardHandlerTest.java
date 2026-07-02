package it.unibo.oop.test.io.keyboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Input.Keys;

import it.unibo.oop.crossline.io.MoveDirections;
import it.unibo.oop.crossline.io.keyboard.KeyboardButton;
import it.unibo.oop.crossline.io.keyboard.KeyboardHandler;
import it.unibo.oop.crossline.io.keyboard.KeyboardObserver;

/**
 * Test class for KeyboardHandler.
 */
public class KeyboardHandlerTest {

    /**
     * Zero value.
     */
    private static final int ZERO = 0;
    /**
     * First handler for keyboard.
     */
    private KeyboardHandler keyboardHandler1;
    /**
     * Second handler for keyboard.
     */
    private KeyboardHandler keyboardHandler2;
    /**
     * Same as first handler for keyboard.
     */
    private KeyboardHandler sameAsKeyboardHandler1;
    /**
     * Unimplemented handler for keyboard.
     */
    private static final KeyboardHandler UNIMPLEMENTED_KEYBOARD_HANDLER = null;

    /**
     * SetUp method for instance buttons.
     * 
     * @throws java.lang.Exception generic exception
     */
    @Before
    public void setUp() throws Exception {
        keyboardHandler1 = new KeyboardHandler();
        keyboardHandler1.setKeyCode(Keys.A);
        keyboardHandler2 = new KeyboardHandler();
        keyboardHandler1.setKeyCode(Keys.B);
        sameAsKeyboardHandler1 = keyboardHandler1;
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardHandler#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertNotEquals(keyboardHandler1.hashCode(), ZERO);
        assertNotEquals(keyboardHandler2.hashCode(), ZERO);
        assertNotEquals(keyboardHandler1.hashCode(), keyboardHandler2.hashCode());
        assertEquals("keyboardHandler1's hashcode equals to sameAsKeyboardHandler1's hashcode",
                keyboardHandler1.hashCode(), sameAsKeyboardHandler1.hashCode());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardHandler#KeyboardHandler(java.util.Map)}.
     */
    @Test
    public void testKeyboardHandler() {
        assertNotNull("keyboardHandler1 is not null", keyboardHandler1);
        assertNotNull("keyboardHandler2 is not null", keyboardHandler2);
        assertNotNull("sameAsKeyboardHandler1 is not null", sameAsKeyboardHandler1);
        assertNull("UNIMPLEMENTED_KEYBOARD_HANDLER is null", UNIMPLEMENTED_KEYBOARD_HANDLER);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardHandler#registerObserver(it.unibo.oop.crossline.io.keyboard.KeyboardObserver)}.
     */
    @Test
    public void testRegisterObserver() {
        final KeyboardButton keyboardButton = new KeyboardButton(Keys.W, MoveDirections.FORWARD.name(), () -> {
            MoveDirections.FORWARD.print();
        });
        assertTrue("keyboardHandler1 registered mouseButton well", keyboardHandler1.registerObserver(keyboardButton));
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardHandler#notifyObserver()}.
     */
    @Test

    public void testNotifyObserver() {
        assertTrue("keyboardHandler1 notified observers well", keyboardHandler1.notifyObserver());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardHandler#removeObserver(it.unibo.oop.crossline.io.keyboard.KeyboardObserver)}.
     */
    @Test
    public void testRemoveObserver() {
        final KeyboardButton keyboardButton = new KeyboardButton(Keys.W, MoveDirections.FORWARD.name(), () -> {
            MoveDirections.FORWARD.print();
        });
        assertTrue("keyboardHandler1 removed mouseButton well", keyboardHandler1.removeObserver(keyboardButton));
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardHandler#updateKeyPressed(int, boolean)}.
     */
    @Test
    public void testUpdateKeyPressed() {
        assertTrue("keyboardHandler1 updated button pressed well", keyboardHandler1.updateKeyPressed(Keys.ANY_KEY));
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardHandler#getObservers()}.
     */
    @Test
    public void testGetObservers() {
        assertNotNull("keyboardHandler1 updated button pressed well", keyboardHandler1.getObservers());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardHandler#setObservers(java.util.List)}.
     */
    @Test
    public void testSetObservers() {
        final KeyboardButton button1 = new KeyboardButton(Keys.W, MoveDirections.FORWARD.name(), () -> {
            MoveDirections.FORWARD.print();
        });
        final KeyboardButton button2 = new KeyboardButton(Keys.S, MoveDirections.BACKWARD.name(), () -> {
            MoveDirections.BACKWARD.print();
        });
        final List<KeyboardObserver> observers = new ArrayList<KeyboardObserver>();
        observers.add(button1);
        observers.add(button2);

        keyboardHandler1.setObservers(observers);
        assertEquals("keyboardHandler1's observers equals to observers", keyboardHandler1.getObservers(), observers);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardHandler#getKeyCode()}.
     */
    @Test
    public void testGetKeyCode() {
        assertNotNull("keyboardHandler1's keyCode is not null", keyboardHandler1.getKeyCode());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardHandler#setKeyCode(int)}.
     */
    @Test
    public void testSetKeyCode() {
        final int keyCode = Keys.ANY_KEY;
        keyboardHandler1.setKeyCode(keyCode);
        assertEquals("keyboardHandler1's keyCode equals to keyCode", keyboardHandler1.getKeyCode(), keyCode);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardHandler#toString()}.
     */
    @Test
    public void testToString() {
        assertNotNull("keyboardHandler1's string is not null", keyboardHandler1.toString());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardHandler#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        assertNotEquals(keyboardHandler1, keyboardHandler2);
        assertEquals("keyboardHandler1 equals sameAsKeyboardHandler1", keyboardHandler1, sameAsKeyboardHandler1);
    }

}
