package it.unibo.jurassiko.model.territory.api;

import java.util.Set;

/**
 * Factory interface to create the game oceans.
 */
public interface OceanFactory {

    /**
     * Produces the game oceans from the configuration file.
     * 
     * @return a copy of the set containing the oceans
     */
    Set<Ocean> createOceans();

}
