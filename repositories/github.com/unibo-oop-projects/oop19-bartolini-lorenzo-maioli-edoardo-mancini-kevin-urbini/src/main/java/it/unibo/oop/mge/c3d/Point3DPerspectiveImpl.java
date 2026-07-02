package it.unibo.oop.mge.c3d;

import it.unibo.oop.mge.c3d.geometry.Line;
import it.unibo.oop.mge.c3d.geometry.Point2D;
import it.unibo.oop.mge.c3d.geometry.Point3D;

/**
 * 
 * Base implementation of Point3DPerspective.
 *
 */
public class Point3DPerspectiveImpl implements Point3DPerspective {

    private final Point3D point;

    Point3DPerspectiveImpl(final Point3D point) {
        this.point = point;
    }

    @Override
    public final Point2D render(final Point3D pointOfView) {
        final double finalX = Line.fromPoints(Point2D.fromDoubles(this.point.getX(), this.point.getY()), Point2D.fromDoubles(pointOfView.getX(), pointOfView.getY()))
                .getZero();
        final double finalY = Line.fromPoints(Point2D.fromDoubles(this.point.getZ(), this.point.getY()), Point2D.fromDoubles(pointOfView.getZ(), pointOfView.getY()))
                .getZero();
        return Point2D.fromDoubles(finalX, finalY);
    }

}
