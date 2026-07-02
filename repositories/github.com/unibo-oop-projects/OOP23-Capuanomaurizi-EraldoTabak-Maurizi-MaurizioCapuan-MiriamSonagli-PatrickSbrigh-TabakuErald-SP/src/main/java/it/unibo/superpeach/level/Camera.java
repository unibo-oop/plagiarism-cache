package it.unibo.superpeach.level;

import it.unibo.superpeach.gameentities.player.Player;

/**
 * Implementation of an object that translates the Graphics view making it move with the player.
 * 
 * @author  Maurizio Capuano
 */
public final class Camera {

    private final int gameWidth;
    private int cameraX;

    /**
     * Camera constructor.
     * @param width window width
     * @param scale game scale
     */
    public Camera(final int width, final int scale) {
        gameWidth = width * scale;
    }

    /**
     * Runtime camera update.
     * @param p played passed to get its coordinates
     */
    public void tick(final Player p) {
        cameraX = gameWidth / 2 - p.getX();
    }

    /**
     * @param x new x value
     */
    public void setCameraX(final int x) {
        this.cameraX = x;
    }

    /**
     * @return camera x value
     */
    public int getCameraX() {
        return cameraX;
    }

}
