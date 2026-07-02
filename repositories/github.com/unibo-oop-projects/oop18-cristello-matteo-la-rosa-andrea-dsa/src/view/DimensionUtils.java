package view;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * This class handle all the dimension of the project.
 */
public final class DimensionUtils {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final double WIDTHFACTOR = screenSize.getWidth() / 1366;
    private static final double HEIGHTFACTOR = screenSize.getHeight() / 768;

    private DimensionUtils() {

    }

    /**
     * @param dimension
     *                      Dimension to resize.
     * @return Dimension resided of the dimension
     */
    public static Dimension resizeForScreenDimension(final Dimension dimension) {
        final Dimension tmpDimension = new Dimension();
        tmpDimension.setSize(dimension.width * WIDTHFACTOR, dimension.height * HEIGHTFACTOR);
        return tmpDimension;
    }

    /**
     * @param value
     *                  Value to Transform
     * @return The transformed value
     */
    public static Integer xForScreenDimension(final Integer value) {
        return (int) (value * WIDTHFACTOR);
    }

    /**
     * @param value
     *                  Value to Transform
     * @return The transformed value
     */
    public static Integer yForScreenDimension(final Integer value) {
        return (int) (value * HEIGHTFACTOR);
    }

}
