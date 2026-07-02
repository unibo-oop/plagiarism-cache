package it.unibo.superpeach.gameentities.powerups;

import java.awt.Graphics;

import it.unibo.superpeach.gameentities.blocks.BlocksHandler;

/**
 * Implementation of the LIFE MUSHROOM power up.
 * @author  Miriam Sonaglia
 */
public final class LifeMushroom extends PowerUp {

    /**
     * Powerup LIFE MUSHROOM, it gives the player 1 more life.
     * @param x
     * @param y
     * @param w
     * @param h
     * @param s
     * @param blocksHandler
     * @author Miriam Sonaglia
     */
    public LifeMushroom(final int x, final int y, final int w, final int h, final int s, final BlocksHandler blocksHandler) {
        super(x, y, w, h, s, blocksHandler, PowerUpType.LIFE_MUSHROOM);
        setMovement(2);
        setIsFalling(true);
    }

    @Override
    public void render(final Graphics g) {
        g.drawImage(getSprites()[1], getX(), getY(), getWidth(), getHeight(), null); 
    }

    @Override
    public void tick() {
        setIsFalling(true);
        collisions();
        updateCoords();
    }
}
