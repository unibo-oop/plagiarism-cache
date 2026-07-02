package it.unibo.superpeach.gameentities.enemies;

import java.awt.Graphics;

import it.unibo.superpeach.gameentities.blocks.BlocksHandler;

/**
 * Enemy Koopa Class.
 * 
 * @author Eraldo Tabaku
 */
public final class KoopaTroopa extends Enemy {

    /**
     * Constructor method for KoopaTroopa class.
     * 
     * @param x             coordinate of the enemy.
     * @param y             coordinate of the enemy.
     * @param width         sprite with of the enemy.
     * @param height        sprite height of the enemy.
     * @param scale         used in the game.
     * @param blocksHandler used by the enemy.
     */
    public KoopaTroopa(final int x, final int y, final int width, final int height, final int scale,
            final BlocksHandler blocksHandler) {
        super(x, y, width, height, scale, blocksHandler);
        setSprites(getTexturer().getKoopaImg());
        setSpeed(2 * scale);
        setFalling(false);
    }

    @Override
    public void render(final Graphics g) {
        if (isDirectionLeft()) {
            g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(),
                    null);
        } else {
            g.drawImage(getSprites()[1], getX(), getY(), getWidth(), getHeight(),
                    null);
        }
    }

    @Override
    public void tick() {
        setFalling(true);
        this.collision();
        this.updateCoords();
    }
}
