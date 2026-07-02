package frogger;

import java.awt.Rectangle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import frogger.common.GameState;
import frogger.model.implementations.MenuButtons;

class MenuButtonsTest {

    @Test
    void testGettersAndBounds() {
        final int xPos = 100;
        final int yPos = 200;
        final MenuButtons button = new MenuButtons(xPos, yPos, 1, GameState.PLAYING);
        assertEquals(xPos, button.getXPos());
        assertEquals(yPos, button.getYPos());

        final Rectangle bounds = button.getBounds();
        assertNotNull(bounds);
        assertEquals(xPos, bounds.x);
        assertEquals(yPos, bounds.y);

        // Check that getBounds returns a copy
        final Rectangle bounds2 = button.getBounds();
        assertNotSame(bounds, bounds2);
    }

    @Test
    void testMouseStates() {
        final MenuButtons button = new MenuButtons(0, 0, 0, GameState.PLAYING);

        button.setMouseOver(true);
        assertTrue(button.isMouseOver());

        button.setMousePressed(true);
        assertTrue(button.isMousePressed());

        button.resetBools();
        assertFalse(button.isMouseOver());
        assertFalse(button.isMousePressed());
    }

    @Test
    void testApplyGameState() {
        final MenuButtons button = new MenuButtons(0, 0, 0, GameState.PAUSE);
        button.applyGameState();
        assertEquals(GameState.PAUSE, GameState.getState());
    }
}
