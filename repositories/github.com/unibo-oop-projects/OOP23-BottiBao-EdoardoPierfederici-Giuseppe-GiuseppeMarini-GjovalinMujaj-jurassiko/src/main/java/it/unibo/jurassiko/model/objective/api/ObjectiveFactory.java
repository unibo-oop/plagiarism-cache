package it.unibo.jurassiko.model.objective.api;

import java.util.Set;

/**
 * Factory interface to create the game objectives.
 */
public interface ObjectiveFactory {

    /**
     * Produces the game objectives from the configuration file.
     * 
     * @return a copy of the set containing the objectives
     */
    Set<Objective> createObjectives();

}
