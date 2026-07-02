package javawulf.view;

import java.awt.Graphics2D;

/**
 * Class used in GamePanel in order to draw the elements of the game.
 */
public interface Drawer {

    /** Draw the components referred to the implemented *Drawer class.
     * @param graphics where components are drawn
     */
    void draw(Graphics2D graphics);
}
