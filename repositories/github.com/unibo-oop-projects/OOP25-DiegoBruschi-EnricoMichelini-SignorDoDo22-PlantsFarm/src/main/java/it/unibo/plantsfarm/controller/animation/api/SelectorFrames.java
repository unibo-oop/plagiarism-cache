package it.unibo.plantsfarm.controller.animation.api;

import java.awt.Graphics2D;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;

/**
 * SelectorFrames interface.
 *
 */
public interface SelectorFrames {

    /**
     * Selecet the current Animation based on player action.
     *
     * @param input Input from user
     */
    void takeInput(UserInput input);

    /**
     * Calculate animation's next frame based on time rimaining.
     *
     * @param now Current time in milliseconds
     */
    void update(Long now);

    /**
     * Render the current frame of the animation.
     *
     * @param g2d Graphics2D object to draw on
     * @param posPlayerx X position of the player
     * @param posPlayery Y position of the player
     * @param camerax X position of the camera
     * @param cameray Y position of the camera
     */
    void render(Graphics2D g2d, double posPlayerx, double posPlayery, int camerax, int cameray);
}
