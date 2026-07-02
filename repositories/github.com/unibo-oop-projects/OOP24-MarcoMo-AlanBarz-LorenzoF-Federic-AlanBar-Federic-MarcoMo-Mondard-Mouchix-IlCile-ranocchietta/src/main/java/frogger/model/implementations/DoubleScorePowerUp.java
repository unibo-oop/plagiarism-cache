package frogger.model.implementations;

import frogger.common.Pair;
import frogger.common.Position;


/**
 * A power-up that doubles the player's score for a limited duration.
 * <p>
 * When collected, this power-up sets the player's score multiplier to 2,
 * allowing the player to earn double points for actions performed while the effect is active.
 * Once the effect duration ends, the score multiplier is reset to its default value.
 * </p>
 * <p>
 * This power-up is intended to be picked up by player entities only.
 * </p>
 *
 * @see PowerUpImpl
 * @see PlayerObjectImpl
 * @see PowerUpType
 */
public class DoubleScorePowerUp extends PowerUpImpl {

    /**
     * Constructs a new DoubleScorePowerUp at the specified position with the given dimensions and duration.
     *
     * @param pos        the position of the power-up
     * @param dimension  the dimensions of the power-up
     * @param duration   the duration of the power-up effect in seconds
     */
    public DoubleScorePowerUp(final Position pos, final Pair dimension, final int duration) {
        super(pos, dimension, duration);
        super.setImage("x2_PawerUp.png");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyEffect() {
        if (super.getRelatedEntity() instanceof PlayerObjectImpl player) {
            player.setScoreMultiplier(2);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEffect() {
        if (super.getRelatedEntity() instanceof PlayerObjectImpl player) {
            player.setScoreMultiplier(1); // Reset the score multiplier
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PickableObjectDependency getRequiredDependencies() {
        return PickableObjectDependency.PLAYER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUpType getPowerUpType() {
        return PowerUpType.X2_SCORE;
    }
}
