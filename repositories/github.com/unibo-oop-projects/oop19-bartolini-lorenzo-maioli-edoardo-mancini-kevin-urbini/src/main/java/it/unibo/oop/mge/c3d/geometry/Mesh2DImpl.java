package it.unibo.oop.mge.c3d.geometry;

import java.util.List;

/**
 * 
 * Base implementation of Mesh2D.
 *
 */
public class Mesh2DImpl implements Mesh2D {
    private final List<Segment2D> segments;

    // package protected
    Mesh2DImpl(final List<Segment2D> segments) {
        super();
        this.segments = segments;
    }

    @Override
    public final List<Segment2D> getSegments() {
        return segments;
    }
}
