package zombieversity.view;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Manages round view, showing round counter during the game and time to start new round during breaks.
 * Implementation of {@link RoundView}
 */
public class RoundViewImpl implements RoundView {

    /**
     * Font dimension in labels.
     */
    private static final int FONT_DIM = 18;

    private int roundNum;
    private final Label timerLabel;
    private final Label roundLabel;

    /**
     * Instatiates a {@link RoundViewImpl}.
     * @param timer timer label.
     * @param round round label.
     */
    public RoundViewImpl(final Label timer, final Label round) {
        this.timerLabel = timer;
        this.roundLabel = round;
        this.timerLabel.setFont(Font.font("Verdana", FONT_DIM));
        this.roundLabel.setFont(Font.font("Verdana", FONT_DIM));
        this.timerLabel.setVisible(true);
        this.roundLabel.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setRound(final int roundCount) {
        this.roundNum = roundCount;
        this.roundLabel.setText(Integer.toString(roundCount));
        this.timerLabel.setVisible(false);
        this.roundLabel.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void breakRound() {
        this.timerLabel.setVisible(true);
        this.roundLabel.setVisible(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateTimerLabel(final double missingTime) {
        this.timerLabel.setText("Round " + (this.roundNum + 1) + " is starting \n in " + Math.round(missingTime) + " seconds");
    }
}
