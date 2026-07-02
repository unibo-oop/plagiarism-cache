package vg.model.entity.dynamicEntity.player;

import com.google.common.base.Optional;
import vg.utils.V2D;
import java.io.Serializable;
import java.util.List;

public interface Tail extends Serializable {

    /**
     * Get all coordinates that define tail of player.
     * @return list of all point of tail saved
     */
    List<V2D> getCoordinates();

    /**
     * Get all vertex point of tail
     * @return list of V2D that are vertex of tail
     */
    List<V2D> getVertex();

    /**
     * Remove al saved coordinates of tail.
     */
    void resetTail();

    /**
     * Get last added coorindate of tail.
     * @return Optional of {@link V2D}, if tail is empty is returned an {@link  Optional#absent()}
     */
    Optional<V2D> getLastCoordinate();

    /**
     * Append new point to tail.
     * @param point new point to add
     */
    void addPoint(V2D point);
}
