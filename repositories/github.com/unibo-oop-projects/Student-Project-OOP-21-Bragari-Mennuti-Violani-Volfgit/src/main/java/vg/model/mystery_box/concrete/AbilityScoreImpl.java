package vg.model.mystery_box.concrete;

import vg.model.Stage;
import vg.model.mystery_box.AbilityInTheBox;
import vg.model.mystery_box.AbstractAbilityInstant;
import vg.model.mystery_box.EAbility;
import vg.utils.V2D;
/**
 * This class represents the ability score.
 */
public class AbilityScoreImpl extends AbstractAbilityInstant {
    private static final long serialVersionUID = 1L;
    private final int scoreIncrease;
    public AbilityScoreImpl(final int scoreIncrease) {
        super(EAbility.SCORE);
        this.scoreIncrease = scoreIncrease;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void activate(final Stage<V2D> stage) {
        this.activated();
        final int currentScore = stage.getCurrentScore();
        stage.setCurrentScore(currentScore + scoreIncrease);
    }
}
