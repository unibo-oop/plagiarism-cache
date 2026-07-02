package view.render;

import java.awt.Graphics;

public interface RenderLevelInterface {

    /**
     * @param g
     * 
     *          method to render floor
     * 
     */
    void renderFloor(Graphics g);

    /**
     * @param g
     * 
     *          method to render player
     * 
     */
    void renderPlayer(Graphics g);

    /**
     * @param g
     * 
     *          method to render enemies
     * 
     */
    void renderEnemies(Graphics g);

    /**
     * @param g
     * 
     *          method to render walls
     * 
     */
    void renderWalls(Graphics g);

    /**
     * @param g
     * 
     *          method to render other items
     * 
     */
    void renderOther(Graphics g);

    /**
     * general method to render.
     */
    void render();

}
