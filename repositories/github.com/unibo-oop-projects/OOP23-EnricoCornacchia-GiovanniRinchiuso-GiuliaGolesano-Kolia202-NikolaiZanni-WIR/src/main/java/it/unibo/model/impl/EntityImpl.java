package it.unibo.model.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.common.Pair;
import it.unibo.model.api.Component;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.utilities.EntityType;

/**
 * EntityImpl.
 */
public class EntityImpl implements Entity {
    private final EntityType type;
    private final Set<Component> components;
    private Pair<Double, Double> position;
    private final GamePerformance gamePerformance;
    private Pair<Double, Double> lastPosition;
    /**
     * EntityImpl constructor.
     *
     * @param type     the type of the entity
     * @param position the position of the entity
     * @param gamePerformance the game performance of the entity
     * @param components the components of the entity
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public EntityImpl(final EntityType type, final Pair<Double, Double> position, 
                      final GamePerformance gamePerformance, final Set<Component> components) {
        this.type = type;
        this.components = new HashSet<>();
        this.position = position;
        this.lastPosition = position;
        components.forEach(c -> ((AbstractComponent) c).setEntity(this));
        this.components.addAll(components);
        this.gamePerformance = gamePerformance;
    }
    /**
     * Method to add a component to the entity.
     * @param component the component to add.
     */
    @Override
    public void addComponent(final AbstractComponent component) {
        component.setEntity(this);
        this.components.add(component);
    }
    /**
     * Getter method for the components.
     * @return the components of the entity.
     */
    @Override
    public Set<Component> getComponents() {
        return Collections.unmodifiableSet(this.components);
    }
    /**
     * Getter method for the component.
     * @param componentType the type of the component.
     * @return the component of the entity.
     */
    @Override
    public Optional<Component> getTheComponent(final ComponentType componentType) {
        return this.components.stream().filter(c -> c.getComponent().equals(componentType)).findFirst();
    }
    /**
     * Get the position of the entity.
     */
    @Override
    public Pair<Double, Double> getPosition() {
        return this.position;
    }
    /**
     * Setter method for the position.
     * @param position the new position of the entity.
     */
    @Override
    public void setPosition(final Pair<Double, Double> position) {
        this.position = position;
    }
    /**
     * Returns the last position of the entity.
     * @return a Pair object representing the last position of the entity.
     */
    @Override
    public Pair<Double, Double> getLastPosition() {
        return this.lastPosition;
    }
    /**
     * Sets the last position of the entity.
     * @param lastPosition the last position to set for the entity.
     */
    @Override
    public void setLastPosition(final Pair<Double, Double> lastPosition) {
        this.lastPosition = lastPosition;
    }
    /**
     * Getter method for the type.
     * @return the type of the entity.
     */
    @Override
    public EntityType getEntityType() {
        return this.type;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public GamePerformance getGamePerformance() {
        return this.gamePerformance;
    }
}
