package controller.collision;

import model.object.Tank;
import model.utility.Pair;

/**
 * Concrete implementation of {@link Collision} interface.
 */
public class TankWithBorders implements Collision {

    private final Tank tank;
    private final Pair<Double, Double> worldBounds;

    /**
     * Constructor.
     * 
     * @param tank
     *            the colliding {@link Tank}.
     * @param worldBounds
     *            the {@link Model} bounds.
     */
    public TankWithBorders(final Tank tank, final Pair<Double, Double> worldBounds) {
        this.tank = tank;
        this.worldBounds = worldBounds;
    }

    @Override
    public final void manageCollision() {
        if (tank.getPosition().getFirst() + Tank.getDimension().getFirst() >= worldBounds.getFirst()) { // Exceeding
                                                                                                        // right
            tank.getPosition().setFirst(worldBounds.getFirst() - Tank.getDimension().getFirst());
        } else if (tank.getPosition().getFirst() <= 0) { // Exceeding left
            tank.getPosition().setFirst(0.0);
        }
        if (tank.getPosition().getSecond() + Tank.getDimension().getSecond() >= worldBounds.getSecond()) { // Exceeding
                                                                                                           // down
            tank.getPosition().setSecond(worldBounds.getSecond() - Tank.getDimension().getSecond());
        } else if (tank.getPosition().getSecond() <= 0) { // Exceeding up
            tank.getPosition().setSecond(0.0);
        }
    }

}
