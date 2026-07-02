package view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BirdViewImpl implements BirdView {

    private final Rectangle r;

    /**
     * This is the constructor method that initialize the rectangle.
     */
    public BirdViewImpl() {
        r = new Rectangle();
    }

    @Override
    public final void setPosition(final double x, final double y) {
        r.setX(x);
        r.setY(y);
    }

    @Override
    public final void setWidthHeight(final int height, final int width) {
        r.setHeight(height);
        r.setWidth(width);
    }

    @Override
    public final void setImage(final String image) {
        r.setFill(new ImagePattern(new Image(image)));
    }

    @Override
    public final void updatePosition(final double y) {
        r.setY(y);
    }

    @Override
    public final Rectangle getBird() {
        return this.r;
    }
}
