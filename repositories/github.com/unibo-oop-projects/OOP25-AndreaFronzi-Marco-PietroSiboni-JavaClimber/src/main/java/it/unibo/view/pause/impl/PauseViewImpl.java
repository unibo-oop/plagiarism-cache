package it.unibo.view.pause.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.controller.pause.api.PauseController;
import it.unibo.view.pause.api.PauseView;

/**
 * Implementation of the {@link PauseView} interface.
 */
public final class PauseViewImpl extends JPanel implements PauseView {

    private static final long serialVersionUID = 1L;

    private static final int SIZE_FONT_TITLE = 60;

    private static final int RED_VALUE_RECORD = 255;
    private static final int GREEN_VALUE_RECORD = 215;
    private static final int BLUE_VALUE_RECORD = 0;

    private static final int SIZE_FONT_RECORD = 36;

    private static final int DIMENSION_WIDTH = 0;
    private static final int DIMENSION_HEIGHT_FIRST = 15;

    private static final int DIMENSION_HEIGHT_SECOND = 30;

    private static final int DIMENSION_HEIGHT_THIRD = 10;

    /**
     * The pause controller associated with this view. It is used to handle user
     * interactions and control the flow of the pause screen.
     */
    private final transient PauseController controller;

    /**
     * The resume button, allowing the player to resume the game.
     */
    private final JButton resume;

    /**
     * The menu button, allowing the player to return to the main menu.
     */
    private final JButton menu;

    /**
     * Constructs a new PauseViewImpl with the specified controller. Initializes the
     * view components and layout.
     * 
     * @param controller the pause controller associated with this view.
     */
    public PauseViewImpl(final PauseController controller) {
        this.controller = controller;
        this.setBackground(Color.BLACK);

        final JLabel pauseTitle = new JLabel("PAUSA");
        pauseTitle.setFont(new Font(Font.SERIF, Font.BOLD, SIZE_FONT_TITLE));
        pauseTitle.setForeground(Color.WHITE);
        pauseTitle.setAlignmentX(CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.resume = new JButton("Resume");
        this.resume.setAlignmentX(CENTER_ALIGNMENT);

        this.menu = new JButton("Menu");
        this.menu.setAlignmentX(CENTER_ALIGNMENT);

        this.add(Box.createVerticalGlue());
        this.add(pauseTitle);

        this.add(Box.createRigidArea(new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT_FIRST)));

        if (this.controller.isNewHighScore()) {
            final JLabel newRecordLabel = new JLabel("NEW HIGH SCORE: " + this.controller.getScore());
            newRecordLabel.setFont(new Font(Font.SERIF, Font.BOLD, SIZE_FONT_RECORD));
            newRecordLabel.setForeground(new Color(RED_VALUE_RECORD, GREEN_VALUE_RECORD, BLUE_VALUE_RECORD));
            newRecordLabel.setAlignmentX(CENTER_ALIGNMENT);
            this.add(newRecordLabel);
        } else {
            final JLabel scoreLabel = new JLabel("Score: " + this.controller.getScore());
            scoreLabel.setFont(new Font(Font.SERIF, Font.BOLD, SIZE_FONT_RECORD));
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
            this.add(scoreLabel);
        }

        this.add(Box.createRigidArea(new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT_SECOND)));
        this.add(this.resume);
        this.add(Box.createRigidArea(new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT_THIRD)));
        this.add(this.menu);
        this.add(Box.createVerticalGlue());

        this.setResumeListener();
        this.setMenuListener();
    }

    private void setResumeListener() {
        this.resume.addActionListener(e -> this.resume());
    }

    private void setMenuListener() {
        this.menu.addActionListener(e -> this.menu());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resume() {
        this.controller.resume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void menu() {
        this.controller.menu();
    }
}
