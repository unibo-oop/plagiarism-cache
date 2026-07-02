package controller.collision;

import model.object.Projectile;
import model.utility.Direction;
import model.utility.Pair;

/**
 * Concrete implementation of {@link Collision} interface.
 */
public class ProjectileWithBorders implements Collision {

    private final Projectile projectile;
    private final Pair<Double, Double> worldBounds;

    /**
     * Constructor.
     * 
     * @param projectile
     *            the colliding {@link Projectile}.
     * @param worldBounds
     *            the {@link Model} bounds.
     */
    public ProjectileWithBorders(final Projectile projectile, final Pair<Double, Double> worldBounds) {
        this.projectile = projectile;
        this.worldBounds = worldBounds;
    }

    @Override
    public final void manageCollision() {
        try {
            if (projectile.getPosition().getFirst() + projectile.getBounds().getFirst() >= worldBounds.getFirst()) {
                projectile.bounce(Direction.RIGHT);
            } else if (projectile.getPosition().getFirst() <= 0) {
                projectile.bounce(Direction.LEFT);
            } else if (projectile.getPosition().getSecond() + projectile.getBounds().getSecond() >= worldBounds
                    .getSecond()) {
                projectile.bounce(Direction.DOWN);
            } else if (projectile.getPosition().getSecond() <= 0) {
                projectile.bounce(Direction.UP);
            }
        } catch (IllegalStateException e) {
            this.projectile.setDead();
        }
    }

}
