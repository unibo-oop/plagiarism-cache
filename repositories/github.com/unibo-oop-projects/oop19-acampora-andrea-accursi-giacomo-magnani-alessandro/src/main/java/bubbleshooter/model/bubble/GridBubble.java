package bubbleshooter.model.bubble;

import bubbleshooter.model.component.CollisionComponent;
import javafx.geometry.Point2D;

/**
 * Class used to manage the {@link Bubble} in the game's grid.
 *
 */
public class GridBubble extends AbstractBubble {

    /**
     * @param position The position on the grid.
     * @param color    The {@link BubbleColor} of the bubble.
     */
    public GridBubble(final Point2D position, final BubbleColor color) {
        super(BubbleType.GRID_BUBBLE, position, color);
    }

    @Override
    protected final void setComponents() {
        this.addComponent(new CollisionComponent(this));
    }
}
