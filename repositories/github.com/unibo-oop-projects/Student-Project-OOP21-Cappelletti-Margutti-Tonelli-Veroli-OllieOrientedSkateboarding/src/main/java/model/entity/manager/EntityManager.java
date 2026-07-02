package model.entity.manager;

import java.util.List;

import model.entity.DynamicEntity;

/**
 * 
 * Interface identifying the manager for {@link DynamicEntity}.
 *
 */
public interface EntityManager {

    /**
     * 
     * @return a list containing the entities that are actually on the game.
     */
    List<DynamicEntity> getEntities();

    /**
     * Set the speedX of all the entities currently on the game.
     * @param speedX the speed of the entities.
     */
    void setSpeedX(double speedX);

    /**
     * Update the position of every entity in the list, remove and add element.
     */
    void updateList();

}
