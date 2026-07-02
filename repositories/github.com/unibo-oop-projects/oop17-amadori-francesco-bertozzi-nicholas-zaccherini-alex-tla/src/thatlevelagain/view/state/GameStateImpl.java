package thatlevelagain.view.state;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import thatlevelagain.view.hints.HintImpl;

/**
 * 
 * abstract class for GameState.
 *
 */
public abstract class GameStateImpl implements GameState {
    private File file;
    private int levelIndex;
    private HintImpl hint;
    /**
     * GameStateManager.
     * 
     */
    private GameStateManagerImpl manager;

    /**
     * abstract method that initialize the state.
     * @throws IOException 
     * 
     */
    public abstract void init() throws IOException;
    /**
     * abstract method that draw the state.
     * @param g
     *   g or draw image
     */
    public abstract void draw(Graphics2D g);
    /**
     * abstract method that update the state.
     * 
     */
    public abstract void update();
    /**
     * abstract method for keyboard.
     * @param k
     *   key code
     * @throws IOException 
     */
    public abstract void keyPressed(int k) throws IOException;
    /**
     * abstract method for keyboard.
     * @param k
     *   key code
     */
    public abstract void keyReleased(int k);

    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    public abstract void mouseClicked(int x, int y);
    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    public abstract void mouseEntered(int x, int y);
    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    public abstract void mouseExited(int x, int y);
    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    public abstract void mousePressed(int x, int y);
    /**
     * 
     * @param x
     *         mouse x.
     * @param y
     *         mouse y.
     */
    public abstract void mouseReleased(int x, int y);

    /**
     * 
     * getManager.
     * @return
     *   Manager
     */
    public GameStateManagerImpl getManager() {
        return manager;
    }
    /**
     * setManager.
     * 
     * @param manager
     *   manager to set
     */
    public void setManager(final GameStateManagerImpl manager) {
        this.manager = manager;
    }
    /**
     * 
     * @param file 
     *         file for get changes value.
     */
    public void setFile(final File file) {
        this.file = file;
    }
    /**
     * @return
     *         file
     */
    public File getFile() {
        return this.file;
    }
    /**
     * 
     * @return
     *         levelIndex
     */
    public int getLevelIndex() {
        return levelIndex;
    }
    /**
     * 
     * @param levelIndex
     *         levelIndex
     */
    public void setLevelIndex(final int levelIndex) {
        this.levelIndex = levelIndex;
    }
    /**
     * 
     * @param hint 
     *          hint to set
     */
    public void setHint(final HintImpl hint) {
        this.hint = hint;
    }
    /**
     * 
     * @return
     *         level hint
     */
    public HintImpl getHint() { 
        return this.hint;
    }
}
