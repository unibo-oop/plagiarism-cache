package it.unibo.scat.view.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.GameState;
import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.api.ViewActionsInterface;
import it.unibo.scat.view.game.api.GamePanelInterface;
import it.unibo.scat.view.game.canvas.Canvas;
import it.unibo.scat.view.game.statusbar.StatusBar;

/**
 * Panel that contains all the graphics element for the game.
 */
@SuppressFBWarnings(value = { "SE_TRANSIENT_FIELD_NOT_RESTORED",
        "EI_EXPOSE_REP2" }, justification = "Component not intended for serialization;Reference intentionally shared")
public final class GamePanel extends JPanel implements GamePanelInterface {
    private static final long serialVersionUID = 1L;
    private final transient ViewActionsInterface viewInterface;
    private transient List<BufferedImage> backgrounds;

    private Canvas canvas;
    private StatusBar statusBar;
    private int currentBackgroundIndex;

    private JDialog pauseDialog;
    private JDialog gameOverDialog;

    /**
     * Game panel constructor, initializes backgrounds, canvas, game over panel and
     * the status bar.
     * 
     * @param viewInterface the menu actions interface.
     */
    public GamePanel(final ViewActionsInterface viewInterface) {
        this.viewInterface = viewInterface;

        setLayout(new BorderLayout());

        initBackgrounds();
        initCanvas();
        initStatusBar();

    }

    /**
     * Initializes the canvas.
     */
    private void initCanvas() {
        canvas = new Canvas(viewInterface);
        canvas.setOpaque(false);
        canvas.setFocusable(true);

        add(canvas, BorderLayout.CENTER);
    }

    /**
     * Initializes the status bar.
     */
    private void initStatusBar() {
        final int height = 70;
        statusBar = new StatusBar(this);
        statusBar.setPreferredSize(new Dimension(0, height));
        statusBar.setOpaque(false);
        add(statusBar, BorderLayout.NORTH);
    }

    /**
     * Calculates and returns the dimension of the optimal frame size.
     * 
     * @param maxWindowBounds the maximum window bounds.
     * @param ins             the insets.
     * @return the optimal dimension.
     */
    public Dimension computeBestFrameSize(final Rectangle maxWindowBounds, final Insets ins) {
        final double aspect = 59.0 / 36.0;

        final int wMax = maxWindowBounds.width - ins.left - ins.right;
        final int hMax = maxWindowBounds.height - ins.top - ins.bottom;

        final int barH = statusBar.getPreferredSize().height;

        final int canvasH = Math.min(hMax - barH, (int) Math.floor(wMax / aspect));
        final int canvasW = (int) Math.floor(canvasH * aspect);

        return new Dimension(
                canvasW + ins.left + ins.right,
                canvasH + barH + ins.top + ins.bottom);
    }

    @Override
    public void pause() {
        viewInterface.pauseGame();
        showPausePanel();
    }

    @Override
    public void resume() {
        viewInterface.resumeGame();
        pauseDialog.dispose();
    }

    /**
     * Initializes the backgrounds.
     */
    private void initBackgrounds() {
        backgrounds = new ArrayList<>();

        try {
            for (int i = 0; i < UIConstants.GAME_BACKGROUNDS_PATHS.size(); i++) {
                final BufferedImage tmp = ImageIO.read(
                        Objects.requireNonNull(getClass().getResource(UIConstants.GAME_BACKGROUNDS_PATHS.get(i))));

                backgrounds.add(tmp);
            }

        } catch (final IOException e) {
            throw new IllegalStateException("Cannot load game background", e);
        }
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final BufferedImage currentBg = backgrounds.get(currentBackgroundIndex);
        final int panelW = getWidth();
        final int panelH = getHeight();
        final int imgW = currentBg.getWidth();
        final int imgH = currentBg.getHeight();

        final double scale = Math.max((double) panelW / imgW, (double) panelH / imgH);

        final int drawW = (int) Math.ceil(imgW * scale);
        final int drawH = (int) Math.ceil(imgH * scale);
        final int x = (panelW - drawW) / 2;
        final int y = (panelH - drawH) / 2;

        g.drawImage(currentBg, x, y, drawW, drawH, null);
    }

    /**
     * Score getter, from the view interface.
     * 
     * @return the score.
     */
    @Override
    public int getScore() {
        return viewInterface.fetchScore();
    }

    /**
     * Player health getter, from the view interface.
     * 
     * @return the player health.
     */
    @Override
    public int getPlayerHealth() {
        return viewInterface.fetchPlayerHealth();
    }

    /**
     * Level getter, from the view interface.
     * 
     * @return the level.
     */
    @Override
    public int getLevel() {
        return viewInterface.getLevel();
    }

    /**
     * Username getter, from the view interface.
     * 
     * @return the username.
     */
    @Override
    public String getUsername() {
        return viewInterface.fetchUsername();
    }

    /**
     * Updates the canvas and the status bar.
     */
    public void update() {

        if (viewInterface.getGameState() == GameState.GAMEOVER) {
            if (gameOverDialog == null || !gameOverDialog.isVisible()) {
                statusBar.repaint();
                this.showGameOver();
            }
            return;
        }

        if (shouldChangeBackground()) {
            updateBackground();
        }

        canvas.update();
        statusBar.repaint();
        canvas.repaint();
    }

    /**
     * Updates the background.
     */
    public void updateBackground() {
        currentBackgroundIndex = viewInterface.getLevel() - 1;
        if (currentBackgroundIndex >= backgrounds.size()) {
            currentBackgroundIndex %= backgrounds.size();
        }
    }

    /**
     * Helper method that checks if the background should be changed.
     * 
     * @return true if the background should be changed, false otherwise.
     */
    private boolean shouldChangeBackground() {
        int bgIndex = viewInterface.getLevel() - 1;
        if (bgIndex >= backgrounds.size()) {
            bgIndex %= backgrounds.size();
        }

        return bgIndex != currentBackgroundIndex;
    }

    /**
     * Shows the pause panel.
     */
    private void showPausePanel() {
        pauseDialog = new JDialog(
                SwingUtilities.getWindowAncestor(this), "PAUSE", JDialog.ModalityType.APPLICATION_MODAL);

        pauseDialog.setContentPane(new PausePanel(this));
        pauseDialog.setUndecorated(true);
        pauseDialog.setBackground(UIConstants.PANELS_BG_COLOR);
        pauseDialog.pack();
        pauseDialog.setLocationRelativeTo(this);
        pauseDialog.setVisible(true);
    }

    @Override
    public void showGameOver() {
        SwingUtilities.invokeLater(() -> {
            gameOverDialog = new JDialog(
                    SwingUtilities.getWindowAncestor(this), "GAME OVER", JDialog.ModalityType.APPLICATION_MODAL);
            gameOverDialog.setContentPane(new GameOverPanel(this));
            gameOverDialog.setUndecorated(true);
            gameOverDialog.pack();
            gameOverDialog.setLocationRelativeTo(this);
            gameOverDialog.setVisible(true);
        });
    }

    @Override
    public void restart() {
        if (gameOverDialog != null) {
            gameOverDialog.dispose();
            gameOverDialog = null;
        }
        viewInterface.resetGame();
    }

    @Override
    public void abortGame() {
        if (pauseDialog != null) {
            pauseDialog.dispose();
            pauseDialog = null;
        }

        if (gameOverDialog != null) {
            gameOverDialog.dispose();
            gameOverDialog = null;
        }

        viewInterface.abortGame();
        viewInterface.pauseGame();
    }

    @Override
    public void quit() {
        if (pauseDialog != null) {
            pauseDialog.dispose();
        }
        if (gameOverDialog != null) {
            gameOverDialog.dispose();
        }

        viewInterface.quitGame();
    }

    @Override
    public GameState getGameState() {
        return viewInterface.getGameState();
    }
}
