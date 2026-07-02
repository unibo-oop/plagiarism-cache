package zombieversity.model.entities;

import zombieversity.model.entities.utils.Life;

/**
 * 
 * Used to manage an entity with a life.
 *
 */
public interface ActiveLivingEntity extends ActiveEntity {

    /**
     * 
     * @return the life manager.
     */
    Life getLifeManager();

}
