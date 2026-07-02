package model.obstacle;

import org.apache.commons.lang3.tuple.Pair;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Circle;

import model.world.WorldSettings;

/*
 * The CircularObstacle class creates a circular obstacle from the physical point of view by extending the {@link AbstractObstacle} class.
 */

public class CircularObstacle extends AbstractObstacle {

    private static final double DEFAULT_RADIUS = 0.01;
    private final Double radius = WorldSettings.WORLD_HEIGHT * DEFAULT_RADIUS;

    /**
     * The constructor of {@link CircularObstacle} class.
     * @param position of the {@link Obstacle}'s center. 
     */
    public CircularObstacle(final Pair<Double, Double> position) {
        final BodyFixture fix = new BodyFixture(new Circle(this.radius));
        super.setBody(fix, position);
        super.measures.add(radius);
    }

    @Override
    public final ObstacleType getType() {
        return ObstacleType.CIRCLE;
    }
}
