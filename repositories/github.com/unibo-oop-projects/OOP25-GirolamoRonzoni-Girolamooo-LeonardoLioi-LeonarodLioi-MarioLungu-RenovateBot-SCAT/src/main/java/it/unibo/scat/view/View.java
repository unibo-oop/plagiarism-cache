package it.unibo.scat.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.EntityState;
import it.unibo.scat.common.GameRecord;
import it.unibo.scat.common.GameState;
import it.unibo.scat.common.Observer;
import it.unibo.scat.control.api.ControlInterface;
import it.unibo.scat.model.api.ModelState;
import it.unibo.scat.view.api.ViewActionsInterface;
import it.unibo.scat.view.api.ViewInterface;
import it.unibo.scat.view.game.GameKL;
import it.unibo.scat.view.game.GamePanel;
import it.unibo.scat.view.menu.MenuPanel;
import it.unibo.scat.view.util.Audio;
import it.unibo.scat.view.util.AudioManager;
import it.unibo.scat.view.util.AudioTrack;

/**
 * The main class for the "View" section of the MVC pattern.
 */
public final class View implements ViewInterface, ViewActionsInterface, Observer {
    private final Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getMaximumWindowBounds();
    private ControlInterface controlInterface;
    private ModelState modelState;
    private JFrame frame;

    private GamePanel gamePanel;
    private Audio backgroundSound;
    private Audio sfxSound;
    private int chosenShipIndex = -1;
    private int lastPlayerHealth = -1;

    @Override
    public void initEverything() {
        backgroundSound = new AudioManager();
        sfxSound = new AudioManager();

        gamePanel = new GamePanel(this);
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(new GameKL(controlInterface, gamePanel));

        initFrame();
        showMenuPanel();
    }

    @Override
    public void update() {
        final int currentPlayerHealth = modelState.getPlayerHealth();
        final GameState currentState = getGameState();

        if (currentState == GameState.RUNNING) {
            if (lastPlayerHealth != -1 && currentPlayerHealth < lastPlayerHealth) {
                sfxSound.play(AudioTrack.HIT, false);
            }
            lastPlayerHealth = currentPlayerHealth;
        }

        if (currentState == GameState.GAMEOVER) {
            lastPlayerHealth = -1;
            backgroundSound.stop();
            backgroundSound.play(AudioTrack.GAME_OVER_SOUND, false);
        }

        gamePanel.update();

    }

    /**
     * Initializes the frame.
     */
    private void initFrame() {
        frame = new JFrame();
        frame.setTitle("SCAT🚀👾");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.getContentPane().setLayout(new CardLayout());

        frame.pack();
        final java.awt.Insets ins = frame.getInsets();

        final Dimension best = gamePanel.computeBestFrameSize(bounds, ins);
        frame.setSize(best);

        frame.setLocation(
                bounds.x + (bounds.width - best.width) / 2,
                bounds.y + (bounds.height - best.height) / 2);

        frame.setVisible(true);
    }

    /**
     * Setter for the model state.
     * 
     * @param mObservable the model state.
     */
    public void setModelState(final ModelState mObservable) {
        this.modelState = mObservable;
    }

    /**
     * Setter for the control interface.
     * 
     * @param cInterface the control interface.
     * 
     */
    public void setControlInterface(final ControlInterface cInterface) {
        this.controlInterface = cInterface;
    }

    @Override
    public void closeFrame() {
        this.frame.dispose();
    }

    @Override
    public List<EntityState> fetchEntitiesFromModel() {
        return modelState.getEntities();
    }

    @Override
    public List<GameRecord> fetchLeaderboard() {
        return modelState.getLeaderboard();
    }

    @Override
    public int fetchScore() {
        return modelState.getScore();
    }

    @Override
    public String fetchUsername() {
        return modelState.getUsername();
    }

    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Intentional exposure is intended.")
    @Override
    public JFrame getFrame() {
        return this.frame;
    }

    @Override
    public void pauseGame() {
        backgroundSound.stop();
        controlInterface.notifyPauseGame();
    }

    @Override
    public void quitGame() {
        controlInterface.notifyQuitGame();
    }

    @Override
    public void resetGame() {
        backgroundSound.play(AudioTrack.GAME_THEME, true);
        controlInterface.notifyResetGame();
    }

    @Override
    public void resumeGame() {
        backgroundSound.play(AudioTrack.GAME_THEME, true);
        controlInterface.notifyResumeGame();
    }

    @Override
    public void setUsername(final String username) {
        controlInterface.notifySetUsername(username);
    }

    @Override
    public int fetchPlayerHealth() {
        return modelState.getPlayerHealth();
    }

    @Override
    public void showGamePanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        SwingUtilities.invokeLater(gamePanel::requestFocusInWindow);
        backgroundSound.stop();
    }

    @Override
    public void showMenuPanel() {
        final MenuPanel menuPanel = new MenuPanel(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(menuPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        lastPlayerHealth = -1;
        sfxSound.stop();
        backgroundSound.stop();
        backgroundSound.play(AudioTrack.SOUND_TRACK, true);
    }

    @Override
    public void startGame() {
        backgroundSound.play(AudioTrack.GAME_THEME, true);
        controlInterface.notifyStartGame();
    }

    @Override
    public ControlInterface getControlInterface() {
        return controlInterface;
    }

    @Override
    public int getChosenShipIndex() {
        return chosenShipIndex;
    }

    @Override
    public void setChosenShipIndex(final int index) {
        chosenShipIndex = index;
    }

    @Override
    public int getLevel() {
        return modelState.getLevel();
    }

    @Override
    public int getInvadersStepMs() {
        return modelState.getInvadersStepMs();
    }

    @Override
    public int getInvadersAccMs() {
        return modelState.getInvadersAccMs();
    }

    @Override
    public int getBonusInvaderAccMs() {
        return modelState.getBonusInvaderAccMs();
    }

    @Override
    public void abortGame() {
        controlInterface.notifyResetGame();
        backgroundSound.stop();
        sfxSound.stop();

        showMenuPanel();
    }

    @Override
    public GameState getGameState() {
        return controlInterface.getGameState();

    }
}
