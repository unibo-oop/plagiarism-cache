package it.unibo.view.end.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.controller.end.api.EndController;
import it.unibo.view.end.api.EndView;

/**
 * Implementation of the {@link EndView} interface, representing the end screen
 * of the game.
 */
public final class EndViewImpl extends JPanel implements EndView {

    private static final long serialVersionUID = 1L;

    private static final int SIZE_FONT_TITLE = 60;

    private static final int RED_VALUE_MORTE = 180;
    private static final int GREEN_VALUE_MORTE = 0;
    private static final int BLUE_VALUE_MORTE = 0;

    private static final int RED_VALUE_RECORD = 255;
    private static final int GREEN_VALUE_RECORD = 215;
    private static final int BLUE_VALUE_RECORD = 0;

    private static final int SIZE_FONT_RECORD = 36;

    private static final int DIMENSION_WIDTH = 0;
    private static final int DIMENSION_HEIGHT_FIRST = 15;

    private static final int DIMENSION_HEIGHT_SECOND = 30;

    private static final int DIMENSION_HEIGHT_THIRD = 10;

    /**
     * The end controller associated with this view. It is used to handle user
     * interactions and control the flow of the end screen.
     */
    private final transient EndController controller;

    /**
     * The restart button, allowing the player to restart the game.
     */
    private final JButton restart;

    /**
     * The menu button, allowing the player to return to the main menu.
     */
    private final JButton menu;

    /**
     * Constructs a new EndViewImpl with the specified controller. Initializes the
     * view components and layout.
     * 
     * @param controller the end controller associated with this view.
     */
    public EndViewImpl(final EndController controller) {
        this.controller = controller;
        this.setBackground(Color.BLACK);

        final JLabel titoloMorte = new JLabel("SEI MORTO");
        titoloMorte.setFont(new Font(Font.SERIF, Font.BOLD, SIZE_FONT_TITLE));
        titoloMorte.setForeground(new Color(RED_VALUE_MORTE, GREEN_VALUE_MORTE, BLUE_VALUE_MORTE));
        titoloMorte.setAlignmentX(CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.restart = new JButton("Restart");
        this.restart.setAlignmentX(CENTER_ALIGNMENT);

        this.menu = new JButton("Menu");
        this.menu.setAlignmentX(CENTER_ALIGNMENT);

        this.add(Box.createVerticalGlue());
        this.add(titoloMorte);
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
        this.add(this.restart);
        this.add(Box.createRigidArea(new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT_THIRD)));
        this.add(this.menu);
        this.add(Box.createVerticalGlue());

        this.setRestartListener();
        this.setMenuListener();
    }

    private void setRestartListener() {
        this.restart.addActionListener(e -> this.restart());
    }

    private void setMenuListener() {
        this.menu.addActionListener(e -> this.menu());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restart() {
        this.controller.restart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void menu() {
        this.controller.menu();
    }

}
