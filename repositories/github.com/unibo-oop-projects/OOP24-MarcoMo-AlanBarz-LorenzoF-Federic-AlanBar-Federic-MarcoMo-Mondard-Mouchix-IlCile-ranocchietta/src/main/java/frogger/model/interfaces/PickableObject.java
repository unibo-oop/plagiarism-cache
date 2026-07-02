package frogger.model.interfaces;

import frogger.model.implementations.PickableObjectDependency;

/**
 * Represents an object in the game that can be picked up or interacted with by other entities.
 * Extends the {@link GameObject} interface and provides methods for handling pickable behavior.
 *
 * <p>Implementations of this interface should define what the object needs to do when picked up,
 * what entity it is related to, and any dependencies required for its functionality.</p>
 */
public interface PickableObject extends GameObject {

    /**
     * Handles the logic when this object is picked up by another entity.
     * This method should define what happens when the object is picked.
     */
    void onPick();

    /**
     * Gets the dependencies required for this pickable object to function properly.
     *
     * @return the dependencies required by this pickable object
     */
    PickableObjectDependency getRequiredDependencies();

    /**
     * Gets the entity that is related to this pickable object.
     *
     * @return the related entity
     */
    Object getRelatedEntity();

    /**
     * Sets the entity that is related to this pickable object.
     *
     * @param relatedEntity the entity to set as related
     */
    void setRelatedEntity(Object relatedEntity);
}
