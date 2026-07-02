package aboidsim.model;

import java.util.List;

import aboidsim.util.Pair;

/**
 *
 * Model Interface.
 *
 */
public interface Model {

    /**
     *
     * @return the environment of the simulation.
     */

    Environment getSimulation();

    /**
     *
     * @return get all possible levels.
     */
    List<String> getEntitiesNames();

    /**
     *
     * @return a list of active rules.
     */
    List<String> getRulesNames();
    
    /**
     * 
     * @return all the environment names.
     */
    List<String> getAllEnvironmentsNames();
    
    /**
     * @return get all levels and relative images.
     */
    List<Pair<Integer, String>> getLevelAndImages();
}
