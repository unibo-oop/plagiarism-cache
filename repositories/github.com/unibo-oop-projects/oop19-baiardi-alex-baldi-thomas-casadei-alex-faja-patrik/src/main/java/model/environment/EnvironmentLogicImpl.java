package model.environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.dyn4j.geometry.AABB;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Shape;
import org.dyn4j.geometry.Vector2;
/**
 * 
 */
public class EnvironmentLogicImpl implements EnvironmentLogic {

    private static final double MOVE_DISTANCE = 20;
    private static final int MAX_ITERATION = 1000;
    private AABB bounds;
    private final List<Shape> obstacles = new ArrayList<>();

    /**
     * 
     */
    @Override
    public void move(final Shape shape) {
        final Shape temp = this.translateAroundPosition(shape, shape.getCenter(), MOVE_DISTANCE);
        shape.translate(temp.getCenter().difference(shape.getCenter()));
    }

    /**
     * 
     */
    @Override
    public void addObstacle(final Shape shape) {
        this.obstacles.add(shape);
    }

    /**
     * 
     */
    @Override
    public void setBounds(final double minX, final double minY, final double maxX, final double maxY) {
        this.bounds = new AABB(minX, minY, maxX, maxY);
    }

    /**
     * 
     */
    @Override
    public Shape translateRandomlyInAcceptableArea(final Shape shape) {
        final Random r = new Random();
        double x;
        double y;
        final double shapeWidth = shape.createAABB().getWidth();
        final double shapeHeight = shape.createAABB().getHeight();
        int count = 0;
        Shape temp;
        do {
            temp = new Rectangle(shapeWidth, shapeHeight);
            x = (r.nextDouble() * (this.bounds.getMaxX() - this.bounds.getMinX() - shapeWidth)) + this.bounds.getMinX()
                    + shapeWidth / 2;
            y = (r.nextDouble() * (this.bounds.getMaxY() - this.bounds.getMinY() - shapeHeight)) + this.bounds.getMinY()
                    + shapeHeight / 2;
            temp.translate(x, y);
            count++;
        } while (!isInAcceptableArea(temp) && count < MAX_ITERATION);
        if (count == MAX_ITERATION) {
            throw new IllegalStateException("There isn't a valid position for the given shape" + shape);
        }
        return temp;
    }

    /**
     * 
     */
    @Override
    public Shape translateAroundPosition(final Shape shape, final Vector2 position, final double distance) {
        final Random r = new Random();
        Vector2 newPosition;
        Shape tempRect;
        final int direction = r.nextInt(MAX_ITERATION);
        int tempDirection = direction;
        do {
            tempRect = new Rectangle(shape.createAABB().getWidth(), shape.createAABB().getHeight());
            tempRect.translate(position);
            newPosition = new Vector2(((double) tempDirection / MAX_ITERATION) * 2 * Math.PI);
            tempRect.translate(newPosition.multiply(distance));
            tempDirection = (tempDirection + 1) % MAX_ITERATION;
        } while (!isInAcceptableArea(tempRect) && tempDirection != direction);
        if (tempDirection == direction) {
            tempRect = translateRandomlyInAcceptableArea(shape);
        }
        return tempRect;
    }

    /**
     * Checks if the given shape is in an acceptable area, inside bounds and not over obstacles.
     * @param shape
     *              shape to test
     * @return
     *          true if the shape is inside bounds and not over obstacles,
     *          false otherwise
     */
    private boolean isInAcceptableArea(final Shape shape) {
        final AABB area = shape.createAABB();
        return bounds.contains(area)
                && obstacles.stream().map(o -> o.createAABB()).allMatch(a -> a.getIntersection(area).isDegenerate());
    }

}
