package it.unibo.pacman.controller.entities;

import java.awt.Graphics;

/**
 * FunctionalInterface An interface that allows to render a view trough the
 * controller.
 */
public interface Renderable {
    /**
     * Method for render the view trough the controller.
     * 
     * @param g the graphics to render.
     */
    void render(Graphics g);
}
