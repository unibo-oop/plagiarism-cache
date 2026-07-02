package it.unibo.model.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.Component;
import it.unibo.model.api.Entity;

/**
 * This is an abstract class that implements the Component interface.
 * It provides implementations for the common methods of different types of Components.
 */
public abstract class AbstractComponent implements Component {

    private Entity entity;

    /**
     * Returns the entity associated with this component.
     *
     * @return the entity associated with this component.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * Sets the entity associated with this component.
     *
     * @param entity the entity to set.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public final void setEntity(final Entity entity) {
        this.entity = entity;
    }
}
