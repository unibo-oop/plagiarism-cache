package it.unibo.jurassiko.model.territory.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.unibo.jurassiko.model.territory.impl.TerritoryImpl;

/**
 * Interface that represents a game Territory.
 */
@JsonDeserialize(as = TerritoryImpl.class)
public interface Territory extends BoardArea<Territory> {

    /**
     * Returns the continent to which the territory belongs.
     * 
     * @return the name of the continent
     */
    String getContinent();

}
