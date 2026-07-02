package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.entities.collectibles.Coin;

import java.awt.Graphics2D;

/**
 * Interface for Coin renderers, to be used in the RenderManager.
 */
public interface CoinRenderer {
    /**
     * Draws a single Coin(GameObject), based on its state, using designed
     * animation frames.
     *
     * @param g2        the Graphics2D context
     * @param coin      the Coin to draw
     * @param offsetY   the vertical offset
     * @param deltaTime the time passed since the last frame (used for animation)
     */
    void drawCoin(Graphics2D g2, Coin coin, float offsetY, float deltaTime);
}
