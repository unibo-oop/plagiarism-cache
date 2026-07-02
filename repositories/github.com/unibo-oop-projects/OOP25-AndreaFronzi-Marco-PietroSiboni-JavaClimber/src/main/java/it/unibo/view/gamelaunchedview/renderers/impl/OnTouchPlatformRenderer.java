package it.unibo.view.gamelaunchedview.renderers.impl;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.util.Objects;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.view.gamelaunchedview.renderers.api.EntityRenderer;
import it.unibo.view.utilities.SpriteEnum;
import it.unibo.view.utilities.SpriteManager;

/**
 * Renderer for {@link Platform} entities that have onTouch behavior.
 */
public final class OnTouchPlatformRenderer implements EntityRenderer<Platform> {

    /**
     * The {@link SpriteManager} used to get the platform sprite.
     */
    private final SpriteManager spriteManager;

    /**
     * Constructor for the OnTouchPlatformRenderer.
     *
     * @param spriteManager the SpriteManager used to retrieve the platform sprite
     */
    public OnTouchPlatformRenderer(final SpriteManager spriteManager) {
        this.spriteManager = spriteManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final List<Platform> platforms, final Graphics2D g) {
        final Image sprite = this.spriteManager.get(SpriteEnum.BROKEN_PLATFORM);
        if (Objects.nonNull(sprite)) {
            platforms.forEach(platform -> {
                g.drawImage(sprite, (int) platform.getPosX(), (int) platform.getPosY(), (int) platform.getWidth(),
                        (int) platform.getHeight(), null);
            });
        }
    }
}
