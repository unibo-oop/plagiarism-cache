package it.unibo.jurassiko.model.territory.api;

import java.util.Set;

/**
 * Factory interface to create the game territories.
 */
public interface TerritoryFactory {

    /**
     * Produces the game territories from the configuration file.
     * 
     * @return a copy of the set containing the territories
     */
    Set<Territory> createTerritories();

}
