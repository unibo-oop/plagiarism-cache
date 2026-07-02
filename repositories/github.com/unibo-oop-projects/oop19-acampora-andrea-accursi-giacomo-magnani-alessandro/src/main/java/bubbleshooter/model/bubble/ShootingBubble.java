package bubbleshooter.model.bubble;

import bubbleshooter.model.component.CollisionComponent;
import bubbleshooter.model.component.ComponentType;
import bubbleshooter.model.component.ShootingComponent;
import javafx.geometry.Point2D;

/**
 * Class used to manage the bubble which will be shot to the grid.
 *
 */
public class ShootingBubble extends AbstractBubble {

    /**
     * 
     * @param position The position in the game.
     * @param color    The {@link BubbleColor} of the bubble.
     */
    public ShootingBubble(final Point2D position, final BubbleColor color) {
        super(BubbleType.SHOOTING_BUBBLE, position, color);
    }

    @Override
    protected final void setComponents() {
        this.addComponent(new ShootingComponent(this));
        this.addComponent(new CollisionComponent(this));
    }

    @Override
    public final void update(final double elapsed) {
        this.getComponent(ComponentType.SHOOTINGCOMPONENT).get().update(elapsed);
    }
}
