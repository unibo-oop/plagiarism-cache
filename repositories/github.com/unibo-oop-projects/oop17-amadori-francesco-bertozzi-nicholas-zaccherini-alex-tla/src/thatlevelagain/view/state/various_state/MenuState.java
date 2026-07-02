package thatlevelagain.view.state.various_state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import thatlevelagain.sound.SoundManager;
import thatlevelagain.sound.SoundPath;
import thatlevelagain.view.backgrounds.various_background.BackgroundMenu;
import thatlevelagain.view.panel.GamePanel;
import thatlevelagain.view.state.GameStateImpl;
import thatlevelagain.view.state.GameStateManagerImpl;

/**
 * 
 * MenuState class.
 *
 */
public class MenuState extends GameStateImpl {

    private final BackgroundMenu background;
    private static final int DIMFONT = GamePanel.BLOCK_HEIGHT * 2;
    private static final int SPACE = 6 * DIMFONT + 10;
    private static final int RGBMAX = 255;
    private static final String TITOLO = "That Level Again";
    private static final int START = 0;
    private static final int LOADGAME = 1;
    private static final int HELP = 2;
    private static final int EXIT = 3;
    /*actual choice of string*/
    private int actualChoice;
    /*list of string to draw */
    private static final String[] MENU = {"Start", "Load Game", "Help", "Exit"};
    private final Color titolo;
    private final Font fontOptions;
    private final Font fontTitolo;
    /**
     * constructor.
     * @param manager
     *   manager to set
     */
    public MenuState(final GameStateManagerImpl manager) {
        super();
        this.setManager(manager);
        actualChoice = 0;
        background = new BackgroundMenu();
        titolo = new Color(RGBMAX, 0, 0);
        fontOptions = new Font("Helvetica", Font.BOLD, DIMFONT);
        fontTitolo = new Font("Helvetica", Font.BOLD, DIMFONT * 2);
    }

    @Override
    public void init() { }

    @Override
    public final void draw(final Graphics2D g) {
        background.draw(g);
        g.setColor(titolo);
        g.setFont(fontTitolo);
        FontMetrics fm = g.getFontMetrics();
        g.drawString(TITOLO, (GamePanel.WIDTH - fm.stringWidth(TITOLO)) / 2, DIMFONT * 2);
        g.setFont(fontOptions);
        fm = g.getFontMetrics();
        for (int i = 0; i < MenuState.MENU.length; i++) {
            if (i == this.actualChoice) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(MenuState.MENU[i], (GamePanel.WIDTH - fm.stringWidth(MenuState.MENU[i])) / 2, SPACE + i * DIMFONT);
        }
    }

    @Override
    public final void update() { }

    @Override
    public final void keyPressed(final int k) throws IOException {
        if (k == KeyEvent.VK_ENTER) {
            SoundManager.getListLoader().get(SoundPath.CHOOSEPATH.getPosition()).play();
            select();
        }
        if (k == KeyEvent.VK_UP) {
            this.actualChoice--;
            SoundManager.getListLoader().get(SoundPath.SELECTPATH.getPosition()).play();
            if (this.actualChoice <= -1) {
                this.actualChoice = MENU.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            this.actualChoice++;
            SoundManager.getListLoader().get(SoundPath.SELECTPATH.getPosition()).play();
            if (this.actualChoice >=  MENU.length) {
                this.actualChoice = 0;
            }
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

    private void select() throws IOException {
        if (this.actualChoice == START) {
            this.getManager().setState(GameStateManagerImpl.PAUSE_LEVEL);
            try {
                Thread.sleep(GameStateManagerImpl.TIMEWAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.getManager().setState(GameStateManagerImpl.START);
        }
        if (this.actualChoice == LOADGAME) {
            this.getManager().setState(GameStateManagerImpl.LOADGAME);
        }
        if (this.actualChoice == HELP) {
            this.getManager().setState(GameStateManagerImpl.HELP);
        }
        if (this.actualChoice == EXIT) {
            this.getManager().getGamePanel().getGameFrame().dispose();
        }
    }

}
