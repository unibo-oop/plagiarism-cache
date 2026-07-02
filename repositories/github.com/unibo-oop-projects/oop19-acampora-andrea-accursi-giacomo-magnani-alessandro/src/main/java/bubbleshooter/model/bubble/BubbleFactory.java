package bubbleshooter.model.bubble;

import javafx.geometry.Point2D;

/**
 * represents a factory for {@link Bubble}s.
 *
 */
public final class BubbleFactory {

    /**
     * creates new {@link GridBubble}.
     * 
     * @param position the position of the bubble
     * @param color    the color of the bubble
     * @return the {@link GridBubble}
     */
    public Bubble createGridBubble(final Point2D position, final BubbleColor color) {
        return new GridBubble(position, color);
    }

    /**
     * creates new {@link ShootingBubble}.
     * 
     * @param position the position of the bubble
     * @param color    the color of the bubble
     * @return the {@link ShootingBubble}
     */
    public Bubble createShootingBubble(final Point2D position, final BubbleColor color) {
        return new ShootingBubble(position, color);
    }

    /**
     * creates new {@link SwitchBubble}.
     * 
     * @param position the position of the bubble
     * @param color    the color of the bubble
     * @return the {@link SwitchBubble}
     */
    public Bubble createSwitchBubble(final Point2D position, final BubbleColor color) {
        return new SwitchBubble(position, color);
    }
}
