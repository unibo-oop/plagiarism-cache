 package model.obstacle;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Rectangle;

import model.world.WorldSettings;

/**
 * The RectangularObstacle class creates a rectangular obstacle from the physical point of view extending the {@link AbstractObstacle} interface.
 */

public class RectangularObstacle extends AbstractObstacle {

    private static final double HEIGHT = 0.02;
    private static final double WIDTH = 0.06;
    private final double height = HEIGHT * WorldSettings.WORLD_WIDTH;
    private final double width = WIDTH * WorldSettings.WORLD_WIDTH;
    private final Double angle;

    /**
     * The constructor of {@link RectangularObstacle} class.
     * @param position of the {@link Obstacle}'s center. 
     * @param angle (in radians).
     */
    public RectangularObstacle(final Pair<Double, Double> position, final double angle) {
        final BodyFixture fix = new BodyFixture(new Rectangle(this.width, this.height));
        this.angle = angle;
        fix.getShape().rotate(Math.toRadians(this.angle));
        super.setBody(fix, position);
        super.measures.addAll(List.of(this.width, this.height, this.angle));
    }

    @Override
    public final ObstacleType getType() {
        return ObstacleType.RECTANGLE;
    }
}
