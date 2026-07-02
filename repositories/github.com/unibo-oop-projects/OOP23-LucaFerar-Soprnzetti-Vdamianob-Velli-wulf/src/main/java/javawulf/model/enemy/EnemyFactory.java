package javawulf.model.enemy;

import javawulf.model.Coordinate;

/**
 * Factory for creating enemies.
 */
public interface EnemyFactory {

    /**
     * Creates a pawn.
     * 
     * @param position the position of the pawn when created
     * @return the pawn
     */
    Pawn createPawn(Coordinate position);

    /**
     * Creates a guard.
     * 
     * @param position the position of the guard when created
     * @return the guard
     */
    Guard createGuard(Coordinate position);

}
