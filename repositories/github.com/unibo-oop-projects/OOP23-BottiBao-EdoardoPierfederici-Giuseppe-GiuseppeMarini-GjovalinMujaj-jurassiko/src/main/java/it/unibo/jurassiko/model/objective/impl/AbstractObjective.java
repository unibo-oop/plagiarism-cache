package it.unibo.jurassiko.model.objective.impl;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;

import it.unibo.jurassiko.model.objective.api.Objective;

/**
 * Abstract class implementing common functionality for game objectives.
 */
public abstract class AbstractObjective implements Objective, Serializable {

    private static final long serialVersionUID = -1279945186700168473L;
    private static final String DEFAULT_OBJECTIVE_DESCRIPTION = "Conquista 12 territori.";

    private String description = "";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void writeDescription();

    /**
     * {@inheritDoc}
     */
    @Override
    public Objective getClone() {
        return SerializationUtils.clone(this);
    }

    /**
     * Sets the description of the objective.
     * 
     * @param description the objective description
     */
    protected void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the description of the default objective
     */
    protected String getDefaultObjectiveDescription() {
        return DEFAULT_OBJECTIVE_DESCRIPTION;
    }

}
