package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Represents label with image and his manage resize.
 *
 */
@SuppressWarnings("serial")
public class MyLabel extends JLabel {
    private static final double SCALEIMAGE_PROP = 0.65;
    private Image myimage;
    private final Image defaultImg;

    /**
     * @param icon
     *            label icon
     */
    public MyLabel(final ImageIcon icon) {
        super();
        this.setIcon(icon);
        defaultImg = icon.getImage();
        myimage = icon.getImage();
    }

    /**
     * @param image
     *            overlayimage
     */
    public void addOverlayImage(final ImageIcon image) {
        final ImageIcon defaultImage = new ImageIcon(myimage);
        final ImageIcon img = new ImageIcon(
                image.getImage().getScaledInstance((int) (defaultImage.getIconWidth() * SCALEIMAGE_PROP),
                        (int) (defaultImage.getIconHeight() * SCALEIMAGE_PROP), Image.SCALE_DEFAULT));
        final BufferedImage combined = new BufferedImage(defaultImage.getIconWidth(), defaultImage.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        final Graphics g = combined.getGraphics();
        g.drawImage(defaultImage.getImage(), 0, 0, null);
        g.drawImage(img.getImage(), (defaultImage.getIconWidth() - img.getIconWidth()) / 2,
                (defaultImage.getIconHeight() - img.getIconHeight()) / 2, null);
        myimage = combined;
    }

    /**
     * 
     */
    public void resetIcon() {
        myimage = defaultImg;
    }

    /**
     * @param g
     *            label graphic
     */
    @Override
    public void paint(final Graphics g) {
        g.drawImage(myimage, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}