package frogger.model.implementations;

import frogger.common.LoadSave;
import frogger.common.Pair;
import frogger.common.Position;

/**
 * Represents a power-up that grants the player an extra life when collected.
 * <p>
 * This power-up is intended to be permanent (duration set to 0) and applies its effect
 * by increasing the player's life count. The effect is only applicable to entities of type
 * {@link PlayerObjectImpl}. The visual representation is set using {@link LoadSave#EXTRA_LIFE}.
 * </p>
 *
 * <p>
 * Usage:
 * <ul>
 *   <li>Call {@link #applyEffect()} to add a life to the player.</li>
 *   <li>Override {@link #removeEffect()} as a no-op since the effect is permanent.</li>
 * </ul>
 * </p>
 *
 * @see PowerUpImpl
 * @see PlayerObjectImpl
 * @see PowerUpType#EXTRA_LIFE
 */
public class ExtraLifePowerUp extends PowerUpImpl {

    /**
     * Constructs a new ExtraLifePowerUp at the specified position with the given dimensions and duration.
     * The duration is set to 0 for a permanent effect.
     *
     * @param pos        the position of the power-up
     * @param dimension  the dimensions of the power-up
     */
    public ExtraLifePowerUp(final Position pos, final Pair dimension) {
        super(pos, dimension, 0); // Duration is to set to 0 for permanent effect
        super.setImage("heartPowerUp.png");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyEffect() {
        if (super.getRelatedEntity() instanceof PlayerObjectImpl player) {
            player.addLife();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEffect() { }

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
       return PowerUpType.EXTRA_LIFE;
    }
}
