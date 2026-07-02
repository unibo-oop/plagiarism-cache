package bzzbomber.model.entities;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This class model a manager of all entity.
 *
 */
public class EntityManager {

    private List<Entity> worldEntities;

    /**
     * Constructor of Entity Manager.
     */
    public EntityManager() {
        this.worldEntities = new LinkedList<>();
    }

    /**
     * @param e
     *            entity
     */
    public synchronized void addEntity(final Entity e) {
        this.worldEntities.add(e);
    }

    /**
     * @return list of entities
     */
    public List<Entity> getWorldEntities() {
        return Collections.unmodifiableList(worldEntities);
    }

    /**
     * 
     * @param worldEntities
     *            the world entity
     */
    public void setWorldEntities(final List<Entity> worldEntities) {
        this.worldEntities = worldEntities;
    }

    /**
     * Delete all entity.
     */
    public synchronized void removeWorldEntities() {
        this.worldEntities.clear();
    }

    /**
     * 
     * @param entity
     *            the entity
     */
    public synchronized void removeEntity(final Entity entity) {
        this.worldEntities.remove(entity);
    }
}
