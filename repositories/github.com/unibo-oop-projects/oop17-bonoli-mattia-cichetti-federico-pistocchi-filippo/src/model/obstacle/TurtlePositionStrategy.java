package model.obstacle;

import model.obstacle.GameObjectImpl.GameObjectType;
import utilities.Constants;

/**
 * 
 *
 */
public class TurtlePositionStrategy implements CentersIterator {

    private static final double BASE_DISTANCE = 1.5;

    private final int groupBy;
    private final double distance;

    private int multiplier;

    private int numberOfGroup;
    private int calculatedOfGroup;

    private double nextCenter;

    /**
     * @param groupBy number of turtle in a group.
     */
    public TurtlePositionStrategy(final int groupBy) {
        this.groupBy = groupBy;
        this.distance = BASE_DISTANCE * groupBy;
        this.multiplier = 1;
        this.numberOfGroup = 1;
        this.calculatedOfGroup = 0;
    }

    /**
     * 
     */
    @Override
    public boolean hasNext() {
        if (this.calculatedOfGroup == 0) {
            return (GameObjectType.TURTLE.getWidth() / 2 * (multiplier + (2 * (groupBy - 1))) + distance * (numberOfGroup - 1)) + GameObjectType.TURTLE.getWidth() / 2 <= Constants.WORLD_RIGHT_LIMIT;
        } else {
            return true;
        }
    }

    /**
     * 
     */
    @Override
    public Double next() {
        if (!hasNext()) {
            throw new UnsupportedOperationException();
        }
        this.calculateNext();
        return this.nextCenter;
    }

    private void calculateNext() {
        this.nextCenter = GameObjectType.TURTLE.getWidth() / 2 * (multiplier) + distance * (numberOfGroup - 1);
        this.calculatedOfGroup++;
        this.multiplier += 2;
        if (this.calculatedOfGroup == groupBy) {
            this.numberOfGroup++;
            this.calculatedOfGroup = 0;
        }
    }

}
