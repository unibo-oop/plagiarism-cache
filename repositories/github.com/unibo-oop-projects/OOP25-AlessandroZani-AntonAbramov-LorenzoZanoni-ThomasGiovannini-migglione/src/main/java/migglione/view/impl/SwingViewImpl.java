package migglione.view.impl;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import migglione.controller.api.Controller;
import migglione.view.api.SwingView;
import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.api.scenes.SceneFactory;
import migglione.view.api.scenes.Scenes;
import migglione.view.impl.scenesimpl.Field;
import migglione.view.impl.scenesimpl.Gallery;
import migglione.view.impl.scenesimpl.SceneFactoryImpl;
import migglione.view.impl.scenesimpl.Scores;
import migglione.view.impl.scenesimpl.StartGame;

/**
 * Implementation of the SwingView interface.
 * 
 * <p>
 * As such, it adopts the Swing style and rapresent the
 * "center" of the MVC method, it initialize the elements
 * of the frame and set the different scenes using CardLayout.
 * 
 * <p>
 * The frame must not be resizable under a certain amount and
 * is expected to make the user able to navigate to the various
 * contents of the application while changing soundtracks and backgrounds.
 */
public final class SwingViewImpl implements SwingView {

    private static final Dimension MONITOR_DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int INITIAL_WIDTH = (int) (MONITOR_DIMENSION.getWidth() * 0.8);
    private static final int INITIAL_HEIGHT = (int) (MONITOR_DIMENSION.getHeight() * 0.8);
    private static final String FRAME_NAME = "Migglione: the game";
    private static final String ICON_IMAGE_PATH = "/images/utilities/backside.png";

    private final JFrame frame = new JFrame(FRAME_NAME);
    private final CardLayout cards = new CardLayout();
    private final JPanel firstPanel = new JPanel(cards);
    private final SceneFactory sceneCreator;
    private final Controller controller;
    private String currentSceneName;
    private MusicPlayer music;

    /**
     * The constructor of the class.
     * Thanks to the implementations of different scenes,
     * it has only the responsibility to change them and to set the
     * restraints of the frame, which is resizable over a certain point.
     * 
     * @param controller is the controller of the application
     */
    public SwingViewImpl(final Controller controller) {
        this.controller = controller;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setMinimumSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
        frame.setPreferredSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
        frame.setIconImage(new ImageIcon(getClass().getResource(ICON_IMAGE_PATH)).getImage());

        sceneCreator = new SceneFactoryImpl();
        firstPanel.add(sceneCreator.createScene(this, Scenes.MENU, controller), Scenes.MENU.getScene());
        firstPanel.add(sceneCreator.createScene(this, Scenes.START_GAME, controller), Scenes.START_GAME.getScene());
        firstPanel.add(sceneCreator.createScene(this, Scenes.TUTORIAL, controller), Scenes.TUTORIAL.getScene());
        firstPanel.add(sceneCreator.createScene(this, Scenes.SCORES, controller), Scenes.SCORES.getScene());
        firstPanel.add(sceneCreator.createScene(this, Scenes.GALLERY, controller), Scenes.GALLERY.getScene());
        firstPanel.add(sceneCreator.createScene(this, Scenes.CREDITS, controller), Scenes.CREDITS.getScene());

        frame.add(firstPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setScene(Scenes.MENU.getScene());
    }

    @Override
    public void setScene(final String sceneName) {

        if (sceneName.equals(Scenes.FIELD.getScene())) {
            for (final var c : firstPanel.getComponents()) {
                if (c instanceof Field) {
                    firstPanel.remove(c);
                    break;
                }
            }
            firstPanel.add(sceneCreator.createScene(this, Scenes.FIELD, controller), Scenes.FIELD.getScene());
        }

        this.cards.show(firstPanel, sceneName);
        currentSceneName = sceneName;

        for (final var c : firstPanel.getComponents()) {
            if (c.isVisible()) {
                if (c instanceof Gallery gallery) {
                    gallery.resetScrollBar();
                }
                if (c instanceof StartGame startGame) {
                    startGame.reset();
                }
                if (c instanceof Scores scores) {
                    scores.refresh();
                }
                if (c instanceof MusicProvider musicGetter) {
                    final MusicPlayer newMusic = musicGetter.getMusic();
                    if (music != null) {
                        if (!sameMusic(music, newMusic)) {
                            endMusic();
                            this.music = musicGetter.getMusic();
                            this.music.playMusic();
                        }
                    } else {
                        this.music = newMusic;
                        this.music.playMusic();
                    }
                    break;
                }
            }
        }
    }

    private boolean sameMusic(final MusicPlayer oldMusic, final MusicPlayer newMusic) {
        return newMusic.getPath().equals(oldMusic.getPath());
    }

    private void endMusic() {
        music.stopMusic();
        music = null;
    }

    @Override
    public String getSceneName() {
        return currentSceneName;
    }

    @Override
    public void quit() {
        endMusic();
        frame.dispose();
    }

    @Override
    public void endMessage(final String winner, final String player, final Integer pScore, final Integer cScore) {

        final String endMessage;
        if (winner.equals(player)) {
            endMessage = "You won with " + pScore + " points!";
        } else if (pScore.equals(cScore)) {
            endMessage = "It's a tie!";
        } else {
            endMessage = "You lost...";
        }

        JOptionPane.showMessageDialog(frame, endMessage, "The game has ended", JOptionPane.INFORMATION_MESSAGE);

        setScene(Scenes.MENU.getScene());
    }

    @Override
    public void showTutorialPrompt() {
        if (yesTutorial()) {
            setScene(Scenes.TUTORIAL.getScene());
        }
    }

    private boolean yesTutorial() {
        final int preference = JOptionPane.showConfirmDialog(
        frame,
        "Hello there! This seems the first time you open the game, would you like to see the tutorial?",
        "Tutorial",
        JOptionPane.YES_NO_OPTION
    );

        return preference == JOptionPane.YES_OPTION;
    }
}
