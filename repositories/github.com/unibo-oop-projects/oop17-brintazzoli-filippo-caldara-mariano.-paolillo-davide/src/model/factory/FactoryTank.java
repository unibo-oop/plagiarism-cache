package model.factory;

import model.object.EnemyTank;
import model.object.PlayerTank;
import model.object.Tank;
import model.utility.Pair;
/**
 * Factory for Tank.
 * Allows to create playerTank and EnemyTank
 * @see Tank
 * @see PlayerTank
 * @see EnemyTank
 */
public final class FactoryTank {
    private static final double SPEED = 3.0;
    private static final double PROJECTILE_SPEED = 4.0;
    /**
     * Constructor.
     */
    private FactoryTank() {
    }
    /**
     * Create a playerTank, speed and projectile's speed are constant.
     * @param position the initial position
     * @param lifes nr of lifes
     * @return {@link PlayerTank} just created
     */
    public static Tank createPlayer(final Pair<Double, Double> position, final int lifes) {
        return new PlayerTank(position, lifes, SPEED, PROJECTILE_SPEED);
    }
    /**
     * Create a {@link EnemyTank}.
     * @param position the initial position
     * @param lifes nr of lifes
     * @param speed of tank
     * @param projectileSpeed projectile speed
     * @return the {@link EnemyTank}
     */
    public static Tank createEnemy(final Pair<Double, Double> position, final int lifes, final double speed, final double projectileSpeed) {
        return new EnemyTank(position, lifes, speed, projectileSpeed);
    }
}
