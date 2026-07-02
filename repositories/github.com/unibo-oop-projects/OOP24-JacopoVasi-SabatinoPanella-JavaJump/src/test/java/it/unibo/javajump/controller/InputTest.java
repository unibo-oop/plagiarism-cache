package it.unibo.javajump.controller;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.controller.input.InputManagerImpl;
import it.unibo.javajump.utility.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JTextField;
import java.awt.event.KeyEvent;

import static it.unibo.javajump.utility.TestConstants.KEY_EVENT_MODIFIER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InputTest {

    private InputManagerImpl inputManager;
    private JTextField testComponent; // A dummy component for KeyEvent source

    @BeforeEach
    void setUp() {
        inputManager = new InputManagerImpl();
        testComponent = new JTextField(); // Using a JTextField as a valid event source
    }

    @Test
    void testPressingLeftKeySetsPressingLeftFlag() {
        final KeyEvent event = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), KEY_EVENT_MODIFIER, KeyEvent.VK_LEFT, ' ');
        inputManager.keyPressed(event);

        assertEquals(Constants.LEFT_DIRECTION, inputManager.getHorizontalDirection());
    }

    @Test
    void testPressingRightKeySetsPressingRightFlag() {
        final KeyEvent event = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), KEY_EVENT_MODIFIER, KeyEvent.VK_RIGHT, ' ');
        inputManager.keyPressed(event);

        assertEquals(Constants.RIGHT_DIRECTION, inputManager.getHorizontalDirection());
    }

    @Test
    void testReleasingLeftKeyResetsPressingLeftFlag() {
        final KeyEvent pressEvent = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), KEY_EVENT_MODIFIER, KeyEvent.VK_LEFT, ' ');
        final KeyEvent releaseEvent = new KeyEvent(testComponent, KeyEvent.KEY_RELEASED,
                System.currentTimeMillis(), KEY_EVENT_MODIFIER, KeyEvent.VK_LEFT, ' ');

        inputManager.keyPressed(pressEvent);
        inputManager.keyReleased(releaseEvent);

        assertEquals(Constants.NULL_DIRECTION, inputManager.getHorizontalDirection());
    }

    @Test
    void testSimultaneousLeftAndRightKeyPressResultsInNullDirection() {
        final KeyEvent leftEvent = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), KEY_EVENT_MODIFIER, KeyEvent.VK_LEFT, ' ');
        final KeyEvent rightEvent = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), KEY_EVENT_MODIFIER, KeyEvent.VK_RIGHT, ' ');

        inputManager.keyPressed(leftEvent);
        inputManager.keyPressed(rightEvent);

        assertEquals(Constants.NULL_DIRECTION, inputManager.getHorizontalDirection());
    }

    @Test
    void testActionQueueWhenEnterKeyIsPressed() {
        final KeyEvent event = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), KEY_EVENT_MODIFIER, KeyEvent.VK_ENTER, ' ');
        inputManager.keyPressed(event);

        final GameAction action = inputManager.getAction();
        assertEquals(GameAction.CONFIRM_SELECTION, action);
    }

    @Test
    void testActionQueueWhenUpKeyIsPressed() {
        final KeyEvent event = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), KEY_EVENT_MODIFIER, KeyEvent.VK_UP, ' ');
        inputManager.keyPressed(event);

        final GameAction action = inputManager.getAction();
        assertEquals(GameAction.MOVE_MENU_UP, action);
    }

    @Test
    void testActionQueueWhenDownKeyIsPressed() {
        final KeyEvent event = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), KEY_EVENT_MODIFIER, KeyEvent.VK_DOWN, ' ');
        inputManager.keyPressed(event);

        final GameAction action = inputManager.getAction();
        assertEquals(GameAction.MOVE_MENU_DOWN, action);
    }

    @Test
    void testActionQueueWhenEscapeKeyIsPressed() {

        final KeyEvent event = new KeyEvent(testComponent, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), KEY_EVENT_MODIFIER, KeyEvent.VK_ESCAPE, ' ');
        inputManager.keyPressed(event);

        final GameAction action = inputManager.getAction();
        assertEquals(GameAction.PAUSE_GAME, action);
    }
}
