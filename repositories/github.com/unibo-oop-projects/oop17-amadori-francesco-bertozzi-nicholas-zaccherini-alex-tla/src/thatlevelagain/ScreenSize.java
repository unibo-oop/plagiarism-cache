package thatlevelagain;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * ScreenSize.
 *
 */
public final class ScreenSize {

    /**
     * lengt of minimum rectangle unit.
     * 
     */
    public static final int NUMBER_BLOCK_WIDTH = 40;
    /**
     * height of minimum rectangle unit.
     * 
     */
    public static final int NUMBER_BLOCK_HEIGHT = 28;
    private static final double SCALA = 0.8;

    private ScreenSize() {
        //not used
    }

    /**
     * This function return a Dimension that contain widthBlock and heightBlock.
     * @return
     *         Dimension that contain widthBlock and heightBlock
     */
    public static Dimension getScala() {
        final Toolkit t = Toolkit.getDefaultToolkit();
        final Dimension sizeScreen = t.getScreenSize();
        final int widthScreen = (int) sizeScreen.getWidth();
        final int heightScreen = (int) sizeScreen.getHeight();
        final int widthApplicationTemp = (int) (widthScreen * SCALA);
        final int heightApplicationTemp = (int) (heightScreen * SCALA);
        final int widthBlock = (int) (widthApplicationTemp / NUMBER_BLOCK_WIDTH);
        final int heightBlock = (int) (heightApplicationTemp / NUMBER_BLOCK_HEIGHT);
        return new Dimension(widthBlock, heightBlock);
    }
}
