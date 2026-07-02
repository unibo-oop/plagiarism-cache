package it.unibo.modularcheckers.util;

import java.util.Iterator;

import it.unibo.modularcheckers.model.Coordinate;

/**
 * A factory that generates Coordinates. 
 */
public interface CoordinateFactory extends Iterator<Coordinate> {

    /**
     * Resets the factory to its initial state.
     */
    void reset();

}
