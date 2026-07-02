package view.animations;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Class that manages sprite's animations.
 *
 */
public final class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;
    private int lastIndex;

    /**
     * Constructor that creates sprite's animations from an image.
     * 
     * @param imageView
     *            image that contains all animation's frames
     * @param duration
     *            of the animation
     * @param cycleCount
     *            how many times the animation repeats itself
     * @param count
     *            number of frames
     * @param columns
     *            number of columns in which the image has to be divided
     * @param offsetX
     *            offset on the x axis
     * @param offsetY
     *            offset on the y axis
     * @param width
     *            of the frame
     * @param height
     *            of the frame
     */
    public SpriteAnimation(final ImageView imageView, final Duration duration, final int cycleCount, final int count,
            final int columns, final int offsetX, final int offsetY, final int width, final int height) {
        super();

        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.setCycleDuration(duration);
        this.setCycleCount(cycleCount);
        this.setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(final double k) {
        final int index = Math.min((int) Math.floor(k * this.count), this.count - 1);
        if (index != this.lastIndex) {
            final int x = (index % this.columns) * this.width + this.offsetX;
            final int y = (index / this.columns) * this.height + this.offsetY;
            this.imageView.setViewport(new Rectangle2D(x, y, this.width, this.height));
            this.lastIndex = index;
        }
    }
}
