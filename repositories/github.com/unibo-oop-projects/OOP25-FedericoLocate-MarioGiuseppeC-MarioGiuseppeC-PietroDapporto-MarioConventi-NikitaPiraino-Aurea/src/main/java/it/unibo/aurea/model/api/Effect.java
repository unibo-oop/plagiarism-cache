package it.unibo.aurea.model.api;

/**
 * Represents which and how a parameter is modified.
 * 
 */
public interface Effect {

    /**
     * Indicates the parameter matched with this effect.
     * 
     * @return the {@code ParameterType} influenced
     */
    ParameterType getParameter();

    /**
     * Indicates the impact of this effect.
     * 
     * @return an {@code int} to add at the level of the parameter
     */
    int getDelta();
}
