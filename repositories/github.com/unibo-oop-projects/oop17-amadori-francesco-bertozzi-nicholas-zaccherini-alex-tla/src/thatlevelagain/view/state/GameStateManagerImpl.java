package thatlevelagain.view.state;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import thatlevelagain.view.panel.GamePanel;
import thatlevelagain.view.state.various_state.HelpState;
import thatlevelagain.view.state.various_state.LoadGameState;
import thatlevelagain.view.state.various_state.MenuState;
import thatlevelagain.view.state.various_state.PauseState;
import thatlevelagain.view.state.various_state.level.Level1;

/**
 * 
 * StateManager.
 *
 */

public class GameStateManagerImpl implements GameStateManager {

    /**
     * Index of Menu.
     */
    public static final int MENU = 0;
    /**
     * 
     * Index of Start.
     */
    public static final int START = 1;
    /**
     * Index of LoadGame.
     */
    public static final int LOADGAME = 2;
    /**
     * 
     * Index of Help.
     */
    public static final int HELP = 3;
    /**
     * Levels index.
     */
    public static final int LEVEL2 = 5, LEVEL3 = 6, LEVEL4 = 7, LEVEL5 = 8, LEVEL6 = 9, LEVEL7 = 10, LEVEL8 = 11, 
            LEVEL9 = 12, LEVEL10 = 13, LEVEL11 = 14, LEVEL12 = 15;
    /**
     * Index of End Game.
     */
    public static final int END = 16;
    /**
     * Index of Pause state.
     */
    public static final int PAUSE_LEVEL = 4;
    /**
     * wait time between level.
     */
    public static final int TIMEWAIT = 3500;
    private static final int UNO = 1;
    private final List<GameStateImpl> states; 
    private int actualState;
    private final GamePanel gamePanel;

    /**
     * Constructor.
     * @param gamePanel 
     *         panel
     */
    public GameStateManagerImpl(final GamePanel gamePanel) {
        this.actualState = MENU;
        this.states = new ArrayList<>(); 
        this.states.add(new MenuState(this));
        this.states.add(new Level1(this));
        this.states.add(new LoadGameState(this));
        this.states.add(new HelpState(this));
        this.states.add(new PauseState(this, UNO));
        this.gamePanel = gamePanel;
    }
    /**
     * getStates.
     * 
     * @return
     *   list of states
     */
    public List<GameStateImpl> getStates() {
        return this.states;
    }

    /**
     * set the state.
     * @param state
     *   state number
     * @throws IOException 
     */
    public void setState(final int state) throws IOException {
        this.actualState = state;
        this.states.get(this.actualState).init();
    }
    /**
     * update state.
     */
    public void update() {
        this.states.get(this.actualState).update();
    }
    /**
     * 
     * draw state.
     * 
     * @param g
     *   graphics to draw
     */
    public void draw(final Graphics2D g) {
        this.states.get(this.actualState).draw(g);
    }
    /**
     * keyPressed in state.
     * @param k
     *   key code
     * @throws IOException 
     */
    public void keyPressed(final int k) throws IOException {
        this.states.get(this.actualState).keyPressed(k);
    }
    /**
     * keyPressed in state.
     * @param k
     *   key code
     */
    public void keyReleased(final int k) {
        this.states.get(this.actualState).keyReleased(k);
    }

    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    public void mouseClicked(final int x, final int y) {
        this.states.get(this.actualState).mouseClicked(x, y);
    }
    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    public void mouseEntered(final int x, final int y) {
        this.states.get(this.actualState).mouseEntered(x, y);
    }
    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    public void mouseExited(final int x, final int y) {
        this.states.get(this.actualState).mouseExited(x, y);
    }
    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    public void mousePressed(final int x, final int y) {
        this.states.get(this.actualState).mousePressed(x, y);
    }
    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    public void mouseReleased(final int x, final int y) {
        this.states.get(this.actualState).mouseReleased(x, y);
    }
    /**
     * 
     * @return
     *         panel
     */
    public GamePanel getGamePanel() {
        return this.gamePanel;
    }
}
