package model.entities.survivor;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.RectBoundingBox;
import model.physics.physics_entities.PhysicsBaseSurvivor;

/**
 * Concrete factory implementation for creating {@link Survivor}.
 * <p>
 * This factory encapsulates the creation logic of survivor objects with
 * predefined attributes such as health, size, velocity, physics behavior,
 * and bounding box for collisions.
 */
public class SurvivorFactory implements ISurvivorFactory {

    /**
     * Creates a new instance of a common {@link Survivor} with default attributes.
     * <p>
     * The survivor is initialized with:
     * <ul>
     * <li>Health: 1000</li>
     * <li>Width: 90</li>
     * <li>Height: 185</li>
     * <li>Velocity: (200.0, 0.0)</li>
     * <li>Physics: {@link PhysicsBaseSurvivor}</li>
     * <li>Bounding Box: {@link RectBoundingBox} computed from position and
     * size</li>
     * </ul>
     *
     * @param pos The initial position of the survivor (top-left corner) as a pair
     *            (x, y)
     * @return A fully initialized {@link Survivor} instance
     */
    public Survivor createCommonSurvivor(final Pair<Double, Double> pos) {
        final int live = 5000;
        final int width = 90;
        final int height = 185;
        final Pair<Double, Double> vel = Pair.of(200.0, 0.0);
        return new Common(live,
                width, height,
                new MutablePair<>(pos.getLeft(), pos.getRight()),
                new MutablePair<>(vel.getLeft(), vel.getRight()),
                new PhysicsBaseSurvivor(),
                new RectBoundingBox(Pair.of(pos.getLeft(), pos.getRight() + height),
                        Pair.of(pos.getLeft() + width, pos.getRight())));
    }

}
