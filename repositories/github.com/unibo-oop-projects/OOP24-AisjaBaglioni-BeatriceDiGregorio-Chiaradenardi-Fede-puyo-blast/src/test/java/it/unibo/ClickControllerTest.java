package it.unibo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.*;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.MouseEvent;

import it.unibo.controller.ClickController;
import it.unibo.model.Point2DI;
import it.unibo.view.interfaces.ClickInterface;

class ClickControllerTest {
    ClickController clickController;

    @BeforeEach
    void setUp() {
        clickController = new ClickController(new HashSet<>());
    }

    @Test
    void testUselessMouseAction() {
        clickController.mousePressed(null);
        clickController.mouseReleased(null);
        clickController.mouseEntered(null);
        clickController.mouseExited(null);
    }

    class Clickable implements ClickInterface {
        private boolean clicked = false;
        private boolean isClickable;

        public Clickable(boolean isClickable) {
            this.isClickable = isClickable;
        }

        public boolean hasBeenClicked() {
            return clicked;
        }

        public boolean isClicked(Point2DI p) {
            return isClickable;
        }

        public void doAction() {
            clicked = true;
        }
    }

    @Test
    void testClick() {
        Clickable alwaysClicked = new Clickable(true);
        Clickable neverClicked = new Clickable(false);
        clickController.addClickable(alwaysClicked);
        clickController.addClickable(neverClicked);
        MouseEvent e = mock(MouseEvent.class);
        when(e.getX()).thenReturn(0);
        when(e.getY()).thenReturn(0);
        clickController.mouseClicked(e);
        assertTrue(alwaysClicked.hasBeenClicked());
        assertFalse(neverClicked.hasBeenClicked());
    }
}
