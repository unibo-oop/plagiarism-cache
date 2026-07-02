package thatlevelagain.view.state;

import java.awt.Graphics2D;
import java.io.IOException;

/**
 * 
 * GameState interface.
 *
 */
public interface GameState {

    /**
     * abstract method that initialize the state.
     * @throws IOException 
     * 
     */
    void init() throws IOException;
    /**
     * abstract method that draw the state.
     * @param g
     *   g or drw image
     */
    void draw(Graphics2D g);
    /**
     * abstract method that update the state.
     * 
     */
    void update();
    /**
     * abstract method for keyboard.
     * @param k
     *   key code
     * @throws IOException 
     */
    void keyPressed(int k) throws IOException;
    /**
     * abstract method for keyboard.
     * @param k
     *   key code
     */
    void keyReleased(int k);

    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    void mouseClicked(int x, int y);
    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    void mouseEntered(int x, int y);
    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    void mouseExited(int x, int y);
    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    void mousePressed(int x, int y);
    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    void mouseReleased(int x, int y);

    /**
     * 
     * getManager.
     * @return
     *   Manager
     */
    GameStateManagerImpl getManager();
    /**
     * setManager.
     * 
     * @param manager
     *   manager to set
     */
    void setManager(GameStateManagerImpl manager);
}
