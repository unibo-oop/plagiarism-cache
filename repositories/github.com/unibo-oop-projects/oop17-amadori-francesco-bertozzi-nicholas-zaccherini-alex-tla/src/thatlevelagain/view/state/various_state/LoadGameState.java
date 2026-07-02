
package thatlevelagain.view.state.various_state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

import thatlevelagain.ScreenSize;
import thatlevelagain.sound.SoundManager;
import thatlevelagain.sound.SoundPath;
import thatlevelagain.view.TextFileFilter;
import thatlevelagain.view.backgrounds.various_background.BackgroundLoadGame;
import thatlevelagain.view.hints.level.Hint2;
import thatlevelagain.view.map.level.Map2;
import thatlevelagain.view.state.GameStateImpl;
import thatlevelagain.view.state.GameStateManagerImpl;

/**
 * 
 * state for load player's save's file.
 *
 */
public class LoadGameState extends GameStateImpl {

    private static final int DISTANCE = (int) ScreenSize.getScala().getHeight() * 3;
    private static final int DIMFONT = (int) ScreenSize.getScala().getHeight();
    private static final String PERCORSO = System.getProperty("user.dir") + "/ThatLevelAgain_Saved";
    private final BackgroundLoadGame background;
    private final JFileChooser chooser;
    private File file;
    private final String[] istruzioni = {"Select your save by clicking on your corrispondent txt file", 
            "After selecting it, click on open button",
            "When cancel option is choosen (or close), you will be redirected to the menu"};
    private final Font font;
    /**
     * contructor.
     * @param manager
     *         manager to set.
     */
    public LoadGameState(final GameStateManagerImpl manager) {
        super();
        this.setManager(manager);
        this.font = new Font("Helvetica", Font.BOLD, DIMFONT);
        this.chooser = new JFileChooser(PERCORSO);
        chooser.setFileFilter(new TextFileFilter());
        background = new BackgroundLoadGame();
    }

    @Override
    public final void init() throws IOException {
        final int n = this.chooser.showOpenDialog(getManager().getGamePanel());
        if (n == JFileChooser.CANCEL_OPTION) {
            this.getManager().setState(GameStateManagerImpl.MENU);
        }
        if (n == JFileChooser.APPROVE_OPTION) {
            int state;
            this.file = chooser.getSelectedFile();
            try (FileReader filereader = new FileReader(this.file);
                      BufferedReader read = new BufferedReader(filereader)) {
                state = Integer.parseInt(read.readLine());
            }
            if (state == GameStateManagerImpl.START) {
                this.getManager().setState(GameStateManagerImpl.PAUSE_LEVEL);
                try {
                    Thread.sleep(GameStateManagerImpl.TIMEWAIT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.getManager().setState(GameStateManagerImpl.START);
            } else {
                this.creaLivello(state);
            }
        }
    }

    @Override
    public final void draw(final Graphics2D g) {
        background.draw(g);
        g.setColor(Color.RED);
        g.setFont(font);
        for (int i = 0; i < istruzioni.length; i++) {
            g.drawString(istruzioni[i], DISTANCE, DISTANCE / 2 + i * DISTANCE);
        }
    }

    /**
     * 
     * @param state
     *         state to load
     * @throws IOException 
     */
    public void creaLivello(final int state) throws IOException {
        this.getManager().getStates().remove(GameStateManagerImpl.START);
        final TransitState level = new TransitState(this.getManager(), new Map2(this.getManager()), new Hint2(), state);
        this.getManager().getStates().add(GameStateManagerImpl.START, level);
        level.creaLivello();
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
