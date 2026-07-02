package model.lane;

import java.util.LinkedList;
import java.util.List;

import model.obstacle.GameObject;
import utilities.Constants;

/**
 * This class is a specialization of class AbstractLaneWithMod.
 * This class represents lanes that have obstacles.
 */
public class LaneWithObstacles extends AbstractLane {

    private final List<GameObject> obstacleSet;
    private double speed;

    /**
     * Constructor.
     * @param obstacleSet lane will have.
     * @param speed lane will have.
     * @param type of lane.
     */
    public LaneWithObstacles(final List<GameObject> obstacleSet, final double speed, final LaneType type) {
        super(type);
        if (type == LaneType.SAFE) {
            throw new IllegalArgumentException();
        }
        this.obstacleSet = obstacleSet;
        this.speed = speed;
    }

    /**
     *
     */
    @Override
    public void update() {
        this.obstacleSet.forEach(o -> {
            if (o.getCenter() >= (Constants.WORLD_RIGHT_LIMIT + o.getWidth() / 2) && speed > 0) {
                o.setCenter(Constants.WORLD_LEFT_LIMIT - o.getWidth() / 2);
            } else if (o.getCenter() <= (Constants.WORLD_LEFT_LIMIT - o.getWidth() / 2) && speed < 0) {
                o.setCenter(Constants.WORLD_RIGHT_LIMIT + o.getWidth() / 2);
            } else {
                o.setCenter(o.getCenter() + this.speed);
            }
        });
    }

    /**
     * @return Defensive copy of obstacles set.
     */
    @Override
    public List<GameObject> getObstacle() {
        return new LinkedList<>(this.obstacleSet);
    }

    /**
     * 
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * 
     */
    @Override
    public void setSpeed(final double speed) {
        this.speed = speed;
    }
}
