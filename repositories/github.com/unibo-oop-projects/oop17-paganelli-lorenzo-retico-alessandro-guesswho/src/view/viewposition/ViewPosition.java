package view.viewposition;

import java.awt.*;
import utilities.Utilities;

/***/
public enum ViewPosition {

    /***/
    CENTER(Utilities.getScreenDimension(), new Point()),
    /***/
    LEFT(splittedScreen(), new Point()),
    /***/
    RIGHT(splittedScreen(), new Point(splittedScreen().width, 0));

    private final Dimension dimension;
    private final Point point;

    ViewPosition(final Dimension dimension, final Point point) {
        this.point = point;
        this.dimension = dimension;
    }

    Dimension getDimension() {
        return dimension;
    }

    Point getPoint() {
        return point;
    }

    private static Dimension splittedScreen() {
        final Dimension dimension = Utilities.getScreenDimension();
        return new Dimension(dimension.width / 2, dimension.height);
    }

}
