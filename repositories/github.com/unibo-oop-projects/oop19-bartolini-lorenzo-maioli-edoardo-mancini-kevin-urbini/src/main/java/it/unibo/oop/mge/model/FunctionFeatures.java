package it.unibo.oop.mge.model;

import java.util.List;

import it.unibo.oop.mge.c3d.geometry.Point3D;
import it.unibo.oop.mge.c3d.geometry.Segment3D;

/**
 * The Interface FunctionFeatures.
 */
public interface FunctionFeatures {

    /**
     * Gets the point of absolute maximum of the function.
     *
     * @return get the absolute maximum of the function.
     */
    Point3D getPointOfAbsoluteMax();

    /**
     * Gets the point of absolute minimum of the function.
     *
     * @return get the absolute minimum of the function.
     */
    Point3D getPointOfAbsoluteMin();

    /**
     * Gets the polygonal model.
     *
     * @return get the polygonal Model of the Function.
     */
    List<Segment3D> getPolygonalModel();

    /**
     * Gets the polygonal model of the axis.
     *
     * @return get the Segments of the 3 axis between the interval given.
     */
    List<Segment3D> getPolygonalAxis();
}
