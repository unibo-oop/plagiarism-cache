package test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import controller.ControllerImpl;
import controller.GameLoop;
import controller.GameLoopImpl;
import controller.KeyObserver;
import controller.event.KeyEvent;
import controller.event.KeyEventImpl;
import model.GameModel;
import model.GameModelImpl;

/**
 * 
 */
public class TestObserver {

    private static final String GAME = "GAME";

    /**
     * tests keys in different states of the game.
     */
    @Test
    public void testKeys() {
        KeyEvent ke = new KeyEventImpl("X", KeyEvent.KeyEventType.KEY_PRESSED, GAME);
        final KeyObserver ko = new KeyObserver();
        final GameModel gm = new GameModelImpl(false);
        final GameLoop gl = new GameLoopImpl(gm);

        assertEquals(0, ControllerImpl.get().getObserverMovements().size());
        ko.onNotify(ke);
        assertEquals(0, ControllerImpl.get().getObserverMovements().size());

        ke = new KeyEventImpl("W", KeyEvent.KeyEventType.KEY_PRESSED, GAME);
        ko.onNotify(ke);
        assertEquals(1, ControllerImpl.get().getObserverMovements().size());

        ke = new KeyEventImpl("W", KeyEvent.KeyEventType.KEY_RELEASED, GAME);
        ko.onNotify(ke);
        assertEquals(0, ControllerImpl.get().getObserverMovements().size());

        ke = new KeyEventImpl("W", KeyEvent.KeyEventType.KEY_RELEASED, GAME);
        ko.onNotify(ke);
        assertEquals(0, ControllerImpl.get().getObserverMovements().size());

        ke = new KeyEventImpl("ESC", KeyEvent.KeyEventType.KEY_PRESSED, GAME);
        ko.onNotify(ke);
        assertFalse(gl.isRunning());

    }
}
