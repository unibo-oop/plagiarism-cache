package it.unibo.superpeach.gameentities.powerups;

import java.awt.Graphics;

import it.unibo.superpeach.gameentities.blocks.BlocksHandler;

/**
 * Implementation of the RED MUSHROOM power up.
 * @author  Miriam Sonaglia
 */
public final class RedMushroom extends PowerUp {

    /**
     * Powerup RED MUSHROOM.
     * @param x
     * @param y
     * @param w
     * @param h
     * @param s
     * @param blocksHandler
     */
    public RedMushroom(final int x, final int y, final int w, final int h, final int s, final BlocksHandler blocksHandler) {
        super(x, y, w, h, s, blocksHandler, PowerUpType.RED_MUSHROOM);
        setMovement(1);
        setIsFalling(false);
    }

    @Override
    public void render(final Graphics g) {
        g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        setIsFalling(true);
        collisions();
        updateCoords();
    }

}
