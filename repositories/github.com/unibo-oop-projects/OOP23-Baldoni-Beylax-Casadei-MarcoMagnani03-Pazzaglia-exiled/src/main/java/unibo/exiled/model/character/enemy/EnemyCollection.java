package unibo.exiled.model.character.enemy;

import unibo.exiled.utilities.Position;

import java.util.Optional;
import java.util.Set;

/**
 * Interface representing a collection of enemy characters in the game.
 */
public interface EnemyCollection extends Iterable<Enemy> {

    /**
     * Removes an enemy from the collection.
     *
     * @param enemy The enemy to be removed.
     */
    void removeEnemy(Enemy enemy);

    /**
     * Adds an enemy to the collection.
     *
     * @param enemy The enemy to be added.
     */
    void addEnemy(Enemy enemy);

    /**
     * Gets the set of enemies in the collection.
     *
     * @return The set of enemies.
     */
    Set<Enemy> getEnemies();

    /**
     * Gets the enemy at a specific position, if any.
     *
     * @param position The position to check.
     * @return An Optional containing the enemy at the position, if present.
     */
    Optional<Enemy> getEnemyFromPosition(Position position);
}
