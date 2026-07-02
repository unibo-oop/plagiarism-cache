package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.player.api.ModifiablePlayer;
import it.unibo.michelito.model.powerups.api.AbstractPowerUp;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.model.bomb.api.Bomb;
import it.unibo.michelito.model.powerups.api.PowerUp;

/**
 * Implementation of a {@link PowerUp} that changes the player's {@link Bomb} type.
 */
class BombTypePowerUp extends AbstractPowerUp {

    /**
     * Constructs a {@code BombTypePowerUp} at the specified position.
     *
     * @param position the {@link Position} where the power-up is located
     */
    BombTypePowerUp(final Position position) {
        super(position);
    }

    /**
     * {@inheritDoc}
     *
     * Changes the player's bomb type to a new random type, ensuring it is different from the current type.
     *
     * @param player the {@link ModifiablePlayer} receiving the effect
     */
    @Override
    public void applyEffect(final ModifiablePlayer player) {
        BombType newBombType = BombType.getRandomType();
        while (newBombType.equals(player.getBombType())) {
            newBombType = BombType.getRandomType();
        }
        player.setBombType(newBombType);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public ObjectType getType() {
        return ObjectType.BOMB_TYPE_POWERUP;
    }
}
