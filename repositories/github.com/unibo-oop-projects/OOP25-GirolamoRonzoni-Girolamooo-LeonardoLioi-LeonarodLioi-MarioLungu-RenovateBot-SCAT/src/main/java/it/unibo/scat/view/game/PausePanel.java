package it.unibo.scat.view.game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.game.api.GamePanelInterface;
import it.unibo.scat.view.util.Audio;
import it.unibo.scat.view.util.AudioManager;
import it.unibo.scat.view.util.AudioTrack;

/**
 * Panel shown in the GamePanel when the game is paused.
 */
public final class PausePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private static final int TITLE_SPACING = 30;
    private static final int BUTTON_SPACING = 20;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 400;
    private static final int PANEL_ALPHA = 230;
    private final transient Audio soundEffect;

    /**
     * Pause panel constructor, creates the RESUME, MENU, QUIT buttons and adds the
     * title..
     * 
     * @param game the game panel interface.
     */
    public PausePanel(final GamePanelInterface game) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(UIConstants.PANELS_BG_COLOR);
        this.setOpaque(true);
        this.soundEffect = new AudioManager();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBorder(UIConstants.PANELS_BORDER);

        add(Box.createVerticalStrut(TITLE_SPACING));
        addTitle();

        add(Box.createVerticalGlue());

        createButton("RESUME", game::resume);
        add(Box.createVerticalStrut(BUTTON_SPACING));
        createButton("MENU", game::abortGame);
        add(Box.createVerticalStrut(BUTTON_SPACING));
        createButton("QUIT", game::quit);

        this.add(Box.createVerticalGlue());
    }

    /**
     * Helper method, adds title.
     */
    private void addTitle() {
        final JLabel title = new JLabel("GAME PAUSED");

        title.setFont(UIConstants.FONT_XL);
        title.setForeground(UIConstants.ARCADE_GREEN);
        title.setAlignmentX(CENTER_ALIGNMENT);

        add(title);
        add(Box.createVerticalStrut(TITLE_SPACING));
    }

    /**
     * Helper method that creates the buttons.
     * 
     * @param text   the text displayed on buttons.
     * @param action the button's action.
     */
    private void createButton(final String text, final Runnable action) {
        final JButton button = new JButton(" " + text + " ");

        button.setFont(UIConstants.FONT_L);
        button.setForeground(Color.RED);
        button.setBackground(Color.BLACK);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setFocusable(false);
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setRolloverEnabled(false);

        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                button.setText("<" + text + ">");
                button.setForeground(Color.WHITE);
                soundEffect.play(AudioTrack.MOUSE_OVER, false);
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                button.setText(" " + text + " ");
                button.setForeground(Color.RED);
            }

            @Override
            public void mouseClicked(final MouseEvent e) {
                soundEffect.play(AudioTrack.OPTION_SELECTED, false);
                action.run();
            }
        });

        add(button);
    }

    /**
     * Helps to paint the component.
     * 
     * @param g the graphics context in which to paint.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(0, 0, 0, PANEL_ALPHA));
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
        g2d.dispose();
    }
}
