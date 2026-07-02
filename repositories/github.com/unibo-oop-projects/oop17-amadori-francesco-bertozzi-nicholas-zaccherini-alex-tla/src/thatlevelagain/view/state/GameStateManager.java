package thatlevelagain.view.state;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.List;

import thatlevelagain.view.panel.GamePanel;

/**
 * GameStateManager interface.
 *
 */
public interface GameStateManager {

    /**
     * getStates.
     * 
     * @return
     *   list of states
     */
    List<GameStateImpl> getStates();

    /**
     * set the state.
     * @param state
     *   state number
     * @throws IOException 
     */
    void setState(int state) throws IOException;
    /**
     * update state.
     */
    void update();
    /**
     * 
     * draw state.
     * 
     * @param g
     *   graphics to draw
     */
    void draw(Graphics2D g);
    /**
     * keyPressed in state.
     * @param k
     *   key code
     * @throws IOException 
     */
    void keyPressed(int k) throws IOException;
    /**
     * keyPressed in state.
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
     * @return
     *         panel
     */
    GamePanel getGamePanel();
}
