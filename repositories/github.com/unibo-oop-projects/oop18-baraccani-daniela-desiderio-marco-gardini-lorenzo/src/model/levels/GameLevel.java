package model.levels;

import java.util.List;

import model.entities.Hero;
import model.entitiesutil.Entity;
import model.entitiesutil.EntityDirection;
import model.entitiesutil.EntityMovable;
import model.physics.CollisionManager;

/**
 * It implements the structure of a game level. It has to manage and contains
 * all entities.
 *
 */
public interface GameLevel {

    /**
     * It adds the {@link EntityMovable} in the list of changed (position or state)
     * entities.
     * 
     * @param entity to be added in the list.
     */
    void addToRecheckEntity(EntityMovable entity);

    /**
     * It returns the list of all changed (position or state) entities that have to
     * be checked by {@link CollisionManager}.
     * 
     * 
     * @return the list of changed entities.
     */
    List<EntityMovable> getToRecheckEntities();

    /**
     * It returns the current level number.
     * 
     * @return the current level number.
     */
    int getLevelNumber();

    /**
     * Add an {@link Entity} to the level by its informations.
     * 
     * @param type      The string that contains the {@link Entity} type
     * @param x         The x position
     * @param y         The y position
     * @param width     Its width
     * @param height    Its height
     * @param direction Its direction. It's null if the {@link Entity} isn't an
     *                  instance of {@link EntityMovable}
     */
    void addEntity(String type, int x, int y, int width, int height, EntityDirection direction);

    /**
     * Remove the specified {@link Entity} from the current level entities.
     * 
     * @param entity Entity which will be removed.
     */
    void removeEntity(Entity entity);

    /**
     * Remove all current level entities.
     */
    void clearAll();

    /**
     * It returns a list of visible entities in the current level.
     * 
     * @return all visible entities.
     */
    List<Entity> getEntities();

    /**
     * It returns the {@link Hero} instance.
     * 
     * @return The {@link Hero}.
     */
    Hero getHero();

    /**
     * It returns the {@link CollisionManager}.
     * 
     * @return The {@link CollisionManager}.
     */
    CollisionManager getCollisionManager();

    /**
     * Add an {@link Entity} to the level by its informations.
     * 
     * @param entity to be added
     */
    void addEntity(Entity entity);
}
