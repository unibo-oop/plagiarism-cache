package powpaw.world.controller;

import powpaw.player.model.api.Hitbox;

/**
 * Static class contains a method to check if a given Hitbox object is out of
 * the screen
 * bounds.
 * 
 * @author Giacomo Grassetti
 */
public final class ScreenController {

    /**
     * Width size of the screen.
     */
    public static final int SIZE_HD_W = 1280;
    /**
     * Height size of the screen.
     */
    public static final int SIZE_HD_H = 720;
    /**
     * Grid column size.
     */
    public static final int NUM_BLOCK_W = 30;
    /**
     * Grid row size.
     */
    public static final int NUM_BLOCK_H = 10;

    private ScreenController() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Method that checks if a given hitbox is out of the screen boundaries.
     * 
     * @param hitbox A Hitbox representing the position and size of an object in a
     *               game.
     * @return True if the given Hitbox position is out of the screen, false
     *         otherwise.
     */
    public static boolean isOutOfScreen(final Hitbox hitbox) {
        if (hitbox.getCenter().getX() >= SIZE_HD_W
                || hitbox.getCenter().getY() <= -SIZE_HD_H / 10) { // margine alto - destro
            return true;
        } else if (hitbox.getCenter().getX() <= 0 || hitbox.getCenter().getY() >= SIZE_HD_H) { // margine basso -
                                                                                               // sinistro
            return true;
        }
        return false;
    }
}
