package thatlevelagain.view.map;

import java.awt.Graphics2D;

/**
 * Map Interface.
 *
 */
public interface Map {

    /**
     * create Map parts.
     */
    void creaMappa();
    /**
     * delete no more important elements. 
     */
    void eliminaElementi();
    /**
     * draw elements in the map.
     * @param g
     *         g where draws elements.
     */
    void draw(Graphics2D g);
    /**
     * set next level.
     */
    void nextLevel();
}
