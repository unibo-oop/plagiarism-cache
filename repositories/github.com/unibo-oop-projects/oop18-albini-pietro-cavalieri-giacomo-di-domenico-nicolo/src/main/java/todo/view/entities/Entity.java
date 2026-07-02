package todo.view.entities;

import java.util.Optional;

import com.badlogic.gdx.math.Vector2;

import todo.utils.Identifiable;

/**
 * This interface represents an entity that has absolute and relative position,
 * rotation and scale. An entity can be a parent of another entity. In case the
 * entity has no parent, the relative transform is identical to the absolute
 * one. When changing the relationships between entities, their absolute
 * transform remains unchanged.
 */
public interface Entity extends Updateable, Identifiable {
    /**
     * @return the absolute position
     */
    Vector2 getPosition();

    /**
     * @param position is the new absolute position
     */
    void setPosition(Vector2 position);

    /**
     * @return the absolute rotation in radians
     */
    float getRotation();

    /**
     * @param radians is the new absolute rotation
     */
    void setRotation(float radians);

    /**
     * @return the absolute scale
     */
    Vector2 getScale();

    /**
     * @param scale is the new absolute scale
     */
    void setScale(Vector2 scale);

    /**
     * @return the relative position
     */
    Vector2 getRelativePosition();

    /**
     * @param relativePosition is the new relative position
     */
    void setRelativePosition(Vector2 relativePosition);

    /**
     * @return the relative rotation in radians
     */
    float getRelativeRotation();

    /**
     * @param relativeRadians is the new relative rotation in radians
     */
    void setRelativeRotation(float relativeRadians);

    /**
     * Get the relative scale of the entity. If the parent has a scale value of (0,
     * 0), the relative scale is also (0, 0).
     *
     * @return the relative scale
     */
    Vector2 getRelativeScale();

    /**
     * @param relativeScale is the new relative scale
     */
    void setRelativeScale(Vector2 relativeScale);

    /**
     * @return the entity's parent, if present
     */
    Optional<Entity> getParent();

    /**
     * @param parentEntity is the new entity's parent
     */
    void setParent(Entity parentEntity);

    /**
     * Remove the parent (if any) from this entity.
     */
    void removeParent();

    /**
     * Visit this entity with the specified {@link EntityVisitor}.
     *
     * @param visitor is the visitor of the entity
     * @return the value returned by the visitor
     * @param <T> the type of the entity visited
     */
    <T> T visit(EntityVisitor<T> visitor);
}
