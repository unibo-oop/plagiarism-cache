package frogger.model.interfaces;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Represents a generic button in the game, providing methods for updating its state,
 * handling mouse interactions, and applying game-specific actions.
 * Implementations should define how the button is rendered and how it interacts with the game state.
 */
public interface Button {

    /**
     * Updates the button's appearance.
     */
    void update();

    /**
     * Returns the current image representing the button.
     *
     * @return the current button image
     */
    BufferedImage getCurrentImg();

    /**
     * Returns the x-coordinate of the button's position.
     *
     * @return the x position
     */
    int getXPos();

    /**
     * Returns the y-coordinate of the button's position.
     *
     * @return the y position
     */
    int getYPos();

    /**
     * Returns whether the button is currently hovered by the mouse.
     *
     * @return true if the mouse is over the button, false otherwise
     */
    boolean isMouseOver();

    /**
     * Sets whether the button is currently hovered by the mouse.
     *
     * @param mouseOver true if the mouse is over the button, false otherwise
     */
    void setMouseOver(boolean mouseOver);

    /**
     * Returns whether the button is currently pressed by the mouse.
     *
     * @return true if the button is pressed, false otherwise
     */
    boolean isMousePressed();

    /**
     * Sets whether the button is currently pressed by the mouse.
     *
     * @param mousePressed true if the button is pressed, false otherwise
     */
    void setMousePressed(boolean mousePressed);

    /**
     * Returns the bounds of the button for collision detection.
     *
     * @return the rectangle representing the button's bounds
     */
    Rectangle getBounds();

    /**
     * Changes the game state to the one associated with this button.
     */
    void applyGameState();

    /**
     * Resets the mouse interaction states of the button.
     * This is typically called after a click is processed to reset the button's state.
     */
    void resetBools();
}
