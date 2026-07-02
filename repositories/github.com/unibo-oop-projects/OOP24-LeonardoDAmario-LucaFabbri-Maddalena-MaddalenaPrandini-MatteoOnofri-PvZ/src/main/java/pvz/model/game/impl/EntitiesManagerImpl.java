package pvz.model.game.impl;

import pvz.model.game.api.EntitiesManager;
import pvz.model.entities.api.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the {@link EntitiesManager} interface.
 * <p>
 * This class manages all game entities (plants, zombies, bullets, etc.), sun resources,
 * zombie spawning, and kill tracking within the game.
 */
public class EntitiesManagerImpl implements EntitiesManager {
    private static final int DEFAULT_SUNS = 50;
    private static final int DEFAULT_KILLS = 0;

    private final Set<Entity> entities = new HashSet<>();
    private int sunCount;
    private int killCount;

    /**
     * Constructs a new EntitiesManager with default sun and kill counts.
     */
    public EntitiesManagerImpl() {
        this.sunCount = DEFAULT_SUNS;
        this.killCount = DEFAULT_KILLS;
    }

    /**
     * Adds an entity to the game.
     *
     * @param entity the entity to add; must not be {@code null}.
     */
    @Override
    public void addEntity(final Entity entity) {
        this.entities.add(entity);
    }

    /**
     * Removes an entity from the game.
     *
     * @param entity the entity to remove; must not be {@code null}.
     */
    @Override
    public void removeEntity(final Entity entity) {
        this.entities.remove(entity);
    }

    /**
     * Returns an unmodifiable view of the current set of entities.
     *
     * @return a {@link Set} of all entities currently managed.
     */
    @Override
    public Set<Entity> getEntities() {
        return Set.copyOf(this.entities);
    }

    /**
     * Increases the sun resource count.
     *
     * @param amount the amount of sun to add; must be positive.
     */
    @Override
    public void addSun(final int amount) {
        this.sunCount = this.sunCount + amount;
    }

    /**
     * Decreases the sun resource count if enough sun is available.
     *
     * @param amount the amount of sun to consume.
     * @return {@code true} if the sun was successfully decreased; {@code false} otherwise.
     */
    @Override
    public boolean decreaseSun(final int amount) {
        if (this.sunCount >= amount) {
            this.sunCount = this.sunCount - amount;
            return true;
        }
        return false;
    }

    /**
     * Increments the zombie kill count.
     */
    @Override
    public void addKill() {
        this.killCount = this.killCount + 1;
    }

    /**
     * Returns the number of zombies killed so far.
     *
     * @return the number of kills.
     */
    @Override
    public int getKillCount() {
        return killCount;
    }

    /**
     * Returns the current amount of sun resources available.
     *
     * @return the sun count.
     */
    @Override
    public int getSunCount() {
        return sunCount;
    }
}
