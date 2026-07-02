package controller.stagehandler.enemygenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import model.entity.EntityID;

/**
 * A part of the controller that chooses what types of enemy to spawn.
 */
public abstract class AbstractEnemyGenerator implements Iterator<EntityID> {

    private static final Set<EntityID> SPAWNABLE_ENEMIES = new HashSet<>();

    static {
        initializeData();
    }

    /**
     * {@inheritDoc}
     * An AbstractEnemyGenerator generates enemies indefinitely.
     */
    @Override
    public boolean hasNext() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract EntityID next();

    /**
     * Returns the set of spawnable enemies.
     * @return the set of spawnable enemies.
     */
    protected static Set<EntityID> getSpawnableEnemies() {
        return Collections.unmodifiableSet(AbstractEnemyGenerator.SPAWNABLE_ENEMIES);
    }

    /**
     * Initialises the set of spawnable enemies for all AbstractEnemyGenerator.
     */
    private static void initializeData() {
        AbstractEnemyGenerator.SPAWNABLE_ENEMIES.addAll(Set.of(
            EntityID.FIGHTER, EntityID.JUGGERNAUT, EntityID.CUTTER
        ));
    }

}
