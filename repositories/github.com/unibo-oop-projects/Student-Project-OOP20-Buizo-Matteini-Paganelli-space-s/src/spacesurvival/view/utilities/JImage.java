package spacesurvival.view.utilities;

import spacesurvival.model.EngineImage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * Implements JImage creation extending from a JComponent.
 */
public class JImage extends JComponent {
    private static final long serialVersionUID = -2971663994387728066L;
    private int width, height;
    private String path = "";

    private final ImageIcon imageIcon;
    private JLabel lbImage;

    /**
     * Create JImage.
     */
    public JImage() {
        super();
        super.setLayout(new BorderLayout());
        this.imageIcon = new ImageIcon();
        this.lbImage = new JLabel(this.imageIcon);
        super.add(this.lbImage, BorderLayout.CENTER);
    }

    /**
     * Create JImage from path.
     * @param path of image.
     */
    public JImage(final String path) {
        this();
        this.path = path;
        this.imageIcon.setImage(JImage.getImageFromPath(path));
        final Dimension dim = EngineImage.getSizeFromImage(path);
        this.width = (int) dim.getWidth();
        this.height = (int) dim.getHeight();
    }

    /**
     * Create JImage from path and dimension width and height.
     * @param path of image.
     * @param width of image.
     * @param height of image.
     */
    public JImage(final String path, final int width, final int height) {
        this(path);
        this.setSize(width, height);
    }

    /**
     * Create JImage from path and dimension.
     * @param path of image.
     * @param dimension of image.
     */
    public JImage(final String path, final Dimension dimension) {
        this(path, dimension.width, dimension.height);
    }

    /**
     * Get path of image.
     * @return path of image.
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Get ImageIcon from image.
     * @return imageIcon of image.
     */
    public ImageIcon getImageIcon() {
        return this.imageIcon;
    }

    /**
     * Get size of JImage.
     * @return dimension of JImage.
     */
    @Override
    public Dimension getSize() {
        return new Dimension(this.width, this.height);
    }

    /**
     * Get image from ImageIcon.
     * @return image from imageIcon.
     */
    public Image getImage() {
        return this.imageIcon.getImage();
    }

    /**
     * Set size of JImage from width and height.
     * @param width of JImage.
     * @param height of JImage.
     */
    @Override
    public void setSize(final int width, final int height) {
        super.setSize(width, height);
        this.lbImage.setSize(width, height);
        this.width = width;
        this.height = height;
        JImage.setSizeImageIcon(this.imageIcon, width, height);
        this.repaint();
    }

    /**
     * Set size of JImage from dimension.
     * @param dimension of JImage.
     */
    @Override
    public void setSize(final Dimension dimension) {
        this.setSize(dimension.width, dimension.height);
    }

    /**
     * Set image for JImage.
     * @param path of image.
     */
    public void setImage(final String path) {
        this.path = path;
        this.imageIcon.setImage(JImage.getImageFromPath(path));
        if (this.width != 0) {
            this.setSize(this.width, this.height);
        }
        this.repaint();
    }

    /**
     * Set bounds of JImage from rectangle.
     * @param rectangle assign a JImage.
     */
    @Override
    public void setBounds(final Rectangle rectangle) {
        super.setBounds(rectangle);
        this.lbImage.setBounds(rectangle);
        this.setSize(rectangle.getSize());
    }

    /**
     * Set size of JImage from scale of respect to.
     * @param scaleOf size use respectTo.
     * @param respectTo size for scale.
     */
    public void setScaleOfRespect(final int scaleOf, final int respectTo) {
        final Dimension dimension = EngineImage.getSizeImageFromScale(this.path, scaleOf, respectTo);
        this.setSize(dimension);
    }

    /**
     * Describes JImage from size and path.
     */
    @Override
    public String toString() {
        return "JImage{"
                + "width=" + width 
                + ", height=" + height 
                + ", path='" + path + '\'' 
                + ", imageIcon=" + imageIcon 
                + ", lbImage=" + lbImage + '}';
    }

    /**
     * Set size Image from imageIcon with width and height.
     * @param imageIcon for set size.
     * @param width parameter for set size.
     * @param height parameter for set size.
     */
    public static void setSizeImageIcon(final ImageIcon imageIcon, final int width, final int height) {
        final Image img = imageIcon.getImage();
        imageIcon.setImage(img.getScaledInstance(width, height, Image.SCALE_REPLICATE));
    }

    /**
     * Set size Image from imageIcon with width and height.
     * @param imageIcon for set size.
     * @param dimension parameter for set size.
     */
    public static void resizeImageIcon(final ImageIcon imageIcon, final Dimension dimension) {
        JImage.setSizeImageIcon(imageIcon, dimension.width, dimension.height);
    }

    /**
     * Get image from path passing by iconImage.
     * @param path of image.
     * @return Image from path.
     */
    public static Image getImageFromPath(final String path) {
        final URL imgURL = ClassLoader.getSystemResource(path);
        return new ImageIcon(imgURL).getImage();
    }
}
