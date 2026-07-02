package it.tbt.model.world.api;

import it.tbt.model.entities.SpatialEntity;

/**
 * Interface implemented by the object who care about other objects logic state.
 * Other SpatialEntities will call the KillObserver onKill and pass themselves signaling to the
 * observer that they should be considered "killed".
 */
public interface KillObserver {

    /**
     * @param spatialEntity the spatialEntity which signals its death.
     */
    void onKill(SpatialEntity spatialEntity);
}
