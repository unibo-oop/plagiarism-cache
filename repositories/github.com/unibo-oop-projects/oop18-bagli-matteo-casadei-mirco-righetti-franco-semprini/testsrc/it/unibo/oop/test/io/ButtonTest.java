package it.unibo.oop.test.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Input.Keys;

import it.unibo.oop.crossline.io.Button;
import it.unibo.oop.crossline.io.MoveDirections;

/**
 * Test class for Button.
 */
public class ButtonTest {

    /**
     * Zero value.
     */
    private static final int ZERO = 0;
    /**
     * First button.
     */
    private Button button1;
    /**
     * Second button.
     */
    private Button button2;
    /**
     * Same as first button.
     */
    private Button sameAsButton1;
    /**
     * Unimplemented button.
     */
    private static final Button UNIMPLEMENTED_BUTTON = null;

    /**
     * SetUp method for instance buttons.
     * 
     * @throws java.lang.Exception generic exception
     */
    @Before
    public void setUp() throws Exception {
        button1 = new Button(Keys.W, MoveDirections.FORWARD.name(), () -> {
            MoveDirections.FORWARD.print();
        });
        button2 = new Button(Keys.S, MoveDirections.BACKWARD.name(), () -> {
            MoveDirections.BACKWARD.print();
        });
        sameAsButton1 = button1;
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Button#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertNotEquals(button1.hashCode(), ZERO);
        assertNotEquals(button2.hashCode(), ZERO);
        assertNotEquals(button1.hashCode(), button2.hashCode());
        assertEquals("button1 equals to sameAsButton1", button1.hashCode(), sameAsButton1.hashCode());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.Button#Button(int, it.unibo.oop.crossline.io.Action)}.
     */
    @Test
    public void testButton() {
        assertNotNull("button1 is not null", button1);
        assertNotNull("button2 is not null", button2);
        assertNotNull("sameAsButton1 is not null", sameAsButton1);
        assertNull("UNIMPLEMENTED_BUTTON is null", UNIMPLEMENTED_BUTTON);
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Button#getAction()}.
     */
    @Test
    public void testGetAction() {
        assertNotNull("the action of button1 is not null", button1.getAction());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.Button#setAction(it.unibo.oop.crossline.io.Action)}.
     */
    @Test
    public void testSetAction() {
        final Runnable action = () -> System.out.println("Test");
        button1.setAction(action);
        assertEquals("the action of button1 equals to action", button1.getAction(), action);
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Button#getKeyCode()}.
     */
    @Test
    public void testGetKeyCode() {
        assertNotNull("the keyCode of button1 is not null", button1.getKeyCode());
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Button#setKeyCode(int)}.
     */
    @Test
    public void testSetKeyCode() {
        final int keyCode = Keys.A;
        button1.setKeyCode(keyCode);
        assertEquals("the keyCode of button1 equals to keyCode", button1.getKeyCode(), keyCode);
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.Button#toString()}.
     */
    @Test
    public void testToString() {
        assertNotNull("the string of button1 is not null", button1.toString());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.Button#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        assertNotEquals(button1, button2);
        assertEquals("button1 equals to sameAsButton1", button1, sameAsButton1);
    }
}
