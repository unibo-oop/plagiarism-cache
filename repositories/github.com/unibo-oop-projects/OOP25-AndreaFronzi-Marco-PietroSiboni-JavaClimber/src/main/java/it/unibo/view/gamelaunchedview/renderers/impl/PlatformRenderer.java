package it.unibo.view.gamelaunchedview.renderers.impl;

import it.unibo.view.gamelaunchedview.renderers.api.EntityRenderer;
import it.unibo.view.utilities.SpriteEnum;
import it.unibo.view.utilities.SpriteManager;
import it.unibo.model.gameobj.api.Platform;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Objects;
import java.util.List;

/**
 * Renderer for the {@link Platform} in the game.
 */
public class PlatformRenderer implements EntityRenderer<Platform> {

    /**
     * The {@link SpriteManager} used to get the platform sprite.
     */
    private final SpriteManager spriteManager;

    /**
     * Constructor for the PlatformRenderer.
     *
     * @param spriteManager the SpriteManager used to retrieve the platform sprite
     */
    public PlatformRenderer(final SpriteManager spriteManager) {
        this.spriteManager = spriteManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final List<Platform> platforms, final Graphics2D g) {
        final Image sprite = spriteManager.get(SpriteEnum.GREEN_PLATFORM);
        if (!Objects.isNull(sprite)) {
            platforms.forEach(platform -> {
                g.drawImage(sprite,
                        (int) platform.getPosX(),
                        (int) platform.getPosY(),
                        (int) platform.getWidth(),
                        (int) platform.getHeight(),
                        null);
            });
        }
    }
}
