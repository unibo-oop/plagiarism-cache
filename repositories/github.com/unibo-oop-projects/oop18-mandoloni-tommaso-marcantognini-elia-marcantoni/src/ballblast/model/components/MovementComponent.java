package ballblast.model.components;

import ballblast.model.gameobjects.GameObject;

/**
 * Adds the ability to move and updates the position of a {@link GameObject} based on his velocity.
 */
public class MovementComponent extends AbstractComponent {
    /**
     * Class constructor.
     */
    public MovementComponent() {
        super(ComponentType.MOVEMENT);
    }

    @Override
    public final void update(final double elapsed) {
        if (this.isEnabled()) {
            this.updatePosition(elapsed);
        }
    }

    private void updatePosition(final double elapsed) {
        final GameObject parent = this.getParent();
        parent.setPosition(parent.getVelocity().multiply(elapsed).translate(parent.getPosition()));
    }
}
