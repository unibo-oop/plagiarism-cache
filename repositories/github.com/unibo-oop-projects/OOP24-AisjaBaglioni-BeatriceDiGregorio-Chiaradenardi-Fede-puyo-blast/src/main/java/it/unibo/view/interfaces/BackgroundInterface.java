package it.unibo.view.interfaces;

import java.awt.Graphics;

/**
 * The {@code BackgroundInterface} represents an interface for drawable background images.
 * It defines a method for rendering a background image with specified dimensions.
 */
public interface BackgroundInterface {
    
    /**
     * Draws the background image scaled to fit the specified dimensions.
     *
     * @param g the {@code Graphics} object used to draw the image.
     * @param width the width to which the image should be scaled.
     * @param height the height to which the image should be scaled.
     */
    void draw(Graphics g, int width, int height);
}
