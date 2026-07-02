package it.unibo.oop.mge.c3d;

import it.unibo.oop.mge.c3d.geometry.Point2D;
import it.unibo.oop.mge.c3d.geometry.Point3D;

/**
 * 
 * An object that can apply perspective to a {@link Point3D}.
 *
 */
public interface Point3DPerspective {

    /**
     * 
     * @param point the source point
     * @return a new Point3DRenderer
     */
    static Point3DPerspective fromPoint(Point3D point) {
        return new Point3DPerspectiveImpl(point);
    }

    /**
     * 
     * @param pointOfView the point of view for the perspective calculation
     * @return the rendered point
     */
    Point2D render(Point3D pointOfView);
}
