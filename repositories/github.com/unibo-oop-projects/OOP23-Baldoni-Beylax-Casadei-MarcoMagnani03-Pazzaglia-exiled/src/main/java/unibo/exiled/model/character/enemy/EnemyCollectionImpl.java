package unibo.exiled.model.character.enemy;

import unibo.exiled.utilities.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.Optional;
import java.util.Iterator;

/**
 * An iterable set of enemies.
 */
public final class EnemyCollectionImpl implements EnemyCollection {
    private final Set<Enemy> enemies;

    /**
     * The constructor of the enemy collection, initializes the Set.
     */
    public EnemyCollectionImpl() {
        this.enemies = new HashSet<>();
    }

    /**
     * Adds an enemy to the collection.
     *
     * @param enemy The enemy to be added.
     */
    @Override
    public void addEnemy(final Enemy enemy) {
        this.enemies.add(enemy);
    }

    /**
     * Removes an enemy from the collection.
     *
     * @param enemy The enemy to be removed.
     */
    @Override
    public void removeEnemy(final Enemy enemy) {
        this.enemies.remove(enemy);
    }

    /**
     * Gets the set of enemies in the collection.
     *
     * @return The set of enemies.
     */
    @Override
    public Set<Enemy> getEnemies() {
        return Collections.unmodifiableSet(this.enemies);
    }

    /**
     * Gets the enemy at a specific position, if any.
     *
     * @param position The position to check.
     * @return An Optional containing the enemy at the position, if present.
     */
    @Override
    public Optional<Enemy> getEnemyFromPosition(final Position position) {
        return this.enemies.stream().filter(enemy -> enemy.getPosition().equals(position)).findFirst();
    }

    @Override
    public Iterator<Enemy> iterator() {
        return this.enemies.iterator();
    }
}
