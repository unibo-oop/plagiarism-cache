package spacesurvival.model.collision.bounding;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import spacesurvival.model.common.P2d;
import spacesurvival.utilities.SystemVariables;
import spacesurvival.model.EngineImage;

public class RectBoundingBox implements BoundingBox {
    private P2d p0, p1;
    private AffineTransform transform;
    private double width, height;

    /**
     * Constructor for new simple RectBoundingBox, composed by the up left point and the bottom right point equals and setted to (0,0).
     * 
     */
    public RectBoundingBox() {
        this.p0 = new P2d(0, 0);
        this.p1 = new P2d(0, 0);
        this.transform = new AffineTransform();
    }

    /**
     * Constructor for new RectBoundingBox, composed by the up left point p0 and the bottom right point p1 and an AffineTransform to set the rotation or straching.
     * @param p0 a P2d rapresenting the up left point.
     * @param p1 a P2d rapresenting the bottom right point.
     * @param transform an AffineTransform rapresenting the transformation for the RectBoundingBox.
     */
    public RectBoundingBox(final P2d p0, final P2d p1, final AffineTransform transform) {
        this();
        this.p0 = p0;
        this.p1 = p1;
        this.width = Math.abs(this.p1.getX() - this.p0.getX());
        this.height = Math.abs(this.p1.getY() - this.p0.getY());
        this.transform.setTransform(transform);
        this.transform.setToTranslation(p0.getX(), p0.getY());
    }

    /**
     * Constructor for new RectBoundingBox, composed by the up left point p0 and the bottom right point p1 and an AffineTransform to set the rotation or straching.
     * @param center a P2d rapresenting the center of the box.
     * @param engineImage an EngineImage to create a bounding box with the dimensions given by the image.
     * @param transform an AffineTransform rapresenting the transformation for the RectBoundingBox.
     */
    public RectBoundingBox(final P2d center, final EngineImage engineImage, final AffineTransform transform) {
        this.p0 = new P2d(center.getX() - (engineImage.getWidth() / 2), center.getY() - (engineImage.getHeight() / 2));
        this.p1 = new P2d(center.getX() + (engineImage.getWidth() / 2), center.getY() + (engineImage.getHeight() / 2));
        this.width = engineImage.getWidth();
        this.height = engineImage.getHeight();
        this.transform = new AffineTransform();
        this.transform.setTransform(transform);
        this.transform.setToTranslation(center.getX() - (engineImage.getWidth() / 2), center.getY() - (engineImage.getHeight() / 2));
    }

    /**
     * Constructor for new RectBoundingBox, composed by the up left point p0 and the bottom right point p1 and an AffineTransform to set the rotation or straching.
     * @param rectangle a Rectangle to create a box with the same size.
     */
    public RectBoundingBox(final Rectangle rectangle) {
        this.p0 = new P2d(rectangle.getX(), rectangle.getY());
        this.p1 = new P2d(rectangle.getWidth() * SystemVariables.SCALE_X, rectangle.getHeight() * SystemVariables.SCALE_Y);
        this.width = rectangle.getWidth();
        this.height = rectangle.getHeight();
        this.transform = new AffineTransform();
    }

    /**
     * Create a Rect Bounding Box.
     * 
     * @param position position of the up left point
     * @param engineImage EngineImage to create a dimension specific RectBoundingBox
     * @param transform transform to apply
     * @return the created RectBoundingBox
     */
    public static BoundingBox createRectBoundingBox(final P2d position, final EngineImage engineImage, final AffineTransform transform) {
        return new RectBoundingBox(new P2d(position.getX() + engineImage.getWidth() / 2, position.getY() + engineImage.getHeight() / 2),
                engineImage, transform);
    }

    /**
     * Return the up left point of the RectBoundingBox.
     * @return the up left point of the box.
     */
    public P2d getULCorner() {
        return this.p0;
    }

    /**
     * Return the bottom right point of the RectBoundingBox.
     * @return the bottom right point of the box.
     */
    public P2d getBRCorner() {
        return this.p1;
    }

    /**
     * Return the width of the RectBoundingBox.
     * @return the width of the box.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the RectBoundingBox.
     * @return the height of the box.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Return the transform of the RectBoundingBox.
     * @return the transform of the box.
     */
    @Override
    public AffineTransform getTransform() {
        return this.transform;
    }

    /**
     * Set the transformation to the RectBoundingBox.
     * @param transform the transformation to be set in the rect bound.
     */
    @Override
    public void setTransform(final AffineTransform transform) { 
        this.transform.setTransform(transform); 

        final AffineTransform trans = new AffineTransform();
        trans.setTransform(transform);
        this.p0 = new P2d(trans.getTranslateX(), trans.getTranslateY());
        trans.translate(this.width, this.height);
        this.p1 = new P2d(trans.getTranslateX(), trans.getTranslateY());
    }

    /** 
     * Return a string rapresenting the RectBoudingBox.
     * @return return the string.
     */
    @Override
    public String toString() {
        return "RectBoundingBox [point Up Left=" + this.p0 + ", point Bottom Right=" + this.p1 + "], AffineTransform = " + this.transform.toString();
    }
}
