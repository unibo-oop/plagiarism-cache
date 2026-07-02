package ballblast.model.components;

import org.locationtech.jts.math.Vector2D;

/**
 * Adds the ability to move and update the position of a GameObject
 * based on gravity.
 */
public class GravityComponent extends AbstractComponent {

    private static final Vector2D STANDARD_GRAVITY = Vector2D.create(0, 100);
    private static final Vector2D UPWARDS_GRAVITY = Vector2D.create(0, 98.7);

    /**
     * Creates a new GravityComponent instance.
     */
    public GravityComponent() {
        super(ComponentType.GRAVITY);
    }

    @Override
    public final void update(final double elapsed) {
        if (this.isEnabled()) {
            final Vector2D dV = this.getGravity().multiply(elapsed);
            this.getParent().setVelocity(this.getParent().getVelocity().add(dV));
        }
    }

    private boolean isGoingUpwards() {
        return this.getParent().getVelocity().getY() <= 0;
    }

    /**
     * Gets the gravity value.
     * 
     * @return the gravity value.
     */
    public Vector2D getGravity() {
        return this.isGoingUpwards() ? UPWARDS_GRAVITY : STANDARD_GRAVITY;
    }

}
