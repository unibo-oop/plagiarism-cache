package model.entities.zombie;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.RectBoundingBox;
import model.physics.physics_entities.PhysicsBaseZombie;

/**
 * Concrete factory class for creating {@link Zombie} instances.
 * <p>
 * Implements the {@link IZombieFactory} interface, providing
 * methods to create specific types of zombies with predefined attributes.
 * </p>
 */
public class ZombieFactory implements IZombieFactory {

    /**
     * Creates a new Clicker-type zombie at the specified position.
     * The Clicker is initialized with:
     * <ul>
     * <li>Health: 1000</li>
     * <li>Attack damage: 20</li>
     * <li>Width: 90</li>
     * <li>Height: 230</li>
     * <li>Velocity: (100.0, 0.0)</li>
     * </ul>
     * The bounding box is constructed based on the zombie's position and size.
     *
     * @param pos the initial position of the zombie as a pair (x, y)
     * @return a new instance of {@link Clicker}
     */
    @Override
    public Zombie createClickerZombie(final Pair<Double, Double> pos) {
        final int live = 1000;
        final int attack = 5;
        final int width = 90;
        final int height = 230;
        final Pair<Double, Double> vel = Pair.of(100.0, 0.0);
        return new Clicker(live, attack,
                width, height,
                new MutablePair<>(pos.getLeft(), pos.getRight()),
                new MutablePair<>(vel.getLeft(), vel.getRight()),
                new PhysicsBaseZombie(),
                new RectBoundingBox(Pair.of(pos.getLeft(), pos.getRight() + height),
                        Pair.of(pos.getLeft() + width, pos.getRight())));
    }
}
