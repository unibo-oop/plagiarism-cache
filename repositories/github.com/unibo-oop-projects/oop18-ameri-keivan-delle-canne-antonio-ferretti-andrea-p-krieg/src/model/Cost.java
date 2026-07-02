package model;

import java.util.Map;
import java.util.Optional;

import model.resources.Resource;

/**
 * Cost interface represents a cost. The could be get or could be print using a
 * String. A game object can have a cost.
 */
public interface Cost {

    /**
     * This method can be used to get the cost. If the cost is not present, this
     * means that thing that has this cost is free.
     * 
     * @return the cost.
     */
    Optional<Map<Resource, Integer>> getCost();

    /**
     * This method is used to return the cost to the string version.
     * @return the cost in the form of a string.
     */
    String toString();
}
