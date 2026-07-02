package it.unibo.scat.view.game.statusbar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.GameState;
import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.game.api.GamePanelInterface;
import it.unibo.scat.view.util.AudioManager;
import it.unibo.scat.view.util.AudioTrack;

/**
 * Represents the panel at the top of the game screen.
 */
@SuppressFBWarnings(value = "SE_TRANSIENT_FIELD_NOT_RESTORED", justification = "Component not intended for serialization")
public final class StatusBar extends JPanel {
    private static final long serialVersionUID = 1L;
    private final transient GamePanelInterface gamePanelInterface;
    private JPanel pausePanel;

    /**
     * Constructs the status bar and initializes its UI components.
     * 
     * @param gamePanelInterface the interface used to retrieve new game data.
     */
    public StatusBar(final GamePanelInterface gamePanelInterface) {
        this.gamePanelInterface = gamePanelInterface;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        initPausePanel();
        initScoreLabel();
        initLevelLabel();
        initLivesPanel();
    }

    /**
     * Initializes the pause control panel.
     */
    private void initPausePanel() {
        final int targetH = 80;
        final int targetW = 210;

        pausePanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            private final Image pauseImage = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource(UIConstants.PAUSE_BUTTON_PATH))).getImage();

            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);

                final int imgW = pauseImage.getWidth(this);
                final int imgH = pauseImage.getHeight(this);
                if (imgW <= 0 || imgH <= 0) {
                    return;
                }

                final double scale = (double) (targetH - 10) / imgH;
                final int drawH = targetH - 10;
                final int drawW = (int) Math.round(imgW * scale);

                final int y = (getHeight() - drawH) / 2;

                g.drawImage(pauseImage, 0, y, drawW, drawH, this);
            }

        };
        pausePanel.setPreferredSize(new Dimension(targetW, targetH));
        pausePanel.setMinimumSize(new Dimension(targetW, targetH));
        pausePanel.setMaximumSize(new Dimension(targetW, targetH));
        pausePanel.setFocusable(false);
        pausePanel.setForeground(Color.WHITE);
        pausePanel.setOpaque(false);

        pausePanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent e) {
                if (gamePanelInterface.getGameState() != GameState.PAUSE) {
                    new AudioManager().play(AudioTrack.OPTION_SELECTED, false);
                    gamePanelInterface.pause();
                }

            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                pausePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                new AudioManager().play(AudioTrack.MOUSE_OVER, false);
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                pausePanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }

        });

        add(pausePanel);
    }

    /**
     * Initializes the difficulty level display.
     */
    private void initLevelLabel() {
        final JLabel levelLabel = new JLabel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                setText("<html><span style='color:#00FF00'>LEVEL:</span><span style='color:white'>"
                        + gamePanelInterface.getLevel() + "</span></html>");
            }

        };

        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(UIConstants.FONT_M);
        levelLabel.setText(" ");
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        levelLabel.setVerticalAlignment(SwingConstants.CENTER);
        levelLabel.setFocusable(false);
        levelLabel.setOpaque(false);

        add(levelLabel);
    }

    /**
     * Initializes the score display.
     */
    private void initScoreLabel() {
        final JLabel scoreLabel = new JLabel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                setText("<html><span style='color:#00FF00'>SCORE:</span><span style='color:white'>"
                        + gamePanelInterface.getScore() + "</span></html>");

            }

        };

        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(UIConstants.FONT_M);
        scoreLabel.setText(" ");
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
        scoreLabel.setFocusable(false);
        scoreLabel.setOpaque(false);

        add(scoreLabel);
    }

    /**
     * Initializes the graphical display for player lives.
     */
    private void initLivesPanel() {
        final int targetH = 80;
        final int targetW = 210;
        final JPanel livesPanel = new JPanel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                final Image currImg;

                switch (gamePanelInterface.getPlayerHealth()) {
                    case 1 -> {
                        currImg = new ImageIcon(
                                Objects.requireNonNull(getClass().getResource(UIConstants.LIFE_PANEL_PATHS.get(1))))
                                .getImage();
                    }
                    case 2 -> {
                        currImg = new ImageIcon(
                                Objects.requireNonNull(getClass().getResource(UIConstants.LIFE_PANEL_PATHS.get(2))))
                                .getImage();
                    }
                    case 3 -> {
                        currImg = new ImageIcon(
                                Objects.requireNonNull(getClass().getResource(UIConstants.LIFE_PANEL_PATHS.get(3))))
                                .getImage();
                    }
                    default -> {
                        currImg = new ImageIcon(
                                Objects.requireNonNull(getClass().getResource(UIConstants.LIFE_PANEL_PATHS.get(0))))
                                .getImage();
                    }
                }

                final int imgW = currImg.getWidth(this);
                final int imgH = currImg.getHeight(this);

                if (imgW <= 0 || imgH <= 0) {
                    return;
                }

                final double scale = (double) (targetH - 20) / imgH;
                final int drawH = targetH - 20;
                final int drawW = (int) Math.round(imgW * scale);

                final int x = (getWidth() - drawW) / 2;
                final int y = (getHeight() - drawH) / 2;

                g.drawImage(currImg, x, y, drawW, drawH, this);

            }

        };
        livesPanel.setOpaque(false);
        livesPanel.setFocusable(false);
        livesPanel.setPreferredSize(new Dimension(targetW, targetH));
        livesPanel.setMinimumSize(new Dimension(targetW, targetH));
        livesPanel.setMaximumSize(new Dimension(targetW, targetH));

        add(livesPanel);
    }

}
