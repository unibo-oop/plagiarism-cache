package it.unibo.jtrs.view.impl;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

import it.unibo.jtrs.controller.api.ScoreController;
import it.unibo.jtrs.view.api.GenericPanel;
import it.unibo.jtrs.view.custom.Constants;
import it.unibo.jtrs.view.custom.Label;

/**
 * The class models the score panel. This view must show the current level and
 * score.
 */
public class ScorePanel extends GenericPanel {

    public static final long serialVersionUID = 4328743;

    private final Label level;
    private final Label score;
    private final Label message;
    private final transient ScoreController controller;

    /**
     * Constructor.
     *
     * @param controller the score controller
     */
    public ScorePanel(final ScoreController controller) {
        this.controller = controller;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false);

        final var levelTxt = new Label("LEVEL", Constants.ScorePanel.FONT_SIZE_L);
        levelTxt.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.level = new Label(String.valueOf(this.controller.getLevel()), Constants.ScorePanel.FONT_SIZE_L);
        this.level.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        final var scoreTxt = new Label("SCORE", Constants.ScorePanel.FONT_SIZE_L);
        scoreTxt.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.score = new Label(String.valueOf(this.controller.getScore()), Constants.ScorePanel.FONT_SIZE_L);
        this.score.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        this.message = new Label("", Constants.ScorePanel.FONT_SIZE_S);
        this.message.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        this.add(levelTxt);
        this.add(Box.createVerticalStrut(Constants.ScorePanel.INTERLINE_S));
        this.add(this.level);
        this.add(Box.createVerticalStrut(Constants.ScorePanel.INTERLINE_L));

        this.add(scoreTxt);
        this.add(Box.createVerticalStrut(Constants.ScorePanel.INTERLINE_S));
        this.add(this.score);
        this.add(Box.createVerticalStrut(Constants.ScorePanel.INTERLINE_L));

        this.add(this.message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redraw() {
        this.level.setText(String.valueOf(this.controller.getLevel()));
        this.score.setText(String.valueOf(this.controller.getScore()));

        final var text = switch (this.controller.returnRemoved()) {
            case 1 -> "One Line!";
            case 2 -> "Two Lines!!";
            case 3 -> "Three Lines!!!";
            case 4 -> "TETRIS";
            default -> "";
        };
        this.message.setText(text);
    }

}
