package it.unibo.jurassiko.model.borders.api;

import java.util.Set;

import it.unibo.jurassiko.model.territory.api.Ocean;
import it.unibo.jurassiko.model.territory.api.Territory;

/**
 * Interface of BorderImpl class.
 */
public interface Border {

    /**
     * Get a Set of String, territory Name, that is Adj to the input terr.
     * 
     * @param terr  input territory
     * @param ocean input ocean
     * @return a Set of String containg the name of the territory thats Adj to terr
     */
    Set<String> getTerritoriesBorder(Territory terr, Ocean ocean);
}
