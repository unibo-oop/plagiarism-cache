package spacesurvival.view.game.utilities;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import spacesurvival.view.utilities.GraphicsLayoutUtils;
import spacesurvival.view.utilities.FactoryGUIs;

/**
 * Implement a component view for score counting.
 */
public class Score extends JPanel {
    private static final long serialVersionUID = 2065504020785088220L;
    private String namePlayer;
    private final JLabel stringScore;
    private final JLabel valueScore;

    /**
     * Initialize and create all graphics components.
     */
    public Score() {
        super(new FlowLayout());
        super.setOpaque(false);
        this.namePlayer = "User ";
        this.stringScore = new JLabel(namePlayer + GraphicsLayoutUtils.SCORE_STRING);
        this.valueScore = new JLabel();

        super.add(FactoryGUIs.createPanelGridBagUnionComponentsHorizontal(java.util.List.of(
                this.stringScore, this.valueScore), FactoryGUIs.INSET_H2));
    }

    /**
     * Set text for name's player.
     * @param namePlayer is text for name of player.
     */
    public void setNamePlayer(final String namePlayer) {
        this.namePlayer = namePlayer;
        this.stringScore.setText(namePlayer + " " + GraphicsLayoutUtils.SCORE_STRING);
    }

    /**
     * Set text of value's score.
     * @param score is value of score.
     */
    public void setScore(final long score) {
        this.valueScore.setText(Long.toString(score));
    }

    /**
     * Set text of value's score.
     * @param score is value of score.
     */
    public void setScore(final String score) {
        this.valueScore.setText(score);
    }

    /**
     * Set font for all components.
     * @param font for components.
     */
    public void setFontAll(final Font font) {
        this.stringScore.setFont(font);
        this.valueScore.setFont(font);
    }

    /**
     * Set color foreground for all components.
     * @param color fore foreground.
     */
    public void setForegroundAll(final Color color) {
        this.stringScore.setForeground(color);
        this.valueScore.setForeground(color);
    }
}
