package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.entities.character.Character;

import java.awt.Graphics2D;

/**
 * Interface for Player renderers, to be used in the RenderManager.
 */
public interface PlayerRenderer {
    /**
     * Draws a player.
     *
     * @param g2        the Graphics2D context
     * @param player    the Player to draw
     * @param offsetY   the vertical offset
     * @param deltaTime the time passed since the last frame (used for animation)
     */
    void drawPlayer(Graphics2D g2, Character player, float offsetY, float deltaTime);
}
