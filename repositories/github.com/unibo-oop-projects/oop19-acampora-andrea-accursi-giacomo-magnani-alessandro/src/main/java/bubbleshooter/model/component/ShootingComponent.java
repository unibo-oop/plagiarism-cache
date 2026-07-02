package bubbleshooter.model.component;

import bubbleshooter.model.bubble.Bubble;
import javafx.geometry.Point2D;

/**
 * The {@link Component} of the {@link Bubble} which allow it to move.
 */
public class ShootingComponent extends AbstractComponent {

    /**
     * The speed of the shootingBubble in the game.
    */
    private static final double BUBBLESPEED = 0.7;
    private Point2D direction;

    /**
     * Method to set the container and the type.
     * @param container The {@link Bubble} which contains this component.
     */
    public ShootingComponent(final Bubble container) {
        super(container);
        this.direction = container.getPosition();
        this.setType(ComponentType.SHOOTINGCOMPONENT);
    }

    @Override
    public final void update(final double elapsed) {
        if (!this.getContainer().getPosition().equals(this.direction)) {
            this.moveBubble(elapsed);
          }
    }

    /**
     * Method to move and change the position of the {@link Bubble}.
     * @param elapsed the time of the loop's cycle of the engine used to not link the velocity with the FPS.
     */
    private void moveBubble(final double elapsed) {
        this.getContainer().setPosition(this.getContainer().getPosition().add(this.direction.multiply(elapsed).multiply(BUBBLESPEED)));
    }

    /**
     * @param direction The shooting direction of the {@link Bubble}.
     */
    public final void setDirection(final Point2D direction) {
        this.direction = direction;
    }

    /**
     * 
     * @return the shooting direction of the {@link Bubble}.
     */
    public final Point2D getDirection() {
        return this.direction;
    }
}
