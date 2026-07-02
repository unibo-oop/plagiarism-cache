package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.player.api.ModifiablePlayer;
import it.unibo.michelito.model.powerups.api.AbstractPowerUp;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;

import java.math.BigDecimal;

/**
 * Implementation of a {@link it.unibo.michelito.model.powerups.api.PowerUp} that increases {@link ModifiablePlayer} speed.
 */
class SpeedPowerUp extends AbstractPowerUp {
    private static final double SPEED_UPGRADE = 0.01;

    /**
     * Constructs a {@code SpeedPowerUp} at the specified {@link Position}.
     *
     * @param position the {@link Position} where the power-up is located
     */
    SpeedPowerUp(final Position position) {
        super(position);
    }

    /**
     * {@inheritDoc}
     *
     * Increases the player's speed by {@value #SPEED_UPGRADE}.
     *
     * @param player the {@link ModifiablePlayer} receiving the effect
     */
    @Override
    public final void applyEffect(final ModifiablePlayer player) {
        final double newSpeed = BigDecimal.valueOf(SPEED_UPGRADE).add(BigDecimal.valueOf(player.getSpeed())).doubleValue();
        player.setSpeed(newSpeed);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ObjectType getType() {
        return  ObjectType.SPEED_POWERUP;
    }
}
