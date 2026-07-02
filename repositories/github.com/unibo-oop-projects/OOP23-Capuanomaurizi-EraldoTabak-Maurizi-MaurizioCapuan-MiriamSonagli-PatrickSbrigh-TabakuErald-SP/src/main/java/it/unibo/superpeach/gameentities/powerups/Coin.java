package it.unibo.superpeach.gameentities.powerups;

import java.awt.Graphics;

import it.unibo.superpeach.gameentities.blocks.BlocksHandler;

/**
 * Implementation of the COIN power up.
 * @author  Miriam Sonaglia
 */
public final class Coin extends PowerUp {

    /**
     * Powerup COIN.
     * @param x
     * @param y
     * @param w
     * @param h
     * @param s
     * @param blocksHandler
     */
    public Coin(final int x, final int y, final int w, final int h, final int s, final BlocksHandler blocksHandler) {
        super(x, y, w, h, s, blocksHandler, PowerUpType.COIN);
        setMovement(0);
        setIsFalling(false);
    }

    @Override
    public void render(final Graphics g) {
        g.drawImage(getSprites()[3], getX(), getY(), getWidth(), getHeight(), null); 
    }

    @Override
    public void tick() { }

}
