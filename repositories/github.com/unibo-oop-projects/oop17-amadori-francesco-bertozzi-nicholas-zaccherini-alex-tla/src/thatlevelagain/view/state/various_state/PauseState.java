package thatlevelagain.view.state.various_state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import thatlevelagain.view.backgrounds.various_background.BackgroundPause;
import thatlevelagain.view.panel.GamePanel;
import thatlevelagain.view.state.GameStateImpl;
import thatlevelagain.view.state.GameStateManagerImpl;

/**
 * 
 * Class that contains graphical instruction relative game's instruction.
 *
 */
public class PauseState extends GameStateImpl {

    private final BackgroundPause background;
    private static final int DIMFONT = (int) (GamePanel.BLOCK_HEIGHT * 6);
    private static final int DUE = 2;
    private static final int TRE = 3;
    private static final int END_LEVEL = 13;
    private String pause;
    private final Font font;

    /**
     * 
     * @param manager
     *   manager.
     * @param index
     *   level index
     */
    public PauseState(final GameStateManagerImpl manager, final int index) {
        super();
        pause = "LEVEL " + index;
        this.setManager(manager);
        this.background = new BackgroundPause();
        font = new Font("Helvetica", Font.BOLD, DIMFONT);
    }

    @Override
    public void init() { }

    @Override
    public final void draw(final Graphics2D g) { 
        background.draw(g);
        g.setColor(Color.WHITE);
        g.setFont(font);
        final FontMetrics fm = g.getFontMetrics();
        g.drawString(pause, (GamePanel.WIDTH - fm.stringWidth(pause)) / DUE, GamePanel.HEIGHT / TRE);
    }

    @Override
    public void update() { }

    @Override
    public final void keyPressed(final int k) { }

    @Override
    public void keyReleased(final int k) { }

    @Override
    public void mouseClicked(final int x, final int y) { }

    @Override
    public void mouseEntered(final int x, final int y) { }

    @Override
    public void mouseExited(final int x, final int y) { }

    @Override
    public void mousePressed(final int x, final int y) { }

    @Override
    public void mouseReleased(final int x, final int y) { }

    /**
     * 
     * @param index
     *         set index level
     */
    @Override
    public void setLevelIndex(final int index) {
        this.pause = "LEVEL " + index;
        if (index == END_LEVEL) {
            this.pause = "FINISH";
        }
    }
}
