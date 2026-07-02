package thatlevelagain.view.sprite;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import thatlevelagain.view.load_image.ImageManager;
import thatlevelagain.view.load_image.ImagePath;
import thatlevelagain.view.map.MapImpl;

/**
 * 
 * represent entity image and position for button.
 *
 */
public class Scossa extends SpriteImpl /*implements Runnable */ {

    //private static final int TIME = 150;
    //private static final int AWAY = 10;
    private boolean firstimage;
    //private boolean attivo;
    private final MapImpl map;
    //private int counter;
    private final BufferedImage image1, image2;
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
     * @param firstImage
     *         choose percorso1 or percorso2
     * @param mapImpl 
     *         map
     */
    public Scossa(final int x, final int y, final int width, final int height, final boolean firstImage, final MapImpl mapImpl) {
        super(x, y, width, height, ImageManager.getListLoader().get(ImagePath.SCOSSA1.getPosition()));
        this.map = mapImpl;
        image1 = ImageManager.getListLoader().get(ImagePath.SCOSSA1.getPosition());
        image2 = ImageManager.getListLoader().get(ImagePath.SCOSSA2.getPosition());
        if (firstImage) {
            this.setImage(image1);
        } else {
            this.setImage(image2);
        }
        this.firstimage = firstImage;
    }
    /**
     * 
     * @param firstImage
     *         set first image if true
     */
    public void setFisrtImage(final boolean firstImage) {
        this.firstimage = firstImage;
    }

    /**
     * 
     * @return
     *         firstImage
     */
    public boolean isFisrstimage() {
         return this.firstimage;
    }
    /**
     * id scossa is drawing, its bounds are its dimension, but if it isn't drawing, its bounds are 0.
     */
    @Override
    public final Rectangle getBounds() {
        if (this.map.isScossaPresent()) {
            return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            return new Rectangle(0, 0, 0, 0);
        }
    }
    /**
     * 
     * @return
     *         second image
     */
    public BufferedImage getImg2() {
        return image2;
    }
    /**
     * 
     * @return
     *         first image
      */
    public BufferedImage getImg1() {
        return image1;
    }
}
