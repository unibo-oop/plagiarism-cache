package menu.components;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * An extension of the class {@link ImageIcon} that allows you to create icons
 * with an image resized by a real scalar inserted at will and using, if
 * necessary, a specified image-scaling algorithm (See {@link Image}. If the
 * scalar is between 0 and 1, the image will be reduced, if it is greater than 1
 * the image will be enlarged. For the original size the value must be 1. This
 * class also has the task of automatically adjusting the image size previously
 * loaded, in ratio from main monitor. Your image will always be displayed
 * correctly, maintaining the resizing ratio previously entered, regardless of
 * the size of the main monitor used.Images that are created from a URL or
 * filename. For further information see {@link ImageIcon} super class.
 *
 */
public class GameImgIcon extends ImageIcon {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Creates a scaled ImageIcon from the specified URL, using a specified real
     * scalar and using a specified image-scaling algorithm (See {@link Image}
     * class).
     * 
     * @param imgURL the URL pointer to get image "resource".
     * @param scale  the real scalar value to resize the image.
     * @param hints  flags to indicate the type of algorithm to use for image resampling.
     */
    public GameImgIcon(final URL imgURL, final double scale, final int hints) {
        super(imgURL);
        this.scaleGameImageIcon(scale, hints);
    }

    /**
     * Creates a scaled ImageIcon from the specified URL, using a specified real
     * scalar and using a Image.SCALE_SMOOTH image-scaling algorithm (See
     * {@link Image} class).
     * 
     * @param imgURL the URL pointer to get image "resource".
     * @param scale  the real scalar value to resize the image.
     */
    public GameImgIcon(final URL imgURL, final double scale) {
        this(imgURL, scale, Image.SCALE_SMOOTH);
    }

    /**
     * Creates a scaled ImageIcon from the specified file,using a specified real
     * scalar and using a specified image-scaling algorithm (See {@link Image}
     * class).
     * 
     * @param filename the image file name to be load.
     * @param scale    the real scalar value to resize the image.
     * @param hints    flags to indicate the type of algorithm to use for image
     *                 resampling.
     */
    public GameImgIcon(final String filename, final double scale, final int hints) {
        super(filename);
        this.scaleGameImageIcon(scale, hints);
    }

    /**
     * Creates a scaled ImageIcon from the specified file, using a specified real
     * scalar and using a Image.SCALE_SMOOTH image-scaling algorithm (See
     * {@link Image} class).
     * 
     * @param filename the image file name to be load.
     * @param scale    the real scalar value to resize the image.
     */
    public GameImgIcon(final String filename, final double scale) {
        this(filename, scale, Image.SCALE_SMOOTH);
    }

    private void scaleGameImageIcon(final double scale, final int hints) {
        final double screenScale = getScreenAndImageRatio() * scale;
        super.setImage(getImage().getScaledInstance((int) (getIconWidth() * screenScale),
                (int) (getIconHeight() * screenScale), hints));
    }

    private double getScreenAndImageRatio() {
        return (SCREEN_SIZE.getHeight() / getIconHeight());
    }
}
