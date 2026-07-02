package it.unibo.view.gamelaunchedview.renderers.impl;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.util.Objects;

import it.unibo.view.gamelaunchedview.renderers.api.EntityRenderer;
import it.unibo.view.utilities.SpriteEnum;
import it.unibo.view.utilities.SpriteManager;
import it.unibo.model.gameobj.api.Coin;

/**
 * Renderer for {@link Coin} entities.
 */
public final class CoinRender implements EntityRenderer<Coin> {

    /**
     * The {@link SpriteManager} used to get the coin sprite.
     */
    private final SpriteManager spriteManager;

    /**
     * Constructor for CoinRender.
     * 
     * @param spriteManager the sprite manager to use
     */
    public CoinRender(final SpriteManager spriteManager) {
        this.spriteManager = spriteManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final List<Coin> coins, final Graphics2D g) {
        final Image sprite = this.spriteManager.get(SpriteEnum.COIN);
        if (Objects.nonNull(sprite)) {
            for (final Coin coin : coins) {
                g.drawImage(sprite, (int) coin.getPosX(), (int) coin.getPosY(), (int) coin.getWidth(),
                        (int) coin.getHeight(), null);
            }
        }
    }

}
