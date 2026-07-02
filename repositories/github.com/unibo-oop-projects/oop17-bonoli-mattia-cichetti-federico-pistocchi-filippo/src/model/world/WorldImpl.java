package model.world;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import model.den.Den;
import model.den.DenImpl;
import model.lane.Lane;
import model.lane.LaneType;
import model.lane.LaneWithObstacles;
import model.lane.SafeLane;
import model.obstacle.CentersIterator;
import model.obstacle.GameObject;
import model.obstacle.GameObjectImpl.GameObjectType;
import model.obstacle.GameObjectBaseType;
import model.obstacle.ObstaclePostionStrategy;
import model.obstacle.TurtlePositionStrategy;
import utilities.Constants;

/**
 *
 */
public final class WorldImpl implements World { //NOPMD

    /* 
     * Suppressed warning "Class cannot be instantiated and does not provide any static methods or fields"
     * This is not correct because there's a public constructor so class can be instantiated.
     */

    private static final List<Integer> DEN_CENTERS = new LinkedList<>(Arrays.asList(300, 600, 900, 1200, 1500));
    private static final int FIRST_RIVER_LANE = 8;
    private static final int LAST_STREET_LANE = 6;

    private final List<Lane> lanes;
    private final List<Den> dens;

    /**
     * 
     * @param lanes of the world.
     * @param obstacleReferences are the turtles that can go down.
     */
    private WorldImpl(final List<Lane> lanes) {
        this.lanes = lanes;
        this.dens = WorldImpl.DEN_CENTERS.stream()
                                         .map(p -> new DenImpl(p))
                                         .collect(Collectors.toList());
    }

    /**
     * This is a pattern builder for the class world.
     */
    public static class Builder {

        private final List<Lane> lanes = new LinkedList<>();

        /**
         * Add a lane without obstacles in the world.
         * @return the builder.
         */
        public Builder addSafeLane() {
            if (this.lanes.size() != 0 && this.lanes.size() != LAST_STREET_LANE) {
                throw new UnsupportedOperationException();
            }
            this.lanes.add(new SafeLane());
            return this;
        }

        /**
         * Add a street in the world, only vehicles can pass through the street.
         * 
         * @param speed is the velocity of obstacles.
         * @param obstacleType is the type of obstacles.
         * @param numObstacle is the number of obstacles.
         * @return the builder.
         */
        public Builder addStreet(final double speed, final GameObjectType obstacleType, final int numObstacle) {
            if (this.lanes.size() > LAST_STREET_LANE) {
                throw new UnsupportedOperationException();
            }
            if (obstacleType.getBaseType() != GameObjectBaseType.VEHICLE) {
                throw new IllegalArgumentException();
            }
            this.lanes.add(new LaneWithObstacles(this.createObstacleSet(obstacleType, numObstacle), speed, LaneType.STREET));
            return this;
        }

        /**
         * Add a river with logs in the world.
         * 
         * @param speed is the velocity of obstacles.
         * @param obstacleType is the type of obstacles.
         * @param numObstacle is the number of obstacles.
         * @return the builder.
         */
        public Builder addRiver(final double speed, final GameObjectType obstacleType, final int numObstacle) {
            if (this.lanes.size() < FIRST_RIVER_LANE - 1) {
                throw new UnsupportedOperationException();
            }
            if (obstacleType.getBaseType() != GameObjectBaseType.LOG) {
                throw new IllegalArgumentException();
            }
            this.lanes.add(new LaneWithObstacles(this.createObstacleSet(obstacleType, numObstacle), speed, LaneType.RIVER));
            return this;
        }

        /**
         * Add a river with turtles.
         * @param speed is the velocity of turtles.
         * @param grouppedBy is the number of turtles in a group.
         * @return the builder.
         */
        public Builder addRiverWithTurtle(final double speed, final int grouppedBy) {
            if (grouppedBy == 0 || grouppedBy > 4) {
                throw new IllegalArgumentException();
            }
            this.lanes.add(new LaneWithObstacles(this.createTurtleSet(grouppedBy), speed, LaneType.RIVER));
            return this;
        }

        /**
         * @return the class World builded.
         */
        public World build() {
            if (this.lanes.size() != Constants.WORLD_NUMBER_OF_LANE) {
                throw new UnsupportedOperationException();
            } else {
                return new WorldImpl(this.lanes);
            }
        }

        /**
         * Creates an obstacle set with the following parameters.
         * @param obstacleType
         * @param numObstacle
         * @return the obstacle set.
         */
        private List<GameObject> createObstacleSet(final GameObjectType obstacleType, final int numObstacle) {
            final CentersIterator obstacleStrategy = new ObstaclePostionStrategy(obstacleType);
            final List<GameObject> l = new LinkedList<>();
            for (int i = 0; i < numObstacle && obstacleStrategy.hasNext(); i++) {
                l.add(obstacleType.create(obstacleStrategy.next()));
            }
            return l;
        }

        private List<GameObject> createTurtleSet(final int groupBy) {
            final CentersIterator obstacleStrategy = new TurtlePositionStrategy(groupBy);
            final List<GameObject> l = new LinkedList<>();
            while (obstacleStrategy.hasNext()) {
                l.add(GameObjectType.TURTLE.create(obstacleStrategy.next()));
            }
            return l;
        }

    }

    /**
     * 
     */
    @Override
    public List<Lane> getLane() {
        return this.lanes;
    }

    /**
     * 
     */
    @Override
    public List<Den> getDen() {
        return this.dens;
    }

}
