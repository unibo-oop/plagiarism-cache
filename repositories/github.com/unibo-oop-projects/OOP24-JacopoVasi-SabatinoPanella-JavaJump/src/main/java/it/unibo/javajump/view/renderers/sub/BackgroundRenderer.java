package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.GameModel;

import java.awt.Graphics2D;

/**
 * Interface for background renderers, to be used in the renderer manager.
 */
public interface BackgroundRenderer {
    /**
     * Draws the background on the screen during gameplay.
     *
     * @param g2        the Graphics2D context
     * @param model     the GameModel, used to get the camera offset
     * @param deltaTime the time passed since the last frame
     */
    void drawBackground(Graphics2D g2, GameModel model, float deltaTime);
}
