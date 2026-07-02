package view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import util.Pair;

public class TubeViewImpl implements TubeView {

    private Rectangle r;
    private Rectangle r2;

    /**
     * This is the constructor method that initialize the first pair of rectangle.
     */
    public TubeViewImpl() {
        r = new Rectangle();
        r2 = new Rectangle();
    }

    @Override
    public final void createRectangles() {
        r = new Rectangle();
        r2 = new Rectangle();
    }

    @Override
    public final void setTubeUpPosition(final double x, final double y) {
        r.setX(x);
        r.setY(y);
    }

    @Override
    public final void setTubeUpDimension(final double width, final double height) {
        r.setWidth(width);
        r.setHeight(height);
    }

    @Override
    public final void setTubeDownPosition(final double x, final double y) {
        r2.setX(x);
        r2.setY(y);
    }

    @Override
    public final void setTubeDownDimension(final double width, final double height) {
        r2.setWidth(width);
        r2.setHeight(height);
    }

    @Override
    public final void seTubeUpImage(final String image) {
        r.setFill(new ImagePattern(new Image(image)));
    }

    @Override
    public final void seTubeDownImage(final String image) {
        r2.setFill(new ImagePattern(new Image(image)));
    }

    @Override
    public final Pair<Rectangle, Rectangle> getRectangles() {
        return new Pair<>(r, r2);
    }
}
