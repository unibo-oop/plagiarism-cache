package thatlevelagain.view.sprite;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;



/**
 * sprite general class.
 *
 */
public class SpriteImpl implements Sprite {

    private static final int RIDUZIONERETTANGOLO = 1;
    private static final int DUE = 2;
    private static final int BASE = 5;
    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage image;

    /**
     * constructor.
     * @param x
     *         x position
     * @param y
     *         y position
     * @param width
     *         shape's width
     * @param height
     *         shape's height
     * @param image
     *         image
     */
    public SpriteImpl(final int x, final int y, final int width, final int height, final BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    /**
     * 
     * @param g
     *         where draw img
     */
    public void disegna(final Graphics2D g) {
        g.drawImage(image, x, y, width, height, null);
    }
    /**
     * 
     * @return
     *         img location.
     */
    public Dimension getLocation() {
         return new Dimension(x, y);
    }

    /**
     * 
     * @return
     *      x position
     */
    public int getX() {
        return x;
     }
    /**
     * 
     * @return
     *      y position
     */
    public int getY() {
        return y;
    }
    /**
     * 
     * @return
     *        shape's width
     */
    public int getWidth() {
        return width;
    }
    /**
     * 
     * @return
     *         shape's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * 
     * @param x
     *         set x value.
     */
    public void setX(final int x) {
        this.x = x;
    }
    /**
     * 
     * @param y
     *         set y value.
     */
    public void setY(final int y) {
        this.y = y;
    }
    /**
     * 
     * @param width
     *         set width value.
     */
    public void setWidth(final int width) {
        this.width = width;
    }
    /**
     * 
     * @param height
     *         set height value.
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *         image.
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * 
     * @param image
     *         set image.
     */
    public void setImage(final BufferedImage image) {
        this.image = image;
    }
    /**
     * 
     * @return
     *         Shape's rectangle dimension.
     */
    public Rectangle getBounds() {  //per il collisiondetection
         return new Rectangle(x, y, width, height);
    }
    /**
     * 
     * @return
     *         Shape's up rectangle dimension.
     */
    public Rectangle getRectUp() {
        return new Rectangle(x + RIDUZIONERETTANGOLO, y, width - (DUE * RIDUZIONERETTANGOLO), BASE);
    }
    /**
     * 
     * @return
     *         Shape's bottom rectangle dimension.
     */
    public Rectangle getRectBottom() {
        return new Rectangle(x + RIDUZIONERETTANGOLO, y  + height - BASE, width - (DUE * RIDUZIONERETTANGOLO), BASE);
    }
    /**
     * 
     * @return
     *         Shape's left rectangle dimension.
     */
    public Rectangle getRectLeft() {
        return new Rectangle(x, y + RIDUZIONERETTANGOLO, BASE, height - (DUE * RIDUZIONERETTANGOLO));
    }
    /**
     * 
     * @return
     *         Shape's right rectangle dimension.
     */
    public Rectangle getRectRight() {
        return new Rectangle(x + width - BASE, y + RIDUZIONERETTANGOLO, BASE, height - (DUE * RIDUZIONERETTANGOLO));
    }
}
