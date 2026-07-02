package it.unibo.oop.test.io.mouse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

import it.unibo.oop.crossline.io.MoveDirections;
import it.unibo.oop.crossline.io.mouse.MouseButton;
import it.unibo.oop.crossline.io.mouse.MouseHandler;
import it.unibo.oop.crossline.io.mouse.MouseObserver;
import it.unibo.oop.crossline.io.mouse.MouseHandler.MODE;

/**
 * Test class for MouseHandler.
 */
public class MouseHandlerTest {
    /**
     * Zero value.
     */
    private static final int ZERO = 0;
    /**
     * First handler for mouse.
     */
    private MouseHandler mouseHandler1;
    /**
     * Second handler for mouse.
     */
    private MouseHandler mouseHandler2;
    /**
     * Same as first handler for mouse.
     */
    private MouseHandler sameAsMouseHandler1;
    /**
     * Unimplemented handler for mouse.
     */
    private static final MouseHandler UNIMPLEMENTED_MOUSE_HANDLER = null;

    /**
     * SetUp method for instance buttons.
     * 
     * @throws java.lang.Exception generic exception
     */
    @Before
    public void setUp() throws Exception {
        mouseHandler1 = new MouseHandler();
        mouseHandler1.setKeyCode(Buttons.LEFT);
        mouseHandler2 = new MouseHandler();
        mouseHandler1.setKeyCode(Buttons.RIGHT);
        sameAsMouseHandler1 = mouseHandler1;
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertNotEquals(mouseHandler1.hashCode(), ZERO);
        assertNotEquals(mouseHandler2.hashCode(), ZERO);
        assertNotEquals(mouseHandler1.hashCode(), mouseHandler2.hashCode());
        assertEquals("mouseHandler1's hashcode equals to sameAsmouseHandler1's hashcode", mouseHandler1.hashCode(),
                sameAsMouseHandler1.hashCode());

    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#MouseHandler(java.util.Map)}.
     */
    @Test
    public void testMouseHandler() {
        assertNotNull("mouseHandler1 is not null", mouseHandler1);
        assertNotNull("mouseHandler2 is not null", mouseHandler2);
        assertNotNull("sameAsMouseHandler1 is not null", sameAsMouseHandler1);
        assertNull("UNIMPLEMENTED_MOUSE_HANDLER is null", UNIMPLEMENTED_MOUSE_HANDLER);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#registerObserver(it.unibo.oop.crossline.io.mouse.MouseObserver)}.
     */
    @Test
    public void testRegisterObserver() {
        final MouseButton mouseButton = new MouseButton(Buttons.LEFT, MoveDirections.FORWARD.name(), () -> {
            MoveDirections.FORWARD.print();
        });
        assertTrue("mouseHandler1 registered mouseButton well", mouseHandler1.registerObserver(mouseButton));

    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#notifyObserver(it.unibo.oop.crossline.io.mouse.MouseHandler.MODE)}.
     */
    @Test
    public void testNotifyObserver() {
        assertTrue("mouseHandler1 notified observers well", mouseHandler1.notifyObserver(MODE.UPDATE));
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#removeObserver(it.unibo.oop.crossline.io.mouse.MouseObserver)}.
     */
    @Test
    public void testRemoveObserver() {
        final MouseButton mouseButton = new MouseButton(Buttons.LEFT, MoveDirections.FORWARD.name(), () -> {
            MoveDirections.FORWARD.print();
        });
        assertTrue("mouseHandler1 removed mouseButton well", mouseHandler1.removeObserver(mouseButton));
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#updateButtonPressed(int, int, boolean, int)}.
     */
    @Test
    public void testUpdateButtonPressed() {
        assertTrue("mouseHandler1 updated button pressed well",
                mouseHandler1.updateButtonPressed(ZERO, ZERO, false, Buttons.LEFT));
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#updateMoved(int, int)}.
     */
    @Test
    public void testUpdateMoved() {
        assertTrue("mouseHandler1 updated moved well", mouseHandler1.updateMoved(ZERO, ZERO));
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#updateMovedDragger(int, int)}.
     */
    @Test
    public void testUpdateMovedDragger() {
        assertTrue("mouseHandler1 updated move dragger well", mouseHandler1.updateMoved(ZERO, ZERO));
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#getObservers()}.
     */
    @Test
    public void testGetObservers() {
        assertNotNull("mouseHandler1's observers list is not null", mouseHandler1.getObservers());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#setObservers(java.util.List)}.
     */
    @Test
    public void testSetObservers() {
        final MouseButton button1 = new MouseButton(Buttons.LEFT, MoveDirections.FORWARD.name(), () -> {
            MoveDirections.FORWARD.print();
        });
        final MouseButton button2 = new MouseButton(Buttons.RIGHT, MoveDirections.BACKWARD.name(), () -> {
            MoveDirections.BACKWARD.print();
        });
        final List<MouseObserver> observers = new ArrayList<MouseObserver>();
        observers.add(button1);
        observers.add(button2);

        mouseHandler1.setObservers(observers);
        assertEquals("mouseHandler1's observers equals to observers", mouseHandler1.getObservers(), observers);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#getKeyCode()}.
     */
    @Test
    public void testGetKeyCode() {
        assertNotNull("mouseHandler1's keyCode is not null", mouseHandler1.getKeyCode());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#setKeyCode(int)}.
     */
    @Test
    public void testSetKeyCode() {
        final int keyCode = Keys.ANY_KEY;
        mouseHandler1.setKeyCode(keyCode);
        assertEquals("mouseHandler1's keyCode equals to keyCode", mouseHandler1.getKeyCode(), keyCode);
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.mouse.MouseHandler#getX()}.
     */
    @Test
    public void testGetX() {
        assertNotNull("mouseHandler1's x is not null", mouseHandler1.getX());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#setX(int)}.
     */
    @Test
    public void testSetX() {
        final int x = 1;
        mouseHandler1.setX(x);
        assertEquals("mouseHandler1's x set has done well", mouseHandler1.getX(), x);
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.io.mouse.MouseHandler#getY()}.
     */
    @Test
    public void testGetY() {
        assertNotNull("mouseHandler1's y is not null", mouseHandler1.getY());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#setY(int)}.
     */
    @Test
    public void testSetY() {
        final int y = 1;
        mouseHandler1.setX(y);
        assertEquals("mouseHandler1's y set has done well", mouseHandler1.getX(), y);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#isDragger()}.
     */
    @Test
    public void testIsDragger() {
        assertNotNull("mouseHandler1's dragger is not null", mouseHandler1.isDragger());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#setDragger(boolean)}.
     */
    @Test
    public void testSetDragger() {
        final boolean d = true;
        mouseHandler1.setDragger(d);
        assertEquals("mouseHandler1's dragger set has done well", mouseHandler1.isDragger(), d);

    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#toString()}.
     */
    @Test
    public void testToString() {
        assertNotNull("mouseHandler1's string is not null", mouseHandler1.toString());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.io.mouse.MouseHandler#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        assertNotEquals(mouseHandler1, mouseHandler2);
        assertEquals("mouseHandler1 equals sameAsMouseHandler1", mouseHandler1, sameAsMouseHandler1);
    }

}
