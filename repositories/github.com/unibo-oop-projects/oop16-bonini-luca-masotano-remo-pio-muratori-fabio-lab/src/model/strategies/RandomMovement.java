package model.strategies;

import model.utils.Collisions;
import model.utils.Direction;
import model.utils.ModelVariables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.hitbox.HitboxImpl;
import model.hitbox.HitboxCircle;

/**
 * 
 * Defines a random movement strategy. This class change the direction chosen at
 * the rate given as parameter in the constructor.
 *
 */
public class RandomMovement implements MovementStrategy {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = 3266448977818691855L;
    private MovementStrategy ms;
    private final double changeRate;
    private double limitChangeRate;

    /**
     * 
     * @param changeRate
     *            The change rate
     */
    public RandomMovement(final double changeRate) {
        this.changeRate = changeRate;
        this.limitChangeRate = 0;
    }

    @Override
    public HitboxImpl movement(final HitboxCircle h, final double steps, final double delta) {
        final Collection<Direction> c = new ArrayList<>();
        ModelVariables.getEnemies().forEach(t -> {
            if (!t.getHitbox().equals(h) || t.getHitbox().getX() != h.getX()) {
                c.addAll(Collisions.entityCollision(t.getHitbox(), h));
            }

        });
        c.addAll(Collisions.entityCollision(ModelVariables.getPlayerHitbox(), h));

        if (this.limitChangeRate <= 0 || Collisions.checkBoundaryCollision(h) || !c.isEmpty()) {
            final List<Direction> d = Direction.getRandomDirections();
            d.removeAll(c);

            if (d.isEmpty()) {
                this.ms = new Stactionary();
            } else {
                ms = new BulletMovement(d.get(0).getAngle());
            }
            this.limitChangeRate = changeRate;
        } else {
            this.limitChangeRate -= delta;
        }

        return ms.movement(h, steps, delta);

    }

}
