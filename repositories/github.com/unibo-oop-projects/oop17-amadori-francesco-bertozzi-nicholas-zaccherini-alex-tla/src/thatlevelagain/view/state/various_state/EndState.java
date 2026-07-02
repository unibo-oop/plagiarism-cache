package thatlevelagain.view.state.various_state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import thatlevelagain.sound.SoundManager;
import thatlevelagain.sound.SoundPath;
import thatlevelagain.view.backgrounds.various_background.BackgroundEnd;
import thatlevelagain.view.panel.GamePanel;
import thatlevelagain.view.state.GameStateImpl;
import thatlevelagain.view.state.GameStateManagerImpl;

/**
 * 
 * Class that contains end state.
 *
 */
public class EndState extends GameStateImpl {

    private final BackgroundEnd background;
    private static final int DISTANCE = GamePanel.BLOCK_HEIGHT * 3;
    private static final int DIMFONT = (int) (GamePanel.BLOCK_HEIGHT / 2 * 3);
    private static final String[] END = {"CONGRATULATIONS!!!!!!", "You have concluded That Level Again!!", "Press \"ESC\" key to exit this screen", "and close the game."};
    private final Font font;

    /**
     * 
     * @param manager
     *   manager.
     */
    public EndState(final GameStateManagerImpl manager) {
        super();
        this.setManager(manager);
        this.background = new BackgroundEnd();
        font = new Font("Helvetica", Font.BOLD, DIMFONT);
    }

    @Override
    public void init() { }

    @Override
    public final void draw(final Graphics2D g) { 
        background.draw(g);
        g.setColor(Color.BLUE);
        g.setFont(font);
        for (int i = 0; i < END.length; i++) {
            g.drawString(END[i], DISTANCE, DISTANCE + i * DISTANCE);
        }
    }

    @Override
    public void update() { }

    @Override
    public final void keyPressed(final int k) {
        if (k == KeyEvent.VK_ESCAPE) {
            SoundManager.getListLoader().get(SoundPath.CHOOSEPATH.getPosition()).play();
            this.getManager().getGamePanel().getGameFrame().dispose();
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
