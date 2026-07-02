package spacesurvival.model.collision.bounding;

import java.awt.geom.AffineTransform;

public interface BoundingBox {

    /**
     * Return the transform of the box.
     * 
     * @return an AffineTransform
     */
    AffineTransform getTransform();

    /**
     * Set the transform to the box.
     * 
     * @param transform AffineTransform to set
     */
    void setTransform(AffineTransform transform);
}
