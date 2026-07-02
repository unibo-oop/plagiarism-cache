package it.unibo.jurassiko.model.territory.api;

import java.util.Set;

/**
 * Interface that represents a generic area of the board.
 * 
 * @param <T> The type of the specific area (Territory or Ocean)
 */
public interface BoardArea<T extends BoardArea<T>> {

    /**
     * @return the name of the area
     */
    String getName();

    /**
     * @return the name of the neighbouring areas
     */
    Set<String> getNeighbours();

}
