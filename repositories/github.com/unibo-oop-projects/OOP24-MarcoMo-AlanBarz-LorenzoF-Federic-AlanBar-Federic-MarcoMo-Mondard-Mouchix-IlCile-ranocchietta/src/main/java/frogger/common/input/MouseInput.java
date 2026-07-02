package frogger.common.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.controller.MenuController;

/**
 * Handles mouse input events and delegates them to the menu controller.
 * 
 * Implements both {@link MouseListener} and {@link MouseMotionListener}.
 */
public class MouseInput implements MouseMotionListener, MouseListener {

    private final MenuController controller;

     /**
     * Constructs a new {@code MouseInput} with the specified controller.
     *
     * @param controller the menu controller that will handle mouse events
     * @param <X>        the type of the menu controller
     */
    public <X extends MenuController> MouseInput(final X controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(final MouseEvent e) { }

    /**
     * Delegates the mouse pressed event to the menu.
     *
     * @param e the mouse event
     */
    @Override
    public void mousePressed(final MouseEvent e) {
        controller.getMenu().mousePressed(e);
    }

    /**
     * Delegates the mouse released event to the menu.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
        controller.getMenu().mouseReleased(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(final MouseEvent e) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(final MouseEvent e) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragged(final MouseEvent e) { }

    /**
     * Delegates the mouse moved event to the menu.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseMoved(final MouseEvent e) {
        controller.getMenu().mouseMoved(e);
    }
}
