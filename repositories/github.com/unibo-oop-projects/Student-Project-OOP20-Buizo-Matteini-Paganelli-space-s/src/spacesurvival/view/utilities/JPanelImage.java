package spacesurvival.view.utilities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import spacesurvival.utilities.dimension.Screen;
import spacesurvival.utilities.path.Background;

/**
 * Implementation of a class that extends a JPanel but with the addition of a background with the image.
 */
public class JPanelImage extends JPanel {
    private static final long serialVersionUID = -6567092970311808627L;

    private final ImageIcon icon;

    /**
     * Create a panelImage with a default transparent background.
     */
    public JPanelImage() {
        super();
        this.icon = new ImageIcon(Background.TRANSPARENT);
        JPanelImage.setSizeFromScreen(this.icon, Screen.RECTANGLE_FULLSCREEN);
    }

    /**
     * Set the background image.
     * @param path of image
     * @param dimension for image
     */
    public void setImage(final String path, final Dimension dimension) {
        this.icon.setImage(JImage.getImageFromPath(path));
        JImage.resizeImageIcon(this.icon, dimension);
        this.repaint();
    }

    /**
     * Set bound from rectangle and repaint component.
     */
    @Override
    public void setBounds(final Rectangle screen) {
        super.setBounds(screen);
        JPanelImage.setSizeFromScreen(this.icon, screen);
        super.repaint();
    }

    /**
     * Paint component with background image.
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.icon.getImage(), 0, 0, null);
    }

    /**
     * Resize image from rectangle.
     * @param image for resize and position
     * @param rectangleGUI is size for image.
     */
    public static void setSizeFromScreen(final ImageIcon image, final Rectangle rectangleGUI) {
        JImage.resizeImageIcon(image, rectangleGUI.getSize());
    }

    /**
     * Described JPanelImage.
     */
    @Override
    public String toString() {
        return "JPanelImage [icon=" + icon + "]";
    }

}
