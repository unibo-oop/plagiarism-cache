package clashclass.view.graphic.components;

import clashclass.commons.Rect2D;
import clashclass.commons.Transform2D;
import clashclass.view.graphic.Graphic;

/**
 * Represents a general-purpose progress bar renderer.
 */
public class ProgressBarRendererImpl extends AbstractGraphicComponent {
    private static final float PERCENTAGE_LIMIT_TO_SHOW = 99.0f;
    private final int barWidth;
    private final int yOffset;
    private final int barHeight;
    private final String colorEx;
    private float percentage;

    /**
     * Constructs the progress bar.
     *
     * @param layer the layer
     * @param barWidth the width of the progress bar
     * @param barHeight the height of the progress bar
     * @param yOffset the y offset from the {@link Transform2D} component's position
     * @param colorEx the color of the progress bar
     */
    public ProgressBarRendererImpl(
            final int layer,
            final int barWidth,
            final int barHeight,
            final int yOffset,
            final String colorEx) {
        super(layer);
        this.barWidth = barWidth;
        this.barHeight = barHeight;
        this.yOffset = yOffset;
        this.colorEx = colorEx;
        this.percentage = 100.0f;
    }

    /**
     * Sets the progress bar current percentage.
     *
     * @param percentage the current percentage
     */
    public void setPercentage(final float percentage) {
        this.percentage = percentage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final Graphic graphics) {
        if (this.percentage >= PERCENTAGE_LIMIT_TO_SHOW) {
            return;
        }
        final var position = this.getGameObject()
                .getComponentOfType(Transform2D.class).get().getPosition();
        final var backgroundRect = new Rect2D(
                (int) position.x(),
                (int) position.y() - yOffset,
                this.barWidth,
                this.barHeight
        );
        final var foregroundRect = new Rect2D(
                (int) position.x(),
                (int) (position.y() - yOffset),
                (int) (this.barWidth * this.percentage),
                this.barHeight
        );

        graphics.drawRectangle(this.getGameObject(), "#FFFFFF", backgroundRect);
        graphics.drawRectangle(this.getGameObject(), this.colorEx, foregroundRect);
    }
}
