package it.unibo.geometrybash.view.renderer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;

import it.unibo.geometrybash.commons.dtos.ObstacleDto;
import it.unibo.geometrybash.view.core.Camera2D;
import it.unibo.geometrybash.view.core.SpriteRegistry;
import it.unibo.geometrybash.view.core.RenderContext;

/**
 * Handles the rendering off all obstacles that exits in the game's world.
 */
public final class ObstacleView implements Drawable<List<ObstacleDto>> {

    private final SpriteRegistry spriteRegistry;

    /**
     * Creates a new {@code ObstacleView}.
     *
     * @param spriteRegistry the registry to retrieve obstacle sprites.
     */
    public ObstacleView(final SpriteRegistry spriteRegistry) {
        this.spriteRegistry = Objects.requireNonNull(spriteRegistry);
    }

    /**
     * Draws all the obstacles in the list on the given graphics context.
     *
     * @param g2d the graphics context
     * @param renderContext the rendering context which gives the view port and camera
     * @param obstacles the list of obstacles ro render
     */
    @Override
    public void draw(final Graphics2D g2d, final RenderContext renderContext, final List<ObstacleDto> obstacles) {
        final Camera2D camera = renderContext.camera();

        for (final ObstacleDto obstacle : obstacles) {
            if (obstacle.isActive()) {
                final BufferedImage sprite = spriteRegistry.obstacleSprite(obstacle.type());
                final int x = camera.xToPx(obstacle.x());
                final int y = camera.yToPx(obstacle.y() + obstacle.height());
                final int width = camera.sizeToPx(obstacle.width());
                final int height = camera.sizeToPx(obstacle.height());

                g2d.drawImage(sprite, x, y, width, height, null);
            }
        }
    }

}
