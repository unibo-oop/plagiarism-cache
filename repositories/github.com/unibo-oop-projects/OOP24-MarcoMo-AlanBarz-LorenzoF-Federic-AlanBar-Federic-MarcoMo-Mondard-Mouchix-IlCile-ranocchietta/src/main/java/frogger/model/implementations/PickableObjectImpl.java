package frogger.model.implementations;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PickableObject;

/**
 * Abstract implementation of the {@link PickableObject} interface, representing an object
 * in the game that can be picked up by the player or another entity.
 * <p>
 * This class extends {@link GameObjectImpl} and provides basic functionality for managing
 * a related entity, which can be associated with the pickable object.
 * </p>
 *
 * <p>
 * Subclasses must implement the {@link #onPick()} method to define behavior when the object
 * is picked, and the {@link #getRequiredDependencies()} method to specify any dependencies
 * required by the pickable object.
 * </p>
 *
 * @see frogger.model.interfaces.PickableObject
 * @see frogger.model.implementations.GameObjectImpl
 */
public abstract class PickableObjectImpl extends GameObjectImpl implements PickableObject {

    /**
     * The entity related to this pickable object, which can be the player, another gameObject or a list of gameObject.
     * Initially set to an empty optional to indicate no related entity.
     */
    private Object relatedEntity; 

    /**
     * Constructs a new PickableObjectImpl with the specified position and dimension.
     *
     * @param pos the position of the pickable object
     * @param dimension the dimensions of the pickable object
     */
    public PickableObjectImpl(final Position pos, final Pair dimension) {
        super(pos, dimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRelatedEntity(final Object relatedEntity) {
        this.relatedEntity = relatedEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getRelatedEntity() {
        return relatedEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void onPick();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract PickableObjectDependency getRequiredDependencies();
}
