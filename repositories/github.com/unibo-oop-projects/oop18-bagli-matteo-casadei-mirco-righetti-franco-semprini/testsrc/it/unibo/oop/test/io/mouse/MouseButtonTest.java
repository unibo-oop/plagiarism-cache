package it.unibo.oop.test.io.mouse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector2;

import it.unibo.oop.crossline.io.MoveDirections;
import it.unibo.oop.crossline.io.mouse.MouseButton;

/**
 * Test class for Button.
 */
public class MouseButtonTest {

    /**
     * Zero value.
     */
    private static final int ZERO = 0;
    /**
     * First button.
     */
    private MouseButton button1;
    /**
     * Second button.
     */
    private MouseButton button2;
    /**
     * Same as first button.
     */
    private MouseButton sameAsButton1;
    /**
     * Unimplemented button.
     */
    private static final MouseButton UNIMPLEMENTED_BUTTON = null;

    /**
     * SetUp method for instance buttons.
     * 
     * @throws java.lang.Exception generic exception
     */
    @Before
    public void setUp() throws Exception {
        button1 = new MouseButton(Buttons.LEFT, MoveDirections.FORWARD.name(), () -> {
            MoveDirections.FORWARD.print();
        });
        button2 = new MouseButton(Buttons.RIGHT, MoveDirections.BACKWARD.name(), () -> {
            MoveDirections.BACKWARD.print();
        });
        sameAsButton1 = button1;
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertNotEquals(button1.hashCode(), ZERO);
        assertNotEquals(button2.hashCode(), ZERO);
        assertNotEquals(button1.hashCode(), button2.hashCode());
        assertEquals("button1's hashcode equals to sameAsButton1's hashcode", button1.hashCode(),
                sameAsButton1.hashCode());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#MouseButton(int, it.unibo.oop.crossline.io.Action)}.
     */
    @Test
    public void testMouseButton() {
        assertNotNull("button1 is not null", button1);
        assertNotNull("button2 is not null", button2);
        assertNotNull("sameAsButton1 is not null", sameAsButton1);
        assertNull("UNIMPLEMENTED_BUTTON is null", UNIMPLEMENTED_BUTTON);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#update(int, int, boolean, int, java.util.Map)}.
     */
    @Test
    public void testUpdate() {
        assertTrue("button1's update has done well", button1.update(ZERO, ZERO, false, button1.getKeyCode()));
        assertTrue("button2's update has done well", button2.update(ZERO, ZERO, false, Buttons.RIGHT));
        assertTrue("sameAsButton1's update has done well",
                sameAsButton1.update(ZERO, ZERO, false, button1.getKeyCode()));
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#updatePosition(int, int)}.
     */
    @Test
    public void testUpdatePosition() {
        assertTrue("button1's position update has done well", button1.updatePosition(ZERO, ZERO));
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#updatePositionDragged(int, int)}.
     */
    @Test
    public void testUpdatePositionDragged() {
        assertTrue("button1's position dragged update has done well", button1.updatePositionDragged(ZERO, ZERO));
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#isDragged()}.
     */
    @Test
    public void testIsDragged() {
        assertFalse("button1 has no dragger", button1.isDragged());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#setDragged(boolean)}.
     */
    @Test
    public void testSetDragged() {
        button1.setDragged(true);
        assertTrue("button1 has dragger", button1.isDragged());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#getAction()}.
     */
    @Test
    public void testGetAction() {
        assertNotNull("button1 has action", button1.getAction());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#setAction(it.unibo.oop.crossline.io.Action)}.
     */
    @Test
    public void testSetAction() {
        final Runnable action = () -> System.out.println("Test");
        button1.setAction(action);
        assertEquals("button1's action equals to action", button1.getAction(), action);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#getKeyCode()}.
     */
    @Test
    public void testGetKeyCode() {
        assertNotNull("button1's keyCode is not null", button1.getKeyCode());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#setKeyCode(int)}.
     */
    @Test
    public void testSetKeyCode() {
        final int keyCode = Buttons.MIDDLE;
        button1.setKeyCode(keyCode);
        assertEquals("button1's keyCode equals to keyCode", button1.getKeyCode(), keyCode);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#getMouseLocation()}.
     */
    @Test
    public void testGetMouseLocation() {
        assertNotNull("button1's mouse location is not null", button1.getMouseLocation());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#setMouseLocation(com.badlogic.gdx.math.Vector2)}.
     */
    @Test
    public void testSetMouseLocation() {
        final Vector2 mouseLocation = new Vector2(0, 0);
        button1.setMouseLocation(mouseLocation);
        assertEquals("button1's mouse location equals to mouse location", button1.getMouseLocation(), mouseLocation);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#toString()}.
     */
    @Test
    public void testToString() {
        assertNotNull("button1's string is not null", button1.toString());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseButton#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        assertNotEquals(button1, button2);
        assertEquals("button1 equals to sameAsButton1", button1, sameAsButton1);
    }
}
