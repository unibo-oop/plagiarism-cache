package it.unibo.oop.mge.c3d.geometry;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 
 * A mesh in 3D space.
 * 
 */
public interface Mesh {
    /**
     * 
     * @param segments the list of segments to generate the mesh from
     * @return a new Mesh with the provided segments
     */
    static Mesh fromSegments(final List<Segment3D> segments) {
        return new MeshImpl(segments);
    }

    static Mesh fromMeshes(final List<Mesh> meshes) {
        return Mesh.fromSegments(
                meshes.stream().flatMap(mesh -> mesh.getSegments().stream()).collect(Collectors.toList()));
    }

    /**
     * 
     * @return the scale of the Mesh
     */
    double getScale();

    /**
     * 
     * @return the list of segments
     */
    List<Segment3D> getSegments();

    /**
     * 
     * @param transformation the transformation to apply to each point
     * @return a new Mesh, with the transformation applied
     */
    Mesh transformed(Function<Double, Double> transformation);

    /**
     * 
     * @param vector the vector to add to each point
     * @return a new Mesh, with the vector applied
     */
    Mesh translated(Point3D vector);

}
