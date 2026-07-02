package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.entities.platforms.Platform;

import java.awt.Graphics2D;

/**
 * Interface for platform renderers, to be used in the renderer manager.
 */
public interface PlatformRenderer {

    /**
     * Draws a platform.
     *
     * @param g2            the Graphics2D context
     * @param platform      the Platform to draw
     * @param cameraOffsetY the vertical offset
     */
    void drawPlatform(Graphics2D g2, Platform platform, float cameraOffsetY);

}
