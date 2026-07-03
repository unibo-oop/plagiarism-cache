package controller;

import model.hitbox.HitboxCircle;
import model.hitbox.HitboxRectangle;
import util.ImageLoaderProxy.ImageID;

/**
 * Defines the wrapper of the model's objects.
 */
public class ModelWrapperImpl implements ModelWrapper {

    private final double x;
    private final double y;
    private final double width;
    private final double height;
    private final ImageID img;
    private final boolean circle;

    /**
     * Construct a new instance of ModelWrapperImpl with an object having a
     * circle hitbox.
     * 
     * @param circle
     *            the object having a circle hitbox
     * @param img
     *            object's image identifier
     */
    public ModelWrapperImpl(final HitboxCircle circle, final ImageID img) {

        this.x = circle.getX();
        this.y = circle.getY();
        this.width = circle.getRadius() * 2;
        this.height = circle.getRadius() * 2;
        this.img = img;
        this.circle = true;
    }

    /**
     * Construct a new instance of ModelWrapperImpl with an object having a
     * rectangle hitbox.
     * 
     * @param rectangle
     *            the object having a rectangle hitbox
     * @param img
     *            object's image ID
     */
    public ModelWrapperImpl(final HitboxRectangle rectangle, final ImageID img) {

        this.x = rectangle.getX();
        this.y = rectangle.getY();
        this.width = rectangle.getWidth();
        this.height = rectangle.getHeight();
        this.img = img;
        this.circle = false;
    }

    @Override
    public double getX() {

        return x;
    }

    @Override
    public double getY() {

        return y;
    }

    @Override
    public ImageID getImg() {

        return img;
    }

    @Override
    public double getHeight() {

        return height;
    }

    @Override
    public double getWidth() {

        return width;
    }

    @Override
    public boolean isCircle() {
        return circle;
    }

}
