package models;

import java.util.Set;

/**
 * The SpawnStrategy Interface give contracts in order to
 * create any kind of implementations for a strategy to spawn entities.
 * This follows Strategy Pattern to give the program more extensibility and removing redundant usage of code
 */
public interface SpawnStrategy {

    /**
     * Get a specific number of locations where entities of a certain type will be located.
     * 
     * @param width The width boundary of the map
     * @param height The height boundary of the map
     * @param numPoints The number of points to generate
     * @return A set of points that will be used to locate a type of entities inside the map
     */
    Set<Point2D> getSpawnPoints(int width, int height, int numPoints);

    /**
     * Checks if the map is big enough to contain the disposed number of entities.
     * 
     * @param boardDimension The size of the game map
     * @param numPoints The number of entities to add in the map
     * @return Whether the size is too big or too small for said number
     */
    boolean checkNumPoints(int boardDimension, int numPoints);

    /**
     * This is used to check if there are duplicates inside sets of points created for different type of
     * entities. It functions in a similar way to 2-Way Combinator Pattern by combining two previously
     * created sets into a new one that does not contain duplicates.
     * 
     * @param width The width boundary of the map
     * @param height The height boundary of the map
     * @param points1 Set of points for the first type of entity
     * @param points2 Set of points for the second and different type of entity
     * @return A new set of points containing both the first and the second set of points without duplicates,
     * which are instead substituted by newly generated points.
     */
    Set<Point2D> getDoubleSpawnPoints(int width, int height, Set<Point2D> points1, Set<Point2D> points2);
}
