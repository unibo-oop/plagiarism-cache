package spacesurvival.utilities;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.nio.file.FileSystems;

public final class SystemVariables {
    /**
     * Detect the default screen device.
     */
    public static final GraphicsDevice GRAPHICS_DEVICE =  GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    /**
     * Detect the size of the screen in pixel.
     */
    public static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Factor scaling x for retina displays. If not retina is one and if is retina is two.
     */
    public static final double SCALE_X = GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice().getDefaultConfiguration()
                    .getDefaultTransform().getScaleX();

    /**
     * Factor scaling y for retina displays. If not retina is one and if is retina is two.
     */
    public static final double SCALE_Y = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice().getDefaultConfiguration()
            .getDefaultTransform().getScaleY();

    /**
     * Path separator for multiplatform.
     */
    public static final String PATH_SEPARATOR = FileSystems.getDefault().getSeparator();

    private SystemVariables() {

    }
}
