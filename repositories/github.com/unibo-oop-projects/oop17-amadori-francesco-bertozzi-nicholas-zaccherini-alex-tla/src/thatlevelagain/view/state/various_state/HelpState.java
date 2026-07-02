package thatlevelagain.view.state.various_state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import thatlevelagain.sound.SoundManager;
import thatlevelagain.sound.SoundPath;
import thatlevelagain.view.backgrounds.various_background.BackgroundHelp;
import thatlevelagain.view.panel.GamePanel;
import thatlevelagain.view.state.GameStateImpl;
import thatlevelagain.view.state.GameStateManagerImpl;

/**
 * 
 * Class that contains graphical instruction relative game's instruction.
 *
 */
public class HelpState extends GameStateImpl {

    private final BackgroundHelp background;
    private static final int DISTANCE = GamePanel.BLOCK_HEIGHT * 3;
    private static final int DIMFONT = (int) (GamePanel.BLOCK_HEIGHT / 2 * 3);
    private static final String[] HELP = {"HELP:", "1)you have got two hints for every level, press ", "the light bulb for show second hint;", "2)during the game:", "-press \"SPACE BAR\" to jump;", "-press \"D\" key to go right;",
            "-press \"A\" key to go left;", "3)press \"ESC\" key to exit this screen."};
    private static final int TITLE = 0;
    private static final int FIRSTOPTIONPARTB = 2;
    private static final int LASTOPTIONS = HELP.length - 1;
    private static final int SECONDOPTION = 3;
    private static final int FIRSTOPTION = 1;
    private final Font font;

    /**
     * 
     * @param manager
     *   manager.
     */
    public HelpState(final GameStateManagerImpl manager) {
        super();
        this.setManager(manager);
        this.background = new BackgroundHelp();
        font = new Font("Helvetica", Font.BOLD, DIMFONT);
    }

    @Override
    public void init() { }

    @Override
    public final void draw(final Graphics2D g) { 
        int interspazio = 0;
        background.draw(g);
        g.setColor(Color.BLUE);
        g.setFont(font);
        for (int i = 0; i < HELP.length; i++) {
            if (i == TITLE) {
                g.drawString(HELP[i], DISTANCE, DISTANCE);
            } else if (i == FIRSTOPTION) {
                g.drawString(HELP[i], DISTANCE, DISTANCE + i * DISTANCE);
            } else if (i == FIRSTOPTIONPARTB) {
                g.drawString(HELP[i], 2 * DISTANCE, DISTANCE + i * DISTANCE);
            } else if (i == SECONDOPTION) {
                g.drawString(HELP[i], DISTANCE, DISTANCE + i * DISTANCE);
            } else if (i == LASTOPTIONS) {
                g.drawString(HELP[i], DISTANCE, DISTANCE + i * DISTANCE - DISTANCE / 4 * interspazio);
            } else {
                interspazio++;
                g.drawString(HELP[i], 2 * DISTANCE, DISTANCE + i * DISTANCE - DISTANCE / 4 * interspazio);
            }
        }
    }

    @Override
    public void update() { }

    @Override
    public final void keyPressed(final int k) throws IOException {
        if (k == KeyEvent.VK_ESCAPE) {
            SoundManager.getListLoader().get(SoundPath.CHOOSEPATH.getPosition()).play();
            this.getManager().setState(GameStateManagerImpl.MENU);
        }
    }

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

}
