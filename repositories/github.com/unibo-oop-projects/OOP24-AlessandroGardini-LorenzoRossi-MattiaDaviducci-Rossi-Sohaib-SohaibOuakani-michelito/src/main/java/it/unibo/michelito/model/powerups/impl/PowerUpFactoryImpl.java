package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.powerups.api.PowerUpFactory;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.model.powerups.api.PowerUpType;
import it.unibo.michelito.util.Position;

/**
 * Implementation of {@link PowerUpFactory}.
 */
public class PowerUpFactoryImpl implements PowerUpFactory {
    /**
     *{@inheritDoc}
     */
    @Override
    public PowerUp createPowerUp(final Position position, final PowerUpType type) {
        return switch (type) {
            case PowerUpType.BOMB_TYPE_POWERUP -> new BombTypePowerUp(position);
            case PowerUpType.BOMB_LIMIT_POWERUP -> new BombLimitPowerUp(position);
            case PowerUpType.SPEED_POWERUP -> new SpeedPowerUp(position);
            default -> throw new IllegalArgumentException("Unknown power up type: " + type);
        };
    }
}
