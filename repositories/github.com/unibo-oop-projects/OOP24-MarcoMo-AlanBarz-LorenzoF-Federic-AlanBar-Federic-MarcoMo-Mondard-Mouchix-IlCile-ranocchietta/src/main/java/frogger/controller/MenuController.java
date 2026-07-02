package frogger.controller;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import frogger.model.interfaces.Menu;

/**
 * Defines the behavior of a controller that manages a menu,
 * providing access to input listeners and the associated menu instance.
 */
public interface MenuController extends Controller {

    /**
     * Returns the listener for mouse movement events in the menu.
     * @return MouseMotionListener associated with the menu
     */
    MouseMotionListener getMouseMotionListener();

    /**
     * Returns the listener for mouse click events in the menu.
     * @return MouseListener associated with the menu
     */
    MouseListener getMouseListener();

    /**
     * Returns the menu currently managed by the controller.
     * @return the current menu
     */
    Menu getMenu();
}
