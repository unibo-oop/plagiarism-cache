package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.player.api.ModifiablePlayer;
import it.unibo.michelito.model.powerups.api.AbstractPowerUp;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;

/**
 * Represents a power-up that increases the player's bomb limit.
 */
class BombLimitPowerUp extends AbstractPowerUp {
    private static final int BOMB_LIMIT_UPGRADE = 1;

    /**
     * Constructs a {@link BombLimitPowerUp} at the specified {@link Position}.
     *
     * @param position the {@link Position} where the power-up is located
     */
    BombLimitPowerUp(final Position position) {
        super(position);
    }

    /**
     * {@inheritDoc}
     *
     * Increases the player's bomb limit by {@value #BOMB_LIMIT_UPGRADE}.
     *
     * @param player the {@link ModifiablePlayer} receiving the effect
     */
    @Override
    public final void applyEffect(final ModifiablePlayer player) {
        final int newLimit = player.getBombLimit() + BOMB_LIMIT_UPGRADE;
        player.setBombLimit(newLimit);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ObjectType getType() {
        return ObjectType.BOMB_LIMIT_POWERUP;
    }
}
