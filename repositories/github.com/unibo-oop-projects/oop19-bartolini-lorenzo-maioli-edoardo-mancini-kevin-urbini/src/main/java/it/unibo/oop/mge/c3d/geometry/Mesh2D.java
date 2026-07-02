package it.unibo.oop.mge.c3d.geometry;

import java.util.List;

/**
 * 
 * A mesh in 2D space.
 *
 */
public interface Mesh2D {
    /**
     * 
     * @param segments the list of segments to build the Mesh2D from
     * @return a new Mesh2D
     */
    static Mesh2D of(List<Segment2D> segments) {
        return new Mesh2DImpl(segments);
    }

    /**
     * 
     * @return a list of this mesh's segments
     */
    List<Segment2D> getSegments();
}
