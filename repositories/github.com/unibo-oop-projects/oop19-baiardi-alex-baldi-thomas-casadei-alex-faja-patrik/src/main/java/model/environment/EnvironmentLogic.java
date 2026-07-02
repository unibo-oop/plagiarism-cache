package model.environment;


import org.dyn4j.geometry.Shape;
import org.dyn4j.geometry.Vector2;

/**
 *  Interface that represent the movement logic.
 */
public interface EnvironmentLogic {

    /**
     * 
     * Moves the given shape in a randomly direction. 
     * @param shape
     *          shape to move
     */
    void move(Shape shape);

    /**
     * Adds an obstacle for the movement.
     * 
     * @param obstacle
     *          the shape that represent the obstacle
     */
    void addObstacle(Shape obstacle);

    /**
     * Sets the area for the movement.
     * 
     * @param minX 
     *          the minimum x coordinates
     * @param minY 
     *          the minimum y coordinates
     * @param maxX 
     *          the maximum x coordinates
     * @param maxY 
     *          the maximum y coordinates
     */
    void setBounds(double minX, double minY, double maxX, double maxY);

    /**
     * Perform the randomly translation of the given shape inside bounds and not over obstacles. 
     * @param shape
     *          the shape to translate
     * @return
     *          new shape translated
     */
    Shape translateRandomlyInAcceptableArea(Shape shape);

    /**
     * Performs the translation of the given shape around a position.
     * @param shape
     *          the shape to translate
     * @param position
     *          the position where translate the shape around
     * @param distance
     *          the distance from the position
     * @return
     *          new shape translated
     */
    Shape translateAroundPosition(Shape shape, Vector2 position, double distance);

}
