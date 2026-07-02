package bubbleshooter.model.bubble;

import javafx.geometry.Point2D;

/**
 * Class used for the Switch feature.
 *
 */
public class SwitchBubble extends AbstractBubble {

    /**
     * 
     * @param position The position in the game.
     * @param color    The {@link BubbleColor} of the bubble.
     */
    public SwitchBubble(final Point2D position, final BubbleColor color) {
        super(BubbleType.SWITCH_BUBBLE, position, color);
    }

    /**
     * It has no component because it's useful only as {@link SwitchBubble} and it
     * has no functions in the game.
     */
    @Override
    protected void setComponents() {
    }
}
