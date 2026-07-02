package it.tbt.model.entities;

import it.tbt.model.world.api.KillObserver;

/**
 * Interface for entities that can be erased from the game world.
 */
public interface KillableEntity extends SpatialEntity {

    /**
     * @param killObserver A KillableEntity should be able to register a KillObserver to be notified about its death.
     */
    void setKillObserver(KillObserver killObserver);

}
