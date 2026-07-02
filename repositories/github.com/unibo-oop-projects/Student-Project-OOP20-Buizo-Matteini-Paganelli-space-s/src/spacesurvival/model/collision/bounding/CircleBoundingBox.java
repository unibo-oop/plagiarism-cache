package spacesurvival.model.collision.bounding;

import spacesurvival.model.EngineImage;
import spacesurvival.model.common.P2d;

import java.awt.geom.AffineTransform;

public class CircleBoundingBox implements BoundingBox {
    private final P2d center;
    private final double radius;
    private final AffineTransform transform;

    /**
     * Constructor for new simple CircleBoundingBox, composed by the a center in (0,0), a radius setted to 0 and an AffineTransform.
     * 
     */
    public CircleBoundingBox() {
        this.center = new P2d(0, 0);
        this.radius = 0;
        this.transform = new AffineTransform();
    }

    /**
     * Constructor for new simple CircleBoundingBox, composed by the a center in (0,0), a radius setted to 0 and an AffineTransform.
     * 
     * @param center P2d rapresenting the center of the CircleBoundingBox.
     * @param radius double rapresenting the radius of the CircleBoundingBox.
     * @param transform an affine to set the transformation.
     */
    public CircleBoundingBox(final P2d center, final double radius, final AffineTransform transform) {
        this.center = new P2d(0, 0);
        this.center.setX(center.getX());
        this.center.setY(center.getY());
        this.radius = radius;
        this.transform = transform;
    }

    /**
     * Create a Circle Bounding Box.
     * 
     * @param position position of the up left point
     * @param engineImage EngineImage to create a dimension specific CircleBoundingBox
     * @param transform transform transform to apply
     * @return the created RectBoundingBox
     */
    public static BoundingBox createCircleBoundingBox(final P2d position,
            final EngineImage engineImage, final AffineTransform transform) {
        final int half = 2;
        return new CircleBoundingBox(position, engineImage.getWidth() / half, transform);
    }

    /**
     * Return the transform of the CircleBoundingBox.
     * @return the transform of the box.
     */
    @Override
    public AffineTransform getTransform() {
        return this.transform;
    }

    /**
     * Set the transformation to the CircleBoundingBox.
     * @param transform the transformation to be set in the circle bound.
     */
    @Override
    public void setTransform(final AffineTransform transform) {
        this.transform.setTransform(transform);
    }

    /**
     * Return the radius of the CircleBoundingBox.
     * @return the radius of the circle.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Return a P2d representing the center of the CircleBoundingBox.
     * @return the center of the circle.
     */
    public P2d getCenter() {
        return this.center;
    }

    /** 
     * Return a string rapresenting the CircleBoudingBox.
     * @return return the string.
     */
    @Override
    public String toString() {
        return "CircleBoundingBox [radius=" + this.radius + ", center=" + this.center.toString() + ", transform=" + this.transform.toString() + "]";
    }
}
