package view;

import java.awt.Dimension;

import java.awt.Image;
import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * This class is the representation of an element in the GraphicEnvironment.
 * @author Missi
 *
 */
public class GraphicElem implements PongElement {
    private Image image;
    private int width;
    private int height;
    private final Dimension size;
    private boolean visible = true;

    /**
     * @param s **The Image URL String**
     * @param width **The width of this GraphicElem**
     * @param height **The height of this GraphicElem**
     */
    public GraphicElem(final String s, final int width, final int height) {
        super();
        this.size = new Dimension(width, height);
        this.setImage(s);
    }

    /**
     * @param img **The Image**
     * @param width **The width of this GraphicElem**
     * @param height **The height of this GraphicElem**
     */
    public GraphicElem(final Image img, final int width, final int height) {
        super();
        this.size = new Dimension(width, height);
        this.image = img;
    }

    /** 
     * @see view.PongElement#getPosition()
     */
    @Override
    public Point getPosition() {
        return new Point(this.width, this.height);
    }

    /**
     * @see view.PongElement#setPosition(java.awt.Point)
     */
    @Override
    public void setPosition(final Point p) {
        this.width = p.x;
        this.height = p.y;
    }

    /**
     * @see view.PongElement#getImage()
     */
    @Override
    public Image getImage() {
        return this.image;
    }

    /**
     * @see view.PongElement#getWidth()
     */
    @Override
    public int getWidth() {
        return this.size.width;
    }

    /**
     * @see view.PongElement#getHeight()
     */
    @Override
    public int getHeight() {
        return this.size.height;
    }

    private void setImage(final String s) {
        try { 
            image = ImageIO.read(getClass().getResource("/res/" + s)) // found the image 
                    .getScaledInstance(this.size.width, this.size.height, Image.SCALE_SMOOTH); // resized the image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see view.PongElement#setVisible(boolean)
     */
    @Override
    public void setVisible(final boolean b) {
        this.visible = b;
    }

    /**
     * @see view.PongElement#isVisible()
     */
    @Override
    public boolean isVisible() {
        return this.visible;
    }
}
