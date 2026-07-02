package it.unibo.model.api;

import java.util.Optional;
import java.util.Set;
import it.unibo.common.Pair;
import it.unibo.model.impl.AbstractComponent;
import it.unibo.utilities.EntityType;
/**
 * Entity.
 */
public interface Entity {
    /**
     * Returns the set of components associated with this entity.
     *
     * @return the set of components
     */
    Set<Component> getComponents();
    /**
     * Returns the component of the specified type associated with this entity.
     *
     * @param componentType the type of the component to retrieve
     * @return the component of the specified type, if present
     */
    Optional<Component> getTheComponent(ComponentType componentType);
    /**
     * Add a component to the entity.
     *
     * @param component the component to add
     */
    void addComponent(AbstractComponent component);
    /**
     * Get the position of the entity.
     *
     * @return the position of the entity as a Pair of Doubles representing the x and y coordinates
     */
    Pair<Double, Double> getPosition();
    /**
     * Set the position of the entity.
     *
     * @param position the new position of the entity as a Pair of Doubles representing the x and y coordinates
     */
    void setPosition(Pair<Double, Double> position);

    /**
     * Returns the last position of the entity.
     *
     * @return a Pair object representing the last position of the entity, where the first element is the x-coordinate
     *         and the second element is the y-coordinate.
     */
    Pair<Double, Double> getLastPosition();
    /**
     * Sets the last position of the entity.
     *
     * @param lastPosition the last position to set for the entity
     */
    void setLastPosition(Pair<Double, Double> lastPosition);
    /**
     * Get the type of an entity.
     * 
     * @return the type of the entity
     */
    EntityType getEntityType();
    /**
     * Get the game performance of the entity.
     * 
     * @return the game performance of the entity
     */
    GamePerformance getGamePerformance();
}
