package it.unibo.scat.view.game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.game.api.GamePanelInterface;
import it.unibo.scat.view.util.Audio;
import it.unibo.scat.view.util.AudioManager;
import it.unibo.scat.view.util.AudioTrack;

/**
 * Panel shown in the GamePanel when the game is over.
 */
public final class GameOverPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 400;
    private static final int TOP_MARGIN = 30;
    private static final int BUTTON_GAP = 15;
    private final transient Audio soundEffect;

    /**
     * Initializes the panel with title, final score, and action buttons.
     * 
     * @param game the game panel interface.
     */
    public GameOverPanel(final GamePanelInterface game) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(UIConstants.PANELS_BG_COLOR);
        this.setBorder(UIConstants.PANELS_BORDER);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.soundEffect = new AudioManager();

        add(Box.createVerticalStrut(TOP_MARGIN));

        final JLabel title = new JLabel("GAME OVER");
        title.setFont(UIConstants.FONT_XL);
        title.setForeground(UIConstants.ARCADE_GREEN);
        title.setAlignmentX(CENTER_ALIGNMENT);
        add(title);
        add(Box.createVerticalGlue());

        createButton("RESTART", game::restart);
        createButton("MENU", game::abortGame);
        createButton("QUIT", game::quit);

        add(Box.createVerticalGlue());
        final JLabel leaderboardLabel = new JLabel("leaderboard updated");
        leaderboardLabel.setFont(UIConstants.FONT_S);
        leaderboardLabel.setForeground(Color.WHITE);
        leaderboardLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(leaderboardLabel);
        add(Box.createVerticalStrut(BUTTON_GAP / 3));

    }

    /**
     * Creates a styled button with sound and hover effects.
     * 
     * @param text   the button label.
     * @param action the logic to execute on click.
     */
    private void createButton(final String text, final Runnable action) {
        final JButton btn = new JButton(" " + text + " ");
        btn.setFont(UIConstants.FONT_L);
        btn.setForeground(Color.RED);
        btn.setBackground(Color.BLACK);
        btn.setAlignmentX(CENTER_ALIGNMENT);
        btn.setFocusable(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                btn.setText("<" + text + ">");
                btn.setForeground(Color.WHITE);
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                soundEffect.play(AudioTrack.MOUSE_OVER, false);
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                btn.setText(" " + text + " ");
                btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                btn.setForeground(Color.RED);
            }
        });

        btn.addActionListener(e -> {
            soundEffect.play(AudioTrack.OPTION_SELECTED, false);

            final Window ancestor = SwingUtilities.getWindowAncestor(this);
            if (ancestor instanceof JDialog) {
                ancestor.dispose();
            }

            action.run();
        });

        add(btn);
        add(Box.createVerticalStrut(BUTTON_GAP));
    }
}
