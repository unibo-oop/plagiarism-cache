package model.obstacle;

import java.util.Random;

import model.obstacle.GameObjectImpl.GameObjectType;
import utilities.Constants;

/**
 * This class manages positions of a generic obstacle.
 *
 */
public class ObstaclePostionStrategy implements CentersIterator {

    private static final String ERROR_MESSAGE = "Must use TurtlePositionStrategy to manages turtle centers.";
    private static final double MIN_DISTANCE = 10.0;

    private final GameObjectType obstacleType;
    private final double distance;
    private double numberOfObstacleCreated;
    private double nextCenter;

    /**
     * 
     * @param obstacleType is the type of the obstacle.
     */
    public ObstaclePostionStrategy(final GameObjectType obstacleType) {
        if (obstacleType == GameObjectType.TURTLE) {
            throw new UnsupportedOperationException(ObstaclePostionStrategy.ERROR_MESSAGE);
        }
        this.obstacleType = obstacleType;
        this.distance = new Random().nextInt(10) + MIN_DISTANCE;
        this.numberOfObstacleCreated = 0;
        this.calculateNext();
    }

    /**
     * 
     */
    @Override
    public boolean hasNext() {
        return this.nextCenter < Constants.WORLD_RIGHT_LIMIT;
    }

    /**
     * 
     */
    @Override
    public Double next() {
        if (!this.hasNext()) {
            throw new UnsupportedOperationException();
        }
        final double tmp = nextCenter;
        this.calculateNext();
        return tmp;
    }

    private void calculateNext() {
        this.nextCenter = this.obstacleType.getWidth() / 2 + (this.numberOfObstacleCreated * this.obstacleType.getWidth()) + (this.numberOfObstacleCreated * this.distance);
        this.numberOfObstacleCreated++;
    }

}
