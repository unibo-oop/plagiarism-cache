package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * Represents the buttons used to select character.
 *
 */
@SuppressWarnings("serial")
public class CharacterButton extends MyButton {
    private static final double TOLLERANCE_PROP = 0.01;
    private final ImageIcon overlayIcon;
    private final ImageIcon defaultIconImg;

    /**
     * @param imgIcon
     *            The button icon
     * @param overlayIcon
     *            the overlay icon
     * @param containerDim
     *            the dimension of buttons grid (used for calculate characters
     *            icon dimension)
     * @param imageNumberWidth
     *            number of images in a row (used for calculate characters icon
     *            dimension)
     * @param imageNumberHeight
     *            number of images in a column (used for calculate characters
     *            icon dimension)
     */
    public CharacterButton(final ImageIcon imgIcon, final ImageIcon overlayIcon, final Dimension containerDim,
            final int imageNumberWidth, final int imageNumberHeight) {
        super();
        final int newWidth = (int) (containerDim.getWidth() / imageNumberWidth
                - containerDim.getWidth() * TOLLERANCE_PROP);
        final int newHeight = (int) (containerDim.getHeight() / imageNumberHeight
                - containerDim.getHeight() * TOLLERANCE_PROP);
        final ImageIcon icon = getScaledIcon(imgIcon, newWidth, newHeight);
        this.defaultIconImg = icon;
        setPreferredSize(new Dimension(newWidth, newHeight));
        setIcon(icon);
        this.overlayIcon = new ImageIcon(overlayIcon.getImage().getScaledInstance(icon.getIconWidth(),
                icon.getIconHeight(), Image.SCALE_DEFAULT));
    }

    /**
     * Adds cross icon to a button when the character is deleted.
     */
    public void addOverlayImage() {
        final ImageIcon defaultImage = (ImageIcon) getIcon();
        final BufferedImage combined = new BufferedImage(defaultImage.getIconWidth(), defaultImage.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        final Graphics g = combined.getGraphics();
        g.drawImage(defaultImage.getImage(), 0, 0, null);
        g.drawImage(overlayIcon.getImage(), 0, 0, null);
        setIcon(new ImageIcon(combined));
    }

    /**
     * Reset the initial image to the button.
     */
    public void resetIconImg() {
        setIcon(defaultIconImg);
    }

}
