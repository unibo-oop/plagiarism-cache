package ballblast.model.gameobjects;

import ballblast.model.components.Component;
import ballblast.model.components.ComponentType;
import ballblast.model.physics.Collidable;

import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.math.Vector2D;

/**
 * Represent a general game's entity.
 */
public interface GameObject {
    /**
     * Gets the {@link GameObject}'s height.
     * 
     * @return the height of game object.
     */
    double getHeight();

    /**
     * Gets the {@link GameObject}'s width.
     * 
     * @return the width of {@link GameObject}.
     */
    double getWidth();

    /**
     * Sets the {@link GameObject}'s velocity.
     * 
     * @param velocity the velocity of the {@link GameObject}.
     */
    void setVelocity(Vector2D velocity);

    /**
     * Gets the {@link GameObject}'s velocity.
     * 
     * @return the velocity of the {@link GameObject}.
     */
    Vector2D getVelocity();

    /**
     * Gets the GameObject position.
     * 
     * @return {@link Coordinate} of the {@link GameObject}.
     */
    Coordinate getPosition();

    /**
     * Sets the {@link GameObject}'s position.
     * 
     * @param position the new {@link Coordinate} of the {@link GameObject}.
     */
    void setPosition(Coordinate position);

    /**
     * Returns a boolean which notifies if the {@link GameObject} is destroyed.
     * 
     * @return true if {@link GameObject} is destroyed, false otherwise.
     */
    boolean isDestroyed();

    /**
     * Destroys the {@link GameObject}.
     */
    void destroy();

    /**
     * Updates {@link GameObject} status.
     * 
     * @param elapsed time elapsed since last update.
     */
    void update(double elapsed);

    /**
     * Adds a {@link Component} to the {@link GameObject}.
     * 
     * @param component {@link Component} to be added.
     */
    void addComponent(Component component);

    /**
     * Removes a specific {@link Component} attached to the {@link GameObject}.
     * 
     * @param type {@link Component} to be removed.
     */
    void removeComponent(ComponentType type);

    /**
     * Gets the {@link List} of all components attached to the {@link GameObject}.
     * 
     * @return the {@link List} of all components attached to the
     *         {@link GameObject}.
     */
    List<Component> getComponents();

    /**
     * Gets the {@link GameObjectType}.
     * 
     * @return the tag which specifies the {@link GameObjectType}.
     */
    GameObjectType getType();

    /**
     * Handles an incoming Collision.
     * 
     * @param collidable the other {@link Collidable}.
     */
    void handleCollision(Collidable collidable);
}
