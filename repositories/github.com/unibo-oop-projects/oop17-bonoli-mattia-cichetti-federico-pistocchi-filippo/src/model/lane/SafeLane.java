package model.lane;

import java.util.LinkedList;
import java.util.List;

import model.obstacle.GameObject;

/**
 * This class represents a lane where there's no obstacle.
 */
public class SafeLane extends AbstractLane {

    /**
     * 
     */
    public SafeLane() {
        super(LaneType.SAFE);
    }

    @Override
    public void update() {

    }

    /**
     * 
     */
    @Override
    public List<GameObject> getObstacle() {
        return new LinkedList<>();
    }

    /**
     * 
     */
    @Override
    public double getSpeed() {
        return 0;
    }

    /**
     * 
     */
    @Override
    public void setSpeed(final double newSpeed) {

    }

}
