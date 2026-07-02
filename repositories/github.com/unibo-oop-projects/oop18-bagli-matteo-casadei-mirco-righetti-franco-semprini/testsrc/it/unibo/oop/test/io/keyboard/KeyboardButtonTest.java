package it.unibo.oop.test.io.keyboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Input.Keys;
import it.unibo.oop.crossline.io.MoveDirections;
import it.unibo.oop.crossline.io.keyboard.KeyboardButton;

/**
 * Test class for KeyboardButton.
 */
public class KeyboardButtonTest {

    /**
     * Zero value.
     */
    private static final int ZERO = 0;
    /**
     * First button.
     */
    private KeyboardButton button1;
    /**
     * Second button.
     */
    private KeyboardButton button2;
    /**
     * Same as first button.
     */
    private KeyboardButton sameAsButton1;
    /**
     * Unimplemented button.
     */
    private static final KeyboardButton UNIMPLEMENTED_BUTTON = null;

    /**
     * SetUp method for instance buttons.
     * 
     * @throws java.lang.Exception generic exception
     */
    @Before
    public void setUp() throws Exception {
        button1 = new KeyboardButton(Keys.W, MoveDirections.FORWARD.name(), () -> {
            MoveDirections.FORWARD.print();
        });
        button2 = new KeyboardButton(Keys.S, MoveDirections.BACKWARD.name(), () -> {
            MoveDirections.BACKWARD.print();
        });
        sameAsButton1 = button1;
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardButton#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertNotEquals(button1.hashCode(), ZERO);
        assertNotEquals(button2.hashCode(), ZERO);
        assertNotEquals(button1.hashCode(), button2.hashCode());
        assertEquals("button1's hashcode equals to sameAsButton1's hashcode", button1.hashCode(), sameAsButton1.hashCode());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardButton#KeyboardButton(int, it.unibo.oop.crossline.io.Action)}.
     */
    @Test
    public void testKeyboardButton() {
        assertNotNull("button1 is not null", button1);
        assertNotNull("button2 is not null", button2);
        assertNotNull("sameAsButton1 is not null", sameAsButton1);
        assertNull("UNIMPLEMENTED_BUTTON is null", UNIMPLEMENTED_BUTTON);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardButton#getAction()}.
     */
    @Test
    public void testGetAction() {
        assertNotNull("button1 has action", button1.getAction());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardButton#setAction(it.unibo.oop.crossline.io.Action)}.
     */
    @Test
    public void testSetAction() {
        final Runnable action = () -> System.out.println("Test");
        button1.setAction(action);
        assertEquals("button1's action equals to action", button1.getAction(), action);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardButton#getKeyCode()}.
     */
    @Test
    public void testGetKeyCode() {
        assertNotNull("button1's keyCode is not null", button1.getKeyCode());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardButton#setKeyCode(int)}.
     */
    @Test
    public void testSetKeyCode() {
        final int keyCode = Keys.A;
        button1.setKeyCode(keyCode);
        assertEquals("button1's keyCode equals to keyCode", button1.getKeyCode(), keyCode);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardButton#update(int, java.util.Map, boolean)}.
     */
    @Test
    public void testUpdate() {
        assertTrue("button1's update has done well", button1.update(button1.getKeyCode()));
        assertTrue("button2's update has done well", button2.update(Keys.S));
        assertTrue("sameAsButton1's update has done well", sameAsButton1.update(button1.getKeyCode()));
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardButton#toString()}.
     */
    @Test
    public void testToString() {
        assertNotNull("button1's string is not null", button1.toString());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.keyboard.KeyboardButton#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        assertNotEquals(button1, button2);
        assertEquals("button1 equals to sameAsButton1", button1, sameAsButton1);
    }

}
