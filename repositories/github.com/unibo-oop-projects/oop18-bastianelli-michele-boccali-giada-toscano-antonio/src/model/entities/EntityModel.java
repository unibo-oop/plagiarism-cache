package model.entities;

import org.jbox2d.common.Vec2;

import enumerators.EntityType;
import enumerators.SpecificType;
import model.components.Component;
import model.physics.PhysicEntity;
import model.physics.Size2D;

/**
 * Represents a base interface for each game object to be create into the game.
 */
public interface EntityModel {

    /**
     * @return the {@link EntityType} of the entity
     */
    EntityType getEntityType();

    /**
     * @return the {@link PhysicEntity}
     */
    PhysicEntity getPhysicEntity();

    /**
     * Add a component (a behavior) to the entity.
     * 
     * @param component is the {@link Component} that must be contained into the
     *                  entity
     */
    void add(Component component);

    /**
     * Get the requested component.
     * 
     * @param component the component Class
     * @return the requested component
     */
    <C extends Component> C getComponent(Class<C> component);

    /**
     * Check if the entity model contain this component.
     * 
     * @param component the component class to check
     * @return true if contained, false otherwise
     */
    <C extends Component> boolean contain(Class<C> component);

    /**
     * Update all entity components.
     */
    void update();

    /**
     * Get the physic position of the entity.
     * 
     * @return a the point of the physic position of the entity
     */
    Vec2 getPhysicPosition();

    /**
     * Get the dimension of the entity.
     * 
     * @return the dimension of the entity
     */
    Size2D getDimension();

    /**
     * @return the specific entity type
     */
    SpecificType getSpecificType();

    /**
     * @return the top side position of the entity
     */
    float getTopSide();

    /**
     * @return the left side position of the entity
     */
    float getLeftSide();

    /**
     * @return the bottom side position of the entity
     */
    float getBottomSide();

    /**
     * @return the right side position of the entity
     */
    float getRightSide();

    /**
     * @return the center of the entity box
     */
    Vec2 getCenter();

    /**
     * Destroy the entity model.
     */
    void destroyModel();

    /**
     * If the entity doesn't contain a Life component it means the entity can't die.
     * 
     * @return true if the entity is alive, false otherwise.
     */
    boolean isAlive();

}
