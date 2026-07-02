package it.unibo.superpeach.gameentities.enemies;

import java.awt.Graphics;

import it.unibo.superpeach.gameentities.blocks.BlocksHandler;

/**
 * Enemy Goomba class.
 * 
 * @author Eraldo Tabaku
 */
public final class Goomba extends Enemy {

    /**
     * Constructor method for Goomba class.
     * 
     * @param x             coordinate of the goomba.
     * @param y             coordinate of the goomba.
     * @param width         of the goomba in the game.
     * @param height        of the goomba in the game.
     * @param scale         of the goomba in the game.
     * @param blocksHandler used by the goomba.
     */
    public Goomba(final int x, final int y, final int width, final int height, final int scale,
            final BlocksHandler blocksHandler) {
        super(x, y, width, height, scale, blocksHandler);
        setSpeed(scale);
        setSprites(getTexturer().getGoombaImg());
        setIsFalling(false);
    }

    @Override
    public void render(final Graphics g) {
        g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        setFalling(true);
        this.collision();
        this.updateCoords();
    }

}
