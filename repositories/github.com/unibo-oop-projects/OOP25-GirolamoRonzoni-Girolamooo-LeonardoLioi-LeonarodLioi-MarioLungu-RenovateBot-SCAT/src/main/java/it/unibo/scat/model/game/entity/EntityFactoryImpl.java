package it.unibo.scat.model.game.entity;

import it.unibo.scat.common.Constants;
import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityType;
import it.unibo.scat.model.api.EntityFactory;

/**
 * Implementation of the EntityFactory interface.
 */
public final class EntityFactoryImpl implements EntityFactory {

    @Override
    public AbstractEntity createEntity(final EntityType type, final int x, final int y) {
        final AbstractEntity newEntity;

        switch (type) {
            case BUNKER -> {
                newEntity = new Bunker(type, x, y, Constants.BUNKER_WIDTH, Constants.BUNKER_HEIGHT,
                        Constants.BUNKER_HEALTH);
            }
            case PLAYER -> {
                newEntity = new Player(type, x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT,
                        Constants.PLAYER_HEALTH);
            }
            case PLAYER_SHOT, INVADER_SHOT -> {
                final Direction direction = type == EntityType.PLAYER_SHOT ? Direction.UP : Direction.DOWN;

                newEntity = new Shot(type, x, y, Constants.SHOT_WIDTH, Constants.SHOT_HEIGHT,
                        Constants.SHOT_HEALTH, direction);
            }
            default -> {
                newEntity = new Invader(type, x, y, Constants.INVADER_WIDTH, Constants.INVADER_HEIGHT,
                        Constants.INVADERS_HEALTH);
            }
        }

        return newEntity;
    }
}
