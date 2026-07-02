package it.unibo.geometrybash.view.renderer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import it.unibo.geometrybash.commons.dtos.PowerUpDto;
import it.unibo.geometrybash.view.core.Camera2D;
import it.unibo.geometrybash.view.core.RenderContext;
import it.unibo.geometrybash.view.core.SpriteRegistry;

/**
 * Handles the rendering off all power-ups that exits in the game's world.
 */
public final class PowerUpView implements Drawable<List<PowerUpDto>> {

    private final SpriteRegistry spriteRegistry;

    /**
     * Creates a new {@code PowerUpView}.
     *
     * @param spriteRegistry the registry to retrieve power-ups sprites.
     */
    public PowerUpView(final SpriteRegistry spriteRegistry) {
        this.spriteRegistry = spriteRegistry;
    }

    /**
     * Draws all the power-ups in the list on the given graphics context.
     *
     * @param g2d           the graphics context
     * @param renderContext the rendering context which gives the view port and
     *                      camera
     * @param powerups     the list of power-ups ro render
     */
    @Override
    public void draw(final Graphics2D g2d, final RenderContext renderContext, final List<PowerUpDto> powerups) {
        final Camera2D camera = renderContext.camera();
        for (final PowerUpDto powerUp : powerups) {
            if (powerUp.isActive()) {
                final BufferedImage sprite = spriteRegistry.powerUpSprite(powerUp.type());
                final int x = camera.xToPx(powerUp.x());
                final int y = camera.yToPx(powerUp.y() + powerUp.height());
                final int width = camera.sizeToPx(powerUp.width());
                final int height = camera.sizeToPx(powerUp.height());

                g2d.drawImage(sprite, x, y, width, height, null);
            }
        }
    }

}
