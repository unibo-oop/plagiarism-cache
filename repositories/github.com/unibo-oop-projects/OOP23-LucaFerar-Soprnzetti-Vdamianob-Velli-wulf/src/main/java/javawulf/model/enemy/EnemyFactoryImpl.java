package javawulf.model.enemy;

import javawulf.model.Coordinate;

/**
 * Implementation of the EnemyFactory.
 */
public final class EnemyFactoryImpl implements EnemyFactory {

    @Override
    public Pawn createPawn(final Coordinate position) {
        return new Pawn(position);
    }

    @Override
    public Guard createGuard(final Coordinate position) {
        return new Guard(position);
    }

}
