package todo.view.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * This interface represents a generic entity builder.
 *
 * @param <S> is the self type of the builder
 * @param <E> is the type of entity to be spawned
 */
public interface EntityBuilder<S extends EntityBuilder<? extends S, E>, E extends Entity> {
    /**
     * Set the entity's position to the desired {@link Vector2}.
     * 
     * @param position is the absolute position of the entity
     * @return the builder
     */
    S position(Vector2 position);

    /**
     * Set the entity's rotation to the desired radians.
     * 
     * @param radians is the absolute rotation of the entity in radians
     * @return the builder
     */
    S rotation(float radians);

    /**
     * Set the entity's scale to the desired {@link Vector2} scale factor.
     * 
     * @param scale is the absolute scale of the entity
     * @return the builder
     */
    S scale(Vector2 scale);

    /**
     * Set the entity's relative position to the desired {@link Vector2}. If the
     * entity has no parent, this method acts like {@link #position(Vector2)}.
     * 
     * @param relativePosition is the relative position of the entity
     * @return the builder
     */
    S relativePosition(Vector2 relativePosition);

    /**
     * Set the entity's relative rotation to the desired radians. If the entity has
     * no parent, this method acts like {@link #rotation(float)}.
     * 
     * @param relativeRadians is the relative rotation of the entity in radians
     * @return the builder
     */
    S relativeRotation(float relativeRadians);

    /**
     * Set the entity's relative scale to the desired {@link Vector2} scale factor.
     * If the entity has no parent, it acts like {@link #scale(Vector2)}.
     * 
     * @param relativeScale is the relative scale of the entity
     * @return the builder
     */
    S relativeScale(Vector2 relativeScale);

    /**
     * Set the entity's parent to the specified entity.
     * 
     * @param parent is the parent of the entity being built
     * @return the builder
     */
    S parent(Entity parent);

    /**
     * Build the entity with the desired settings.
     * 
     * @return the built entity
     */
    E build();
}
