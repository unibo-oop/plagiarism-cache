package it.unibo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.awt.Graphics;

import it.unibo.controller.PauseController;
import it.unibo.model.PauseModel;
import it.unibo.model.Point2DI;
import it.unibo.model.Rectangle;
import it.unibo.model.Scale;
import it.unibo.view.PauseView;

/**
 * Test class for {@link PauseView}.
 */
public class PauseViewTest {
    
    private PauseModel model;
    private PauseController controller;
    private PauseView pauseView;
    private Scale scale;

    @BeforeEach
    void setUp() {
        scale = new Scale(500);
        model = new PauseModel();
        controller = Mockito.mock(PauseController.class);
        pauseView = new PauseView(scale, model, controller);
    }

    /**
     * Tests if the pause button area is correctly calculated.
     */
    @Test
    void testGetArea() {
        Rectangle area = pauseView.getArea();
        assertNotNull(area, "The button area should not be null");
        assertTrue(area.upleft.x() >= 0 && area.upleft.y() >= 0, "Upper-left coordinates should be positive");
        assertTrue(area.lowright.x() > area.upleft.x() && area.lowright.y() > area.upleft.y(), "Lower-right coordinates should be greater than upper-left");
    }

    /**
     * Tests if clicking inside the pause button area is detected correctly.
     */
    @Test
    void testIsClicked() {
        Rectangle area = pauseView.getArea();
        Point2DI insidePoint = new Point2DI(area.upleft.x() + 5, area.upleft.y() + 5);
        assertTrue(pauseView.isClicked(insidePoint), "Click inside the button area should be detected");
        
        Point2DI outsidePoint = new Point2DI(0, 0);
        assertFalse(pauseView.isClicked(outsidePoint), "Click outside the button area should not be detected");
    }

    /**
     * Tests if clicking the button triggers the pause toggle.
     */
    @Test
    void testDoAction() {
        pauseView.doAction();
        Mockito.verify(controller, Mockito.times(1)).togglePause();
    }

    /**
     * Tests if the draw method executes without exceptions.
     */
    @Test
    void testDraw() {
        Graphics mockGraphics = Mockito.mock(Graphics.class);
        assertDoesNotThrow(() -> pauseView.draw(mockGraphics), "Drawing the pause button should not throw an exception");
    }
}
